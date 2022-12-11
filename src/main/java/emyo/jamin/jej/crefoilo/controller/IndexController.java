package emyo.jamin.jej.crefoilo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import emyo.jamin.jej.crefoilo.security.SessionUser;

@Controller
public class IndexController {
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        if (userIdInSession != null) {
            return "redirect:myportfolio/list";
        }
        return "login";
    }

}
