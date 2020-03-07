package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.Contracts.LoanRepository;
import ie.ucd.DoHO.model.Contracts.Reservation;
import ie.ucd.DoHO.model.Contracts.ReservationRepository;
import ie.ucd.DoHO.model.User;
import ie.ucd.DoHO.model.UserRepository;
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
    private ReservationRepository resRepository;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", userSession.getUser());
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
        model.addAttribute("loans", loanRepository.findByUserId(user.get().getId()));
        return "user_profile";
    }

    /**
     * One method to handle User reservations.<br>
     * Members can reserve on their own behalf while librarians must specify a username
     *
     * @param artifact
     * @param user
     * @param username the name of the user the administrator wants to reserve for
     * @param response
     * @return the profile page of the relevant user if logged in, the login page otherwise
     * @throws IOException
     */
    @PostMapping("/artifact/reserve")
    public String reserve(@RequestParam(name = "artifact") Artifact artifact,
                          @RequestParam(name = "user") User user,
                          @RequestParam(name = "username") String username,
                          HttpServletResponse response) throws IOException {
        if (userSession.isMember()) {
            Reservation res = new Reservation(user, artifact);
            resRepository.save(res);
            response.sendRedirect("/user_profile?id=" + user.getId());
        } else if (userSession.isAdmin()) {
            Optional<User> optionalUser = userRepository.findByUsername(username);

            if (optionalUser.isPresent()) {
                User reservee = optionalUser.get();

                // Check reservee is not an admin
                if (!reservee.getRole().equals("member")) {
                    logger.info("user " + username + " is an admin");
                    return "/errors/librarian_reservation";
                }

                Reservation res = new Reservation(reservee, artifact);
                resRepository.save(res);
                response.sendRedirect("/user_profile?id=" + reservee.getId());
            } else {
                logger.info("user " + username + " does not exist");
                response.sendRedirect("/error/no_such_user?uname=" + username);
            }
        }
        return "login_main";
    }

    /**
     * Returns a user with the given name if there is one
     * @param name the name of the user you are looking for
     * @return the User with username 'name', otherwise null
     */
//    private User findUserByName(String name) {
//
//    }
}
