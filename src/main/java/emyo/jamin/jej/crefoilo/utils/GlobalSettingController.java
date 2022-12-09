package emyo.jamin.jej.crefoilo.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import emyo.jamin.jej.crefoilo.controller.SettingController;
import emyo.jamin.jej.crefoilo.security.SessionUser;

@ControllerAdvice(assignableTypes = { SettingController.class })
public class GlobalSettingController {

    @Autowired
    private HttpSession httpSession;

    /**
     * setting 페이지에 들어갈 때 model에 userName 전부 넣어주는 기능
     * 
     * @param model
     */
    @ModelAttribute
    public void settingUserName(Model model) {
        SessionUser userIdInSession = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("name", userIdInSession.getSnsName());
    }

}
