package ru.cwl.testapp.shortener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 01.06.2017.
 */
@Controller
public class HelpController {
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String getHelp(HttpServletRequest request) {
        return "redirect:" + "/index.html";
    }
}
