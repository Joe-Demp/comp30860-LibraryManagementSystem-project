package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.ArtifactRepository;
import ie.ucd.DoHO.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/portal")
    public String portal() {
        return "librarian_portal";
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
