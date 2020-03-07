package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private MessageRepository messageRepository;
    private static Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", userSession.getUser());
    }

    @GetMapping("/portal")
    public String portal(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("title", "Librarian Portal");
        if (userSession.isAdmin()) {
            model.addAttribute("fullName", userSession.getUser().getFullName());
            model.addAttribute("username", userSession.getUser().getUsername());
            model.addAttribute("email", userSession.getUser().getEmail());
            model.addAttribute("phoneNumber", userSession.getUser().getPhoneNumber());
            model.addAttribute("id", userSession.getUser().getId());
            model.addAttribute("created", userSession.getUser().getCreated());
            model.addAttribute("messages", messageRepository.findAll());

            return "librarian_portal";
        }
        return "login_main";
    }

    @GetMapping("/create")
    public String addArtifact(Model model) {
        model.addAttribute("title", "Create Artifact");
        if (userSession.isAdmin()) {
            model.addAttribute("book", new Book());
            model.addAttribute("cd", new CD());
            model.addAttribute("comic", new Comic());
            model.addAttribute("ebook", new EBook());
            model.addAttribute("magazine", new Magazine());
            model.addAttribute("periodical", new Periodical());
            model.addAttribute("video", new Video());
            return "add_artifact";
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

    @PostMapping("/artifact/edit")
    public String editArtifact(@ModelAttribute ArtifactForm form, BindingResult errors,
                               Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            Artifact artifact = makeNewArtifact(form);
            artifactRepository.deleteById(form.getId());
            artifactRepository.save(artifact);

            response.sendRedirect("/artifact?id=" + artifact.getId());
        }
        return "login_main";
    }

    public Artifact makeNewArtifact(ArtifactForm form) {
        Artifact artifact;
        switch (form.getSubject()) {
            case "Book":
                artifact = new Book(form);
                break;
            case "CD":
                artifact = new CD(form);
                break;
            case "Comic":
                artifact = new Comic(form);
                break;
            case "EBook":
                artifact = new EBook(form);
                break;
            case "Magazine":
                artifact = new Magazine(form);
                break;
            case "Periodical":
                artifact = new Periodical(form);
                break;
            case "Video":
                artifact = new Video(form);
                break;
            default:
                throw new IllegalArgumentException("ArtifactForm in editArtifact has an invalid subject: " + form.getSubject());
        }
        return artifact;
    }

    @GetMapping("/error/no_such_user")
    public String noSuchUser(@RequestParam(name = "uname") String username, Model model) {
        model.addAttribute("name", username);
        return "errors/no_such_user";
    }
    @GetMapping("/delete_message")
    public void deleteMessage(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
        messageRepository.deleteById(id);
        response.sendRedirect("/portal?id="+userSession.getUser().getId());
    }
}
