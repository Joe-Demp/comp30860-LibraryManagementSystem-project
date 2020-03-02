package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private ArtifactRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", userSession.getUser());
        return "index.html";
    }

    @GetMapping("/loan_history")
    public String loan_history() {
        return "loan_history.html";
    }

    @GetMapping("/artifact")
    public String artifact() {
        return "artifact.html";
    }

    @GetMapping("/user")
    public String user() {
        return "user_profile.html";
    }

    @GetMapping("/signUp")
    public String signUp(){
        return "signUp.html";
    }

}
