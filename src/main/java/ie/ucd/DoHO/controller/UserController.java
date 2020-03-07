package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.Contracts.LoanRepository;
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

    @PostMapping("/artifact/reserve")
    public String reserve(@RequestParam("aID") Integer artifactId,
                          @RequestParam("uName") String userName,
                          HttpServletResponse response) {
        if (userSession.isMember()) {

        } else if (userSession.isAdmin()) {

        }
        return "login_main";
    }
}
