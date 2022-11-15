package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.ContactService;
import emyo.jamin.jej.crefoilo.service.LanguageService;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;
import emyo.jamin.jej.crefoilo.service.ProjectService;

@Controller
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioListService;

    @Autowired
    private AboutmeService aboutmeService;

    @Autowired
    private LanguageService languageskill;

    @Autowired
    private OtherSkillService otherSkillService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/portfolio/{portfolioid}")
    public String portfolio(@PathVariable Long portfolioid, Model model, String userId) {
        model.addAttribute("portfolio", portfolioListService.findPortfolioHome(portfolioid));
        model.addAttribute("aboutme", aboutmeService.findAboutme(portfolioid));
        model.addAttribute("languageskill", languageskill.findLanguage(portfolioid));
        model.addAttribute("otherskill", otherSkillService.findOtherSkillList(portfolioid));
        model.addAttribute("projectDetail", projectService.findProjectAll(portfolioid));
        model.addAttribute("contact", contactService.findContact(portfolioid));


        return "portfolio";
    }

}
