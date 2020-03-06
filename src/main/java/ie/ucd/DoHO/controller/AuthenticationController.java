package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.User;
import ie.ucd.DoHO.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class AuthenticationController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login_main")
    public String login_main(Model model) {
        model.addAttribute("error", "Username or Password not correct. Please try again.");
        return "login_main.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (userSession.isLoginFailed()) {
            userSession.setLoginFailed(false);
        }
        return "login.html";
    }

    @PostMapping("/login")
    public void doLogin(String username, String password, HttpServletResponse response) throws IOException {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent() && user.get().getRole().equals("admin")) {
            userSession.setUser(user.get());
            response.sendRedirect("/portal");
        } else if (user.isPresent()) {
            userSession.setUser(user.get());
            response.sendRedirect("/");
        }
        else {
            userSession.setLoginFailed(true);
            response.sendRedirect("/login_main");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        userSession.setUser(null);
        response.sendRedirect("/");
    }

    @GetMapping("/sign_up")
    public String signUp() {
        return "sign_up.html";
    }

    @PostMapping("/signup")
    public void signup(String username, String password, String email, String phoneNumber, String fullName, HttpServletResponse response) throws IOException{
        User user = new User(fullName, username, password, email, phoneNumber, "member");
        userRepository.save(user);
        response.sendRedirect("/");
    }
}
