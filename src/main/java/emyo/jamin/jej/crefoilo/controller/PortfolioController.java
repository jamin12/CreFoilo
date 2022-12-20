package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import emyo.jamin.jej.crefoilo.dto.HomeViewDto;
import emyo.jamin.jej.crefoilo.dto.PortfolioDto;
import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.ContactService;
import emyo.jamin.jej.crefoilo.service.LanguageService;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;
import emyo.jamin.jej.crefoilo.service.ProjectService;
import emyo.jamin.jej.crefoilo.service.UserService;
import emyo.jamin.jej.crefoilo.utils.CustomException;
import emyo.jamin.jej.crefoilo.utils.ErrorCode;

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

    @Autowired
    private UserService userService;

    @GetMapping(value = "/portfolio/{portfolioid}")
    public String portfolio(@PathVariable Long portfolioid, Model model) {
        HomeViewDto findedPortfolio = portfolioService.findPortfolioHome(portfolioid);
        String userName = userService.findUser(findedPortfolio.getUserId());
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        Boolean myfolio = false;

        if (userIdInSession != null) {
            if (userName.equals(userIdInSession.getSnsName())) {
                myfolio = true;
            }
        }
        model.addAttribute("portfolio", findedPortfolio);
        model.addAttribute("aboutme", aboutmeService.findAboutme(portfolioid));
        model.addAttribute("languageskill", languageskill.findLanguage(portfolioid));
        model.addAttribute("otherskill", otherSkillService.findOtherSkillList(portfolioid));
        model.addAttribute("projectDetail", projectService.findProjectAll(portfolioid));
        model.addAttribute("contact", contactService.findContact(portfolioid));
        model.addAttribute("username", userName);
        model.addAttribute("myfolio", myfolio);
        model.addAttribute("portfolioid", portfolioid);

        return "portfolio";
    }

    /**
     * 포트폴리오 삭제
     * 
     * @param portfolioid
     * @return
     */
    @ResponseBody
    @DeleteMapping(value = "/myportfolio/d/{portfolioid}")
    public String deletePortfolio(@PathVariable Long portfolioid) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
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
