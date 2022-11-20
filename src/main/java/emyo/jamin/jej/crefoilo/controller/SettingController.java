package emyo.jamin.jej.crefoilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
    @GetMapping(value = "/setting/aboutme")
    public String settingAboutMe() {
        return "setting/settingAboutMe";
    }

    @GetMapping(value = "/setting/aboutmet1")
    public String settingAboutMeT1() {
        return "setting/settingAboutMeT1";
    }

    @GetMapping(value = "/setting/aboutmet2")
    public String settingAboutMeT2() {
        return "setting/settingAboutMeT2";
    }

    @GetMapping(value = "/setting/home")
    public String settingHome() {
        return "setting/settingHome";
    }

    @GetMapping(value = "/setting/project")
    public String settingProject() {
        return "setting/settingProject";
    }

    @GetMapping(value = "/setting/projectdetail")
    public String settingProjectDetail() {
        return "setting/settingProjectDetail";
    }
}
