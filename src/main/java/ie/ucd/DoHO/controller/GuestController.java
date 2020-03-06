package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.*;
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
    private HibernateSearchDao searchservice;
    private Logger logger = LoggerFactory.getLogger(GuestController.class);

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", userSession.getUser());
    }

    @GetMapping("/")
    public String index(Model model) {
        if (userSession.getUser() != null)
            model.addAttribute("id", userSession.getUser().getId());
        return "index";
    }

    @GetMapping("/loan_history")
    public String loan_history() {
        return "loan_history";
    }

    @GetMapping("/artifact")
    public String viewArtifact(@RequestParam(name = "id") Integer id, Model model)
            throws IOException {
        Optional<Artifact> artifact = artifactRepository.findById(id);
        if (artifact.isPresent()) {
            model.addAttribute("artifact", artifact.get());
            model.addAttribute("additionalDetails", artifact.get().getAdditionalDetails());

            model.addAttribute("isAdmin", userSession.isAdmin());
            logger.info("In viewArtifact: user=" + userSession.getUser() + " isAdmin=" + userSession.isAdmin());
        } else {
            // todo return a proper error page here
            return "404";
        }
        return "artifact";
    }

    @GetMapping("/members")
    public String displayUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "search_users.html";
    }


    @GetMapping("/user_profile")
    public String user(@RequestParam("id") Integer id, Model model, HttpServletResponse response) throws IOException {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent() && user.get().getRole().equals("admin")) {
            response.sendRedirect("/portal");
        }

        model.addAttribute("fullName", user.get().getFullName());
        model.addAttribute("username", user.get().getUsername());
        model.addAttribute("email", user.get().getEmail());
        model.addAttribute("phoneNumber", user.get().getPhoneNumber());
        model.addAttribute("id", user.get().getId());
        model.addAttribute("created", user.get().getCreated());
        return "user_profile";
    }

    @GetMapping("/search_artifact")
    public String displayArtifacts(@RequestParam(value = "search", required = false) String query, Model model) {
        List<Artifact> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearchArtifact(query);
        } catch (Exception ignored) {
        }

        model.addAttribute("artifacts", searchResults);
        return "search_artifact.html";
    }

    @GetMapping("/search_members")
    public String displayMembers(@RequestParam(value = "searchMems", required = false) String query, Model model) {

       System.out.println("query :" + query);
        List<User> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearchUser(query);
            System.out.println(searchResults.size());
        } catch (Exception ignored) {

        }

        model.addAttribute("users", searchResults);
        return "search_users.html";
    }


    //TODO Authentication to prompt user to enter current password before changing to new
    @PostMapping("change_password")
    public void changePassword(String newPassword, HttpServletResponse response) throws IOException {
        User user = userSession.getUser();
        user.setPassword(newPassword);
        userRepository.save(user);
        response.sendRedirect("/");
    }


    @PostMapping("edit_profile")
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
}
