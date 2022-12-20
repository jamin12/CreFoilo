package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController extends BasicErrorController {

    public ErrorController(ErrorAttributes errorAttributes,
            ServerProperties serverProperties,
            List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus hs = getStatus(request);
        ModelAndView mv = new ModelAndView("error/errorpage.html");
        if(hs == HttpStatus.NOT_FOUND){
            mv.setViewName("error/error404.html");
        }
        return mv;
    }
}