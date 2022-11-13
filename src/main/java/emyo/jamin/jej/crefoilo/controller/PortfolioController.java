package emyo.jamin.jej.crefoilo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;

@Controller
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioListService;

    @Autowired
    private AboutmeService aboutmeService;

    @GetMapping(value = "/portfolio/{portfolioid}")
    public String portfolio(@PathVariable Long portfolioid, Model model) {
        model.addAttribute("aboutme", aboutmeService.findAboutme(portfolioid));
        return "portfolio";
    }

}
