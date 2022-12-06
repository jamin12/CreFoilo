package emyo.jamin.jej.crefoilo.controller;

import java.util.Map;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import antlr.collections.List;
import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.service.ProjectService;
import emyo.jamin.jej.crefoilo.dto.LanguageSettingDto;


/**
 * 세팅 페이지 컨트롤러
 */
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

    @GetMapping(value = "/setting/language")
    public String settingLanguage() {
        return "setting/settingLanguageSkill";
    }

    @PostMapping(value = "/setting/lang/{portfolioid}")
    public String settingLanguageT1(
        @RequestBody List<LanguageSettingDto> languageSettingDtoList,
        @PathVariable Long portfolioid) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        return languageService.CUDLanguage(languageSettingDtoList, portfolioid, userIdInSession.getUserId());
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
        model.addAttribute("portfolioid", portfolioid.toString());
        return "setting/settingProject";
    }

    /**
     * 프로젝트 삭제
     * 
     * @param projectid 삭제할 프로젝트 아이디
     * @return
     */
    @ResponseBody
    @DeleteMapping(value = "/setting/project/{projectid}")
    public String settingProject(@PathVariable Long projectid) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        projectService.deleteProject(projectid, userIdInSession.getUserId());
        return null;
    }

    /**
     * 프로젝트 세부 정보 조회
     * 
     * @param projectid 프로젝트 아이디
     * @param model     모델
     * @return
     */
    @GetMapping(value = { "/setting/projectdetail/{portfolioid}/{projectid}", "/setting/projectdetail/{portfolioid}" })
    public String settingProjectDetail(@PathVariable Long portfolioid, @PathVariable Optional<Long> projectid,
            Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("name", userIdInSession.getSnsName());
        if (!projectid.isPresent()) {
            model.addAttribute("projectDetail", ProjectDetailDto.builder().portfolioId(portfolioid).build());
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
    @PostMapping(value = { "/setting/projectdetail/{portfolioid}", "/setting/projectdetail/{portfolioid}/{projectid}" })
    public String settingProjectDetailPost(@RequestBody ProjectDetailDto projectDetailDto,
            @PathVariable Long portfolioid, @PathVariable Optional<Long> projectid, Model model) {
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
