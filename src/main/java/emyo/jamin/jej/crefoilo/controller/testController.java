package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;

@RestController
public class testController {

    @Autowired
    private OtherSkillService otherSkillService;

    @GetMapping(value = "/test")
    public List<OtherSkillListDto> test() {
        return otherSkillService.findOtherSkillList(1L);
    }
}
