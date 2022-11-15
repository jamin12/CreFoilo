package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;

@RestController
public class PortfolioController {

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
    public String deletePortfolio(HttpServletRequest request, @PathVariable Long portfolioid, String userId) {
        HttpSession session = request.getSession();

        String name = (String) session.getAttribute("userId");
        System.out.println(name);
        portfolioService.deletePortfolio(portfolioid, name);
        return "우와";
    }

}
