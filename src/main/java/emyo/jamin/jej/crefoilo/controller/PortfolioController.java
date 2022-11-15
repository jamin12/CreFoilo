package emyo.jamin.jej.crefoilo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;

@Controller
public class PortfolioController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private AboutmeService aboutmeService;

    @GetMapping(value = "/portfolio/{portfolioid}")
    public String portfolio(@PathVariable Long portfolioid, Model model) {
        model.addAttribute("aboutme", aboutmeService.findAboutme(portfolioid));
        return "portfolio";
    }

    @GetMapping(value = "/portfolio/d/{portfolioid}")
    public String deletePortfolio(@PathVariable Long portfolioid) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        if (userIdInSession == null) {
            // TODO: 로그인을 하세요
            return null;
        }

        portfolioService.deletePortfolio(portfolioid, userIdInSession.getUserId());
        return "우와";
    }

}
