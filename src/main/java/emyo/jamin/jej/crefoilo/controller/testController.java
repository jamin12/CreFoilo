package emyo.jamin.jej.crefoilo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.dto.ContactDto;
import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.dto.HomeViewDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDto;
import emyo.jamin.jej.crefoilo.repository.ProejectRepository;
import emyo.jamin.jej.crefoilo.service.ContactService;
import emyo.jamin.jej.crefoilo.service.LanguageService;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;
import emyo.jamin.jej.crefoilo.service.PortfolioService;
import emyo.jamin.jej.crefoilo.service.ProjectService;

@RestController
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
        contacts.add(new ContactDto(1L, "phone", "새로새로"));
        contacts.add(new ContactDto(1L, "git", "새로 만드렁써 되써용"));
        contacts.add(new ContactDto(1L, "myimg", "새로아하하"));
        contacts.add(new ContactDto(1L, "phone", "이건 새애애애롱"));
        return contactService.createAndUpdateContact(1L, "100625979022689944834", contacts);
    }
}
