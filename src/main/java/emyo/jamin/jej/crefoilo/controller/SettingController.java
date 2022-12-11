package emyo.jamin.jej.crefoilo.controller;

import java.util.List;
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

import emyo.jamin.jej.crefoilo.dto.AboutmeDto;
import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.dto.HomeViewDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.security.SessionUser;
import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.LanguageService;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;
import emyo.jamin.jej.crefoilo.service.ProjectService;
import emyo.jamin.jej.crefoilo.dto.AboutmeDto;
import emyo.jamin.jej.crefoilo.dto.LanguageSettingDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillDto;
import emyo.jamin.jej.crefoilo.dto.PortfolioHomeDto;

/**
 * 세팅 페이지 컨트롤러
 */
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

    @Autowired
    private OtherSkillService otherSkillService;

    @Autowired
    private PortfolioService portfolioService;

    /**
     * home 선택 페이지
     * 
     * @param portfolioid
     * @param model
     * @return
     */
    @GetMapping(value = { "/setting/home", "/setting/home/{portfolioid}" })
    public String settingHome(@PathVariable Optional<Long> portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        // 포트폴리오 아이디가 있을 때
        if (portfolioid.isPresent()) {
            model.addAttribute("portfolioHome",
                    portfolioService.findPortfolioHome(portfolioid.get(), userIdInSession.getUserId()));
            model.addAttribute("portfolioid", portfolioid.get());
            return "setting/settingHome";
        }
        model.addAttribute("portfolioHome", new HomeViewDto());
        model.addAttribute("portfolioid", null);

        return "setting/settingHome";
    }

    /**
     * home 생성 수정
     * 
     * @param portfolioHomeDto 포트폴리오 홈 데이터
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/setting/home")
    public Long createHome(@RequestBody PortfolioHomeDto portfolioHomeDto) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        Portfolio Cuportfolio = portfolioService.CUPortfolioHome(portfolioHomeDto, userIdInSession.getUserId());
        return Cuportfolio.getPortfolioId();
    }

    /**
     * aboutme 타입 선택 페이지
     * 
     * @param portfolioid 포트폴리오 아이디
     * @param model
     * @return
     */
    @GetMapping(value = "/setting/aboutme/{portfolioid}")
    public String settingAboutMe(@PathVariable Long portfolioid, Model model) {
        model.addAttribute("portfolioid", portfolioid);
        return "setting/settingAboutMe";
    }

    /**
     * 자신의 aboutMe 조회
     * 
     * @param portfolioid 포트폴리오 아이디
     * @param type        aboutme 타입
     * @param model
     * @return
     */
    @GetMapping(value = "/setting/aboutme/{portfolioid}/{type}")
    public String settingAboutMeT(@PathVariable Long portfolioid, @PathVariable Integer type, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        AboutmeDto findedAboutme = aboutmeService.findAboutme(portfolioid, userIdInSession.getUserId());
        // 새로 만들 때
        if (findedAboutme == null) {
            findedAboutme = new AboutmeDto();
        }
        // 기존에 있을 때
        model.addAttribute("portfolioid", portfolioid);
        model.addAttribute("aboutMe", findedAboutme);
        if (type == 1) {
            return "setting/settingAboutMeT1";
        }
        return "setting/settingAboutMeT2";
    }

    /**
     * aboutme 수정 저장
     * 
     * @param portfolioid 포트폴리오 아이디
     * @param aboutmeDto  전송받을 aboutme 데이터
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/setting/aboutme/{portfolioid}")
    public String createAboutMe(@PathVariable Long portfolioid, @RequestBody AboutmeDto aboutmeDto) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        aboutmeService.createAboutMe(portfolioid, userIdInSession.getUserId(), aboutmeDto);
        // 리턴하는 값 : js에 있는ㄷ 데이터
        return null;
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
        model.addAttribute("LanguageSkillList",
                laguageService.findLanguageList(portfolioid, userIdInSession.getUserId()));
        model.addAttribute("portfolioid", portfolioid);
        return "setting/settingLanguageSkill";
    }

    /**
     * 언어기술 페이지 생성 수정 삭제 작업
     * 
     * @param languageSettingDtos
     * @param portfolioid
     * @param model
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/setting/language/{portfolioid}")
    public String createLanguageSkill(@RequestBody List<LanguageSettingDto> languageSettingDtos,
            @PathVariable Long portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        laguageService.CUDLanguage(languageSettingDtos, portfolioid, userIdInSession.getUserId());
        return "/setting/other/" + portfolioid.toString();
    }

    /**
     * Other Skill 페이지 조회
     * 
     * @param portfolioid
     * @param model
     * @return
     */
    @GetMapping(value = "/setting/other/{portfolioid}")
    public String settingOtherSkill(@PathVariable Long portfolioid, Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("portfolioid", portfolioid);
        model.addAttribute("otherskilllist",
                otherSkillService.findOtherSkillList(portfolioid, userIdInSession.getUserId()));
        return "setting/settingOtherSkill";
    }

    /**
     * Other Skill 생성 수정 삭제
     * 
     * @param portfolioid
     * @param model
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/setting/other/{portfolioid}")
    public String CUDOtherSkill(@PathVariable Long portfolioid, @RequestBody List<OtherSkillDto> otherSkillDtos,
            Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        otherSkillService.CUDOtherSkill(otherSkillDtos, portfolioid, userIdInSession.getUserId());
        return "/setting/project/" + portfolioid.toString();
    }

    /**
     * base Other Skill 생성
     * 
     * @param portfolioid
     * @param otherSkillDto
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/setting/baseother/{portfolioid}")
    public OtherSkillDto createBaseOtherSkill(@PathVariable Long portfolioid,
            @RequestBody OtherSkillDto otherSkillDto) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        return otherSkillService.createBaseOtherSkill(otherSkillDto, portfolioid, userIdInSession.getUserId());
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
        if (!projectid.isPresent()) {
            projectService.createProject(projectDetailDto.getPortfolioId(), userIdInSession.getUserId(),
                    projectDetailDto);
            return "/setting/project/" + projectDetailDto.getPortfolioId().toString();
        }
        projectService.updateProject(projectid.get(), userIdInSession.getUserId(), projectDetailDto);
        return "/setting/project/" + projectDetailDto.getPortfolioId().toString();
    }

}
