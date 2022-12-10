package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.dto.OtherSkillBaseList;
import emyo.jamin.jej.crefoilo.dto.OtherSkillDto;
import emyo.jamin.jej.crefoilo.service.OtherSkillService;

@RestController
public class testsetset {

    @Autowired
    private OtherSkillService otherSkillService;

    @GetMapping(value = "/test22")
    public List<OtherSkillBaseList> test22() {
        return otherSkillService.findOtherSkillList(1L, "100625979022689944834");
    }
}
