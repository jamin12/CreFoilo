package emyo.jamin.jej.crefoilo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emyo.jamin.jej.crefoilo.service.LanguageService;
import lombok.RequiredArgsConstructor;

@RestController
public class testController {
    @Autowired
    private LanguageService languageService;

    @GetMapping(value = "/test")
    public String test() {
        languageService.findLanguage(1L);
        return "test";
    }
}
