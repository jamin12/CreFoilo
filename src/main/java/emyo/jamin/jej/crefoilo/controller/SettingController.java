package emyo.jamin.jej.crefoilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
    @GetMapping(value = "/setting/aboutme")
    public String settingAboutMe() {
        return "setting/settingAboutMe";
    }

    // @GetMapping(value = "/setting/aboutme")
    // public String settingHome() {
    // return "setting/settingAboutMe";
    // }

    // @GetMapping(value = "/setting/aboutme")
    // public String settingProject() {
    // return "setting/settingAboutMe";
    // }

    @GetMapping(value = "/setting/settingprojectdetail")
    public String settingProjectDetail() {
        return "setting/settingProjectDetail";
    }
}
