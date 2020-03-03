package ie.ucd.DoHO.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Optional;

@Controller
public class GuestController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private ArtifactRepository artifactRepository;
    private ObjectMapper mapper = new ObjectMapper();

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
    public String viewArtifact(@RequestParam(name = "aID") Integer id, Model model)
            throws IOException {
        Optional<Artifact> artifact = artifactRepository.findById(id);
        if (artifact.isPresent()) {
            String artifactJSON = mapper.writeValueAsString(artifact.get());

            model.addAttribute("artifact", artifact.get());
            model.addAttribute("artifactJSON", artifactJSON);
        } else {
            return "404";
        }
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

    @GetMapping("/sign_up")
    public String signUp(){
        return "sign_up.html";
    }


    @GetMapping("/search_artifact")
    public String displayArtifacts(){
        return "search_artifact.html";
    }
}
