package ie.ucd.DoHO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error(@RequestParam("title") String title,
                        @RequestParam("msg") String message,
                        Model model, HttpServletResponse response) {
        model.addAttribute("message", message);
        return "errors/error";
    }

    @GetMapping("/error/user_not_found")
    public void userNotFound(@RequestParam("name") String name, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(
                "/error?title='User not found'&msg='No such user:" + name + "'"
        );
    }
}
