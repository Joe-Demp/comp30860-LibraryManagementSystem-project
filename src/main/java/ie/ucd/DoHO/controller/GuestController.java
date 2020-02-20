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
        return "index.html";
    }
}
