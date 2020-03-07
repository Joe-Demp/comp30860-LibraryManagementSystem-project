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
     * @param res      the Reservation bean passed from the form
     * @param username the name of the user the administrator wants to reserve for
     * @return the profile page of the relevant user if logged in, the login page otherwise
     */
    @PostMapping("/artifact/reserve")
    public String reserve(@RequestParam(name = "artifact") Artifact artifact,
                          @RequestParam(name = "user") User user,
                          @RequestParam(name = "username") String username,
                          HttpServletResponse response) throws IOException {
        if (userSession.isMember()) {
            Reservation res = new Reservation(user, artifact);
            resRepository.save(res);
            response.sendRedirect("/user_profile");
        } else if (userSession.isAdmin()) {

        }
        return "login_main";
    }
}
