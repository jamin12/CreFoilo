package emyo.jamin.jej.crefoilo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.utils.CustomException;
import emyo.jamin.jej.crefoilo.utils.ErrorCode;
import emyo.jamin.jej.crefoilo.utils.ErrorResponse;

@Controller
public class IndexController {
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/test")
    public String callback(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getSnsName());
            System.out.println(user.getUserEmail());
        }
        return "callback";
    }
}
