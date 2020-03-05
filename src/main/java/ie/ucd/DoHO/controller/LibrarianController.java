package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A controller to support privileged actions only available to a librarian (admin)
 */
@Controller
public class LibrarianController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtifactRepository artifactRepository;
    private Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", userSession.getUser());
    }

    @GetMapping("/portal")
    public String portal() {
        if (userSession.isAdmin()) {
            return "librarian_portal";
        }
        return "login_main";
    }

    @GetMapping("/create")
    public String addArtifact(Model model) {
        if (userSession.isAdmin()) {
            model.addAttribute("book", new Book());
            model.addAttribute("magazine", new Magazine());
            // todo add all attributes (all types of artifact)
            return "add_artifact";
        }
        return "login_main";
    }

    @PostMapping("/create/book")
    public String addBook(@ModelAttribute Book book, BindingResult errors,
                          Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.save(book);
            response.sendRedirect("/artifact?aID=" + book.getId());
        }
        return "login_main";
    }

    @PostMapping("/create/magazine")
    public String addMagazine(@ModelAttribute Magazine magazine, BindingResult errors,
                              Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.save(magazine);
            response.sendRedirect("/artifact?aID=" + magazine.getId());
        }
        return "login_main";
    }

    @PostMapping("/artifact/delete")
    public String deleteArtifact(@RequestParam(name = "aID") Integer id,
                                 HttpServletResponse response)
            throws IOException {
        logger.info("Inside deleteArtifact");
        if (userSession.isAdmin()) {
            logger.info("Deleting artifact with id = " + id);
            artifactRepository.deleteById(id);
            response.sendRedirect("/portal");
        }
        return "index";
    }
}
