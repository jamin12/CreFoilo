package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDto;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;
import emyo.jamin.jej.crefoilo.service.ProjectService;

@RestController
public class testController {

    @Autowired
    private OtherSkillService otherSkillService;

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/test")
    public List<ProjectDto> test() {
        return projectService.findProjectList(3L, "100625979022689944834");
    }
}
