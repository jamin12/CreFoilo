package emyo.jamin.jej.crefoilo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.dto.AboutmeDto;
import emyo.jamin.jej.crefoilo.dto.ContactDto;
import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.dto.HomeViewDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDto;
import emyo.jamin.jej.crefoilo.entity.AboutMe;
import emyo.jamin.jej.crefoilo.repository.ProejectRepository;
import emyo.jamin.jej.crefoilo.service.AboutmeService;
import emyo.jamin.jej.crefoilo.service.ContactService;
import emyo.jamin.jej.crefoilo.service.LanguageService;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;
import emyo.jamin.jej.crefoilo.service.ProjectService;

@Controller
public class testController {

    @Autowired
    private OtherSkillService otherSkillService;
    @Autowired
    private ProejectRepository proejectRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private AboutmeService aboutmeService;

    @GetMapping(value = "/test")
    public ProjectDetailDto test() {
        return projectService.findProjectDetail(1L, "100625979022689944834");
        // return projectService.findProjectList(3L, "100625979022689944834");
    }

    @GetMapping(value = "/test1")
    public List<ContactDto> test1() {
        return contactService.findContact(1L);
        // return projectService.findProjectList(3L, "100625979022689944834");
    }

    @GetMapping(value = "/test2")
    public HomeViewDto test2() {
        return portfolioService.findPortfolioHome(1L);
    }

    @GetMapping(value = "/test3")
    public List<FindLanguageDto> test3() {
        return languageService.findLanguage(1L);
    }

    @GetMapping(value = "/test4")
    public String test4() {
        List<ContactDto> contacts = new ArrayList<ContactDto>();
        contacts.add(new ContactDto("phone", "새로새로"));
        contacts.add(new ContactDto("git", "새로 만드렁써 되써용"));
        contacts.add(new ContactDto("myimg", "새로아하하"));
        contacts.add(new ContactDto("phone", "이건 새애애애롱"));
        return contactService.CUDContact(1L, "100625979022689944834", contacts);
    }

    @GetMapping(value = "/test6")
    public List<OtherSkillListDto> test6() {
        return otherSkillService.findOtherSkillList(4L, "110335477341106437565");
    }

    @GetMapping(value = "/test7")
    public List<OtherSkillListDto> test7() {
        List<OtherSkillDto> otherskillDtos = new ArrayList<>();
        new OtherSkillDto();
        otherskillDtos.add(OtherSkillDto.builder().otherSkillID(23L).baseOtherSkillId(18L)
                .otherSkillName("업데이트으으으으으으 스킬 테스트").build());
        otherskillDtos.add(OtherSkillDto.builder().otherSkillID(24L).otherSkillName("업데이라넹 헤해").build());
        otherskillDtos.add(OtherSkillDto.builder().otherSkillID(18L).baseOtherSkillId(24L)
                .otherSkillName("업데이트 스킬이에용 헤해").build());
        return otherSkillService.CUDOtherSkill(otherskillDtos, 1L, "100625979022689944834");
    }

    @GetMapping(value = "/test5")
    public AboutmeDto test5() {
        return aboutmeService.findAboutme(2L, "100625979022689944834");
    }

    @GetMapping(value = "/test8")
    public String test8() {
        AboutmeDto aboutmeDto = AboutmeDto.builder()
                .portfolioId(1L)
                .aboutMeId(2L)
                .aboutMeBgColor("#12555")
                .aboutMeType(1)
                .aboutMeTitle1("ddd")
                .aboutMeTitle2("dddd")
                .aboutMeTitle3("ddddd")
                .aboutMeIntro1("ddddddd")
                .aboutMeIntro2("ffffffff")
                .aboutMeImg("44444444")
                .build();

        return aboutmeService.createAboutMe(1L, "100625979022689944834", aboutmeDto);
    }

    @GetMapping(value = "/test9")
    public String test9() {
        return "setting/settingLanguageSkill";
    }

}
