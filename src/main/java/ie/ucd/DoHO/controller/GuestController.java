package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.*;
import ie.ucd.DoHO.model.Contracts.*;
import ie.ucd.DoHO.util.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class GuestController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private ArtifactRepository artifactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private OpeningHoursRepository openingHoursRepository;
    @Autowired
    private MotmRepository motmRepository;
    @Autowired
    private HibernateSearchDao searchservice;

    private Logger logger = LoggerFactory.getLogger(GuestController.class);

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", userSession.getUser());
    }

    @GetMapping("/")
    public String index(Model model) {
        if (userSession.getUser() != null) {
            model.addAttribute("id", userSession.getUser().getId());
        }
        model.addAttribute("title", "Home");

        addAllDays();
        model.addAttribute("openingHours", openingHoursRepository.findAll());
        model.addAttribute("open-string", openString());
        model.addAttribute("motms",motmRepository.findAll());

        return "index";
    }

    public boolean openNow() {
        LocalDateTime today = LocalDateTime.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(today);
        LocalTime time = LocalTime.from(today);
        Optional<OpeningHours> optionalOpen = openingHoursRepository.findById(dayOfWeek);

        if (optionalOpen.isPresent()) {
            OpeningHours open = optionalOpen.get();
            return time.isAfter(open.getOpening()) && time.isBefore(open.getClosing());
        }
        return false;
    }

    public String openString() {
        LocalDateTime today = LocalDateTime.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(today);

        if (openNow()) {
            Optional<OpeningHours> optionalOpen = openingHoursRepository.findById(dayOfWeek);
            if (optionalOpen.isPresent()) {
                OpeningHours open = optionalOpen.get();
                return "Open now until: " + Formatter.toTimeString(open.getClosing());
            }
        } else {
            return "We're closed!";
        }
        return null;
    }

    @GetMapping("/loan_history")
    public String loan_history(Model model) {
        model.addAttribute("title", "Loan History");
        return "loan_history";
    }

    @GetMapping("/artifact")
    public String viewArtifact(@RequestParam(name = "id") Integer id, Model model)
            throws IOException {
        model.addAttribute("title", "Artifact");
        Optional<Artifact> artifact = artifactRepository.findById(id);
        if (artifact.isPresent()) {
            model.addAttribute("artifact", artifact.get());
            model.addAttribute("additionalDetails", artifact.get().getAdditionalDetails());
            model.addAttribute("reservation", new Reservation());

            if (userSession.isAdmin()) {
                model.addAttribute("isAdmin", true);
                model.addAttribute("artifactForm", new ArtifactForm());
            } else if (userSession.isMember()) {
                model.addAttribute("isMember", true);
            } else {
                model.addAttribute("isGuest", true);
            }
        } else {
            // todo return a proper error page here
            return "404";
        }
        return "artifact";
    }

    @GetMapping("/catalogue")
    public String displayCatalogue(Model model) {
        model.addAttribute("title", "Catalogue");
        model.addAttribute("artifacts", artifactRepository.findAll());
        return "search_artifact.html";
    }

    @GetMapping("/catalogue/on_loan")
    public String displayOnLoan(Model model) {

        model.addAttribute("loans", loanRepository.findAll());
        System.out.println(loanRepository.findAll().size());
        return "loan_history.html";
    }

    @GetMapping("/catalogue/reserved")
    public String displayReserved(Model model) {

        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations.html";
    }


    @GetMapping("/search_artifact")
    public String displayArtifacts(@RequestParam(value="search",required = false)String query, Model model) {

        if(query.isEmpty()){
            return "search_artifact.html";
        }
        model.addAttribute("title", "Search Artifact");
        List<Artifact> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearchArtifact(query);
        } catch (Exception ignored) {
        }

        System.out.println(searchResults.size());
        model.addAttribute("artifacts", searchResults);
        return "search_artifact.html";
    }


    @GetMapping("/members")
    public String allMembers(Model model) {
        model.addAttribute("title", "Members");
        model.addAttribute("users", userRepository.findAll());
        return "search_users.html";
    }

    @GetMapping("/search_members")
    public String displayMembers(@RequestParam(value = "searchMems", required = false) String query, Model model) {

        if(query.isEmpty()){
            return "search_users.html";
        }
        model.addAttribute("title", "Search Members");
        List<User> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearchUser(query);
        } catch (Exception ignored) {

        }

        model.addAttribute("users", searchResults);
        return "search_users.html";
    }


    //TODO Authentication to prompt user to enter current password before changing to new
    @PostMapping("/change_password")
    public void changePassword(String newPassword, HttpServletResponse response) throws IOException {
        User user = userSession.getUser();
        user.setPassword(newPassword);
        userRepository.save(user);
        response.sendRedirect("/");
    }


    @PostMapping("/edit_profile")
    public void editProfile(String newName, String newUsername, String newEmail, String newPhoneNumber, HttpServletResponse response) throws IOException {
        User user = userSession.getUser();
        Integer id = user.getId();
        user.setFullName(newName);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
        response.sendRedirect("/user_profile?id=" + id);
    }

    @PostMapping("/update_media_of_month")
    public void updateMotm(String content, HttpServletResponse response) throws IOException {
      List<Motm> motm = motmRepository.findAll();
        for (Motm mediaOfMonth : motm) {

            mediaOfMonth.setBodyOfText(content);
            motmRepository.save(mediaOfMonth);
            System.out.println(mediaOfMonth.getBodyOfText());
        }

        response.sendRedirect("/");

    }


    private void addAllDays() {
        OpeningHours[] hours = new OpeningHours[]{
                new OpeningHours(DayOfWeek.MONDAY, LocalTime.NOON, LocalTime.MIDNIGHT),
                new OpeningHours(DayOfWeek.TUESDAY, LocalTime.NOON, LocalTime.MIDNIGHT),
                new OpeningHours(DayOfWeek.WEDNESDAY, LocalTime.NOON, LocalTime.MIDNIGHT),
                new OpeningHours(DayOfWeek.THURSDAY, LocalTime.NOON, LocalTime.MIDNIGHT),
                new OpeningHours(DayOfWeek.FRIDAY, LocalTime.NOON, LocalTime.MIDNIGHT),
                new OpeningHours(DayOfWeek.SATURDAY, LocalTime.NOON, LocalTime.MIDNIGHT),
                new OpeningHours(DayOfWeek.SUNDAY, LocalTime.NOON, LocalTime.MIDNIGHT)
        };

        List<OpeningHours> hoursList = Arrays.asList(hours);
        openingHoursRepository.saveAll(hoursList);
    }
}
