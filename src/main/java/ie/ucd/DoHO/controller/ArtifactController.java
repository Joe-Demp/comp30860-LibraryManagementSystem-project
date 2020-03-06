package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ArtifactController {
    @Autowired
    UserSession userSession;
    @Autowired
    ArtifactRepository artifactRepository;

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

    @PostMapping("/create/cd")
    public String addCD(@ModelAttribute CD cd, BindingResult errors,
                        Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.save(cd);
            response.sendRedirect("/artifact?aID=" + cd.getId());
        }
        return "login_main";
    }

    @PostMapping("/create/comic")
    public String addComic(@ModelAttribute Comic comic, BindingResult errors,
                           Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.save(comic);
            response.sendRedirect("/artifact?aID=" + comic.getId());
        }
        return "login_main";
    }

    @PostMapping("/create/ebook")
    public String addEBook(@ModelAttribute EBook eBook, BindingResult errors,
                           Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.save(eBook);
            response.sendRedirect("/artifact?aID=" + eBook.getId());
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

    @PostMapping("/create/periodical")
    public String addPeriodical(@ModelAttribute Periodical periodical, BindingResult errors,
                                Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.save(periodical);
            response.sendRedirect("/artifact?aID=" + periodical.getId());
        }
        return "login_main";
    }

    @PostMapping("/create/video")
    public String addVideo(@ModelAttribute Video video, BindingResult errors,
                           Model model, HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.save(video);
            response.sendRedirect("/artifact?aID=" + video.getId());
        }
        return "login_main";
    }
}
