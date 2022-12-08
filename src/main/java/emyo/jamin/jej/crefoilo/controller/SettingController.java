package emyo.jamin.jej.crefoilo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import emyo.jamin.jej.crefoilo.dto.AboutmeDto;
import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.LanguageService;
import emyo.jamin.jej.crefoilo.service.ProjectService;

@Controller
public class SettingController {
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AboutmeService aboutmeService;

    @Autowired
    private LanguageService laguageService;

    @GetMapping(value = "/setting/aboutme")
    public String settingAboutMe() {
        return "setting/settingAboutMe";
    }

    @GetMapping(value = "/setting/aboutmet1/{portfolioid}")
    public String settingAboutMeT1(@PathVariable Long portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        AboutmeDto findedAboutme = aboutmeService.findAboutme(portfolioid, userIdInSession.getUserId());
        model.addAttribute("name", userIdInSession.getSnsName());
        model.addAttribute("aboutMe", findedAboutme);

        return "setting/settingAboutMeT1";
    }

    @GetMapping(value = "/setting/aboutmet2/{portfolioid}")
    public String settingAboutMeT2(@PathVariable Long portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        AboutmeDto findedAboutme = aboutmeService.findAboutme(portfolioid, userIdInSession.getUserId());
        model.addAttribute("name", userIdInSession.getSnsName());
        model.addAttribute("aboutMe", findedAboutme);

        return "setting/settingAboutMeT2";
    }

    @GetMapping(value = "/setting/home")
    public String settingHome() {
        return "setting/settingHome";
    }

    /**
     * 언어기술 페이지 조회
     * 
     * @param portfolioid
     * @param model
     * @return
     */

    @GetMapping(value = "/setting/language/{portfolioid}")
    public String settingLanguageSkill(@PathVariable Long portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("name", userIdInSession.getSnsName());
        model.addAttribute("LanguageSkillList", laguageService.findLanguage(portfolioid));

        return "setting/settingLanguageSkill";
    }   

    /**
     * 포트폴리오의 프로젝트리스트 페이지 조회
     * 
     * @param portfolioid 포트폴리오 아이디
     * @param model       모델
     * @return
     */
    @GetMapping(value = "/setting/project/{portfolioid}")
    public String settingProject(@PathVariable Long portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("name", userIdInSession.getSnsName());
        model.addAttribute("projectDtoList", projectService.findProjectList(portfolioid, userIdInSession.getUserId()));
        return "setting/settingProject";
    }

    /**
     * 프로젝트 세부 정보 조회
     * 
     * @param projectid 프로젝트 아이디
     * @param model     모델
     * @return
     */
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

    /**
     * 프로젝트 세부정보 저장 수정
     * 
     * @param projectDetailDto 프로젝트 세부정보 DTO
     * @param projectid        프로젝트 아이디
     * @return
     */
    @ResponseBody
    @PostMapping(value = { "/setting/projectdetail", "/setting/projectdetail/{projectid}" })
    public String settingProjectDetailPost(@RequestBody ProjectDetailDto projectDetailDto,
            @PathVariable Optional<Long> projectid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("name", userIdInSession.getSnsName());
        if (!projectid.isPresent()) {
            projectService.createProject(projectDetailDto.getPortfolioId(), userIdInSession.getUserId(),
                    projectDetailDto);
            return "/setting/project/" + projectDetailDto.getPortfolioId().toString();
        }
        projectService.updateProject(projectid.get(), userIdInSession.getUserId(), projectDetailDto);
        return "/setting/project/" + projectDetailDto.getPortfolioId().toString();
    }

}
