package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.ArtifactRepository;
import ie.ucd.DoHO.model.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/search_user")
    public String displayUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "search_users.html";
    }

    @GetMapping("/user")
    public String user() {
        return "user_profile";
    }

    @GetMapping("/portal")
    public String librarianPortal() {
        return "librarian_portal";
    }

    @GetMapping("/sign_up")
    public String signUp(){
        return "sign_up.html";
    }


    @GetMapping("/search_artifact")
    public String displayArtifacts(){
        return "search_artifact.html";
    }
}
