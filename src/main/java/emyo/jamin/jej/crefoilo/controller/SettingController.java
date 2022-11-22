package emyo.jamin.jej.crefoilo.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.service.ProjectService;

@Controller
public class SettingController {
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/setting/aboutme")
    public String settingAboutMe() {
        return "setting/settingAboutMe";
    }

    @GetMapping(value = "/setting/aboutmet1")
    public String settingAboutMeT1() {
        return "setting/settingAboutMeT1";
    }

    @GetMapping(value = "/setting/aboutmet2")
    public String settingAboutMeT2() {
        return "setting/settingAboutMeT2";
    }

    @GetMapping(value = "/setting/home")
    public String settingHome() {
        return "setting/settingHome";
    }

    @GetMapping(value = "/setting/project/{portfolioid}")
    public String settingProject(@PathVariable Long portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("name", userIdInSession.getSnsName());
        model.addAttribute("projectDtoList", projectService.findProjectList(portfolioid, userIdInSession.getUserId()));
        return "setting/settingProject";
    }

    @GetMapping(value = { "/setting/projectdetail/{projectid}", "/setting/projectdetail" })
    public String settingProjectDetail(@PathVariable Optional<Long> projectid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("name", userIdInSession.getSnsName());
        if (!projectid.isPresent()) {
            model.addAttribute("projectDetail", new ProjectDetailDto());
            return "setting/settingProjectDetail";
        }
        model.addAttribute("projectDetail",
                projectService.findProjectDetail(projectid.get(), userIdInSession.getUserId()));
        return "setting/settingProjectDetail";
    }
}
