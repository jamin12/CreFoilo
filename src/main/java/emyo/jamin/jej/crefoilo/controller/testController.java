package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.service.LanguageService;

@RestController
public class testController {
    @Autowired
    private LanguageService languageService;

    @GetMapping(value = "/test")
    public List<FindLanguageDto> test() {
        return languageService.findLanguage(1L);
    }
}
