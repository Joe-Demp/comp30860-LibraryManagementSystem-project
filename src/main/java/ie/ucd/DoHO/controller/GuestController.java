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
        return "index";
    }

    @GetMapping("/loan_history")
    public String loan_history() {
        return "loan_history";
    }

    @GetMapping("/artifact")
    public String artifact() {
        return "artifact";
    }

    @GetMapping("/user")
    public String user() {
        return "user_profile";
    }

    @GetMapping("/portal")
    public String librarianPortal() {
        return "librarian_portal";
    }

    @GetMapping("/signUp")
    public String signUp(){
        return "sign_up.html";
    }


    @GetMapping("/searchArtifact")
    public String displayArtifacts(){
        return "search_artifact.html";
    }
}
