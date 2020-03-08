package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.*;
import ie.ucd.DoHO.model.Contracts.Loan;
import ie.ucd.DoHO.model.Contracts.LoanRepository;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private LoanRepository loanRepository;
    @Autowired
    private ArtifactRepository artifactRepository;
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
            return "librarian_portal";
        }
        return "login_main";
    }

    @PostMapping("/artifact/loan")
    public String loan(@RequestParam("username") String username,
                       @RequestParam("artifact") Artifact artifact,
                       HttpServletResponse response) throws IOException {
        if (artifact.isAvailable()) {
            Optional<User> optionalUser = userRepository.findByUsername(username);

            if (optionalUser.isPresent()) {
                User loaner = optionalUser.get();

                // Check reservee is not an admin
                if (loaner.isAdmin()) {
                    logger.info("user " + username + " is an admin");
                    return "/errors/librarian_reservation";
                } else if (loanedAlready(loaner, artifact)) {
                    logger.info("user " + username + " has already loaned " + artifact.getTitle());
                    return "/errors/reserved_already";
                }

                loanForUser(loaner, artifact, response);
            } else {
                logger.info("user " + username + " does not exist");
                response.sendRedirect("/error/user_not_found?name=" + username);
            }
        }
        return "errors/artifact_unavailable";
    }

    private boolean loanedAlready(User user, Artifact artifact) {
        List<Loan> loans = loanRepository.findByUserAndArtifact(user, artifact);
        for (Loan loan : loans) {
            if (loan.isActive()) {
                return true;
            }
        }
        return false;
    }

    private void loanForUser(User user, Artifact artifact, HttpServletResponse response)
            throws IOException {
        Date inTwoWeeks = Date.from(Instant.now().plus(14, ChronoUnit.DAYS));

        Loan loan = new Loan(user, artifact, inTwoWeeks);
        loanRepository.save(loan);
        response.sendRedirect("/user_profile?id=" + user.getId());
    }

    @PostMapping("/artifact/receive")
    public void receive(@RequestParam("username") String username,
                        @RequestParam("artifact") Artifact artifact,
                        HttpServletResponse response) throws IOException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User loaner = optionalUser.get();
            // Check reservee is not an admin
            if (loaner.isAdmin()) {
                logger.info("user " + username + " is an admin");
                response.sendRedirect("/error/invalid_receive?name=" + username);
            }

            List<Loan> loans = loanRepository.findByUserAndArtifact(loaner, artifact);
            Loan target = loans.stream().filter(Loan::isActive).findFirst().orElse(null);
            if (target != null) {
                target.doReturn();
                loanRepository.save(target);
                response.sendRedirect("/user_profile?id=" + loaner.getId());
            } else {
                response.sendRedirect("/error/loan_not_found");
            }
        } else {
            logger.info("user " + username + " does not exist");
            response.sendRedirect("/error/user_not_found?name=" + username);
        }
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
}
