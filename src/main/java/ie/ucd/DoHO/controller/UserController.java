package ie.ucd.DoHO.controller;

import antlr.debug.MessageAdapter;
import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.Contracts.LoanRepository;
import ie.ucd.DoHO.model.Contracts.ReservationRepository;
import ie.ucd.DoHO.model.Contracts.Reservation;
import ie.ucd.DoHO.model.Contracts.ReservationRepository;
import ie.ucd.DoHO.model.Message;
import ie.ucd.DoHO.model.MessageRepository;
import ie.ucd.DoHO.model.User;
import ie.ucd.DoHO.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MessageRepository messageRepository;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("user", userSession.getUser());
    }

    /**
     * @param id the id of the user profile you want to pull up
     */
    @GetMapping("/user_profile")
    public String user(@RequestParam("id") Integer id, Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("title", "Profile");
        Optional<User> optionalUser = userRepository.findById(id);
        User actor = userSession.getUser();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (actor.isAdmin()) {
                // if they look for their own profile, give them the portal
                // otherwise let them view the user's profile
                if (actor.getId().equals(id)) {
                    response.sendRedirect("/portal?id=" + id);
                }
            } else if (actor.isMember()) {
                // if they look for a profile that's not theirs give them the index
                if (!actor.getId().equals(id)) {
                    response.sendRedirect("/");
                }
            }

            model.addAttribute("fullName", optionalUser.get().getFullName());
            model.addAttribute("username", optionalUser.get().getUsername());
            model.addAttribute("email", optionalUser.get().getEmail());
            model.addAttribute("phoneNumber", optionalUser.get().getPhoneNumber());
            model.addAttribute("id", optionalUser.get().getId());
            model.addAttribute("created", optionalUser.get().getCreated());
            model.addAttribute("loans", loanRepository.findByUserId(optionalUser.get().getId()));
            model.addAttribute("reservations", reservationRepository.findByUserId(optionalUser.get().getId()));
            return "user_profile";
        }
        return "errors/no_such_user";
    }

    /**
     * One method to handle User reservations.<br>
     * Members can reserve on their own behalf while librarians must specify a username
     *
     * @param artifact the Artifact to be reserved
     * @param user the user who wants to have the Artifact reserved
     * @param username the name of the user the administrator wants to reserve for
     * @param response an HttpServletResponse
     * @return the profile page of the relevant user if logged in, the login page otherwise
     * @throws IOException from the HttpServletResponse
     */
    @PostMapping("/artifact/reserve")
    public String reserve(@RequestParam(name = "artifact") Artifact artifact,
                          @RequestParam(name = "user") User user,
                          @RequestParam(name = "username") String username,
                          HttpServletResponse response) throws IOException {
        if (userSession.isMember()) {
            if (reservedAlready(user, artifact)) {
                return "errors/reserved_already";
            }
            reserveForUser(user, artifact, response);
        } else if (userSession.isAdmin()) {
            return adminReserve(username, artifact, response);
        }
        return "login_main";
    }

    /**
     * Helper function to reserve Artifacts for a user by an admin
     */
    private String adminReserve(String username, Artifact artifact, HttpServletResponse response)
            throws IOException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User reservee = optionalUser.get();

            // Check reservee is not an admin
            if (reservee.isAdmin()) {
                logger.info("user " + username + " is an admin");
                return "/errors/librarian_reservation";
            } else if (reservedAlready(reservee, artifact)) {
                logger.info("user " + username + " has already reserved " + artifact.getTitle());
                return "/errors/reserved_already";
            }

            reserveForUser(reservee, artifact, response);
        } else {
            logger.info("user " + username + " does not exist");
            response.sendRedirect("/error/no_such_user?uname=" + username);
        }
        return "login_main";
    }

    private void reserveForUser(User user, Artifact artifact, HttpServletResponse response)
            throws IOException {
        Reservation res = new Reservation(user, artifact);
        reservationRepository.save(res);
        response.sendRedirect("/user_profile?id=" + user.getId());
    }

    private boolean reservedAlready(User user, Artifact artifact) {
        List<Reservation> resList = reservationRepository.findByUserAndArtifact(user, artifact);
        return !resList.isEmpty();
    }


    @PostMapping("/message")
    public void newMessage(String name, String email, String subject, String content, HttpServletResponse response) throws IOException{
        Message message = new Message(name, email, subject, content);
        messageRepository.save(message);
        response.sendRedirect("/");
    }

}
