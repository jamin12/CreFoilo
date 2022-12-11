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
import org.thymeleaf.util.StringUtils;

import emyo.jamin.jej.crefoilo.dto.PortfolioDto;
import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.ContactService;
import emyo.jamin.jej.crefoilo.service.LanguageService;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;
import emyo.jamin.jej.crefoilo.service.ProjectService;

@Controller
public class PortfolioController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private PortfolioService portfolioService;

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
    public String portfolio(@PathVariable Long portfolioid, Model model) {
        model.addAttribute("portfolio", portfolioService.findPortfolioHome(portfolioid));
        model.addAttribute("aboutme", aboutmeService.findAboutme(portfolioid));
        model.addAttribute("languageskill", languageskill.findLanguage(portfolioid));
        model.addAttribute("otherskill", otherSkillService.findOtherSkillList(portfolioid));
        model.addAttribute("projectDetail", projectService.findProjectAll(portfolioid));
        model.addAttribute("contact", contactService.findContact(portfolioid));

        return "portfolio";
    }

    @GetMapping(value = "/portfolio/d/{portfolioid}")
    public String deletePortfolio(@PathVariable Long portfolioid) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        if (userIdInSession == null) {
            return "redirect:/";
        }
        portfolioService.deletePortfolio(portfolioid, userIdInSession.getUserId());
        return "우와";
    }

    /**
     * 포트폴리오 리스트 조회
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/myportfolio/list")
    public String portfolioList(Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        List<PortfolioDto> findedportFolioList = portfolioService.findPortfolioList(userIdInSession.getUserId());
        model.addAttribute("portfolioList", findedportFolioList);
        return "portfoliloList";
    }

}
