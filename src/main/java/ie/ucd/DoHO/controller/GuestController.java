package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {
    @Autowired
    private ArtifactRepository repository;

    @GetMapping("/")
    public String index() {
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

    @GetMapping("/admin/portal")
    public String librarianPortal() {
        return "librarian_portal";
    }
}
