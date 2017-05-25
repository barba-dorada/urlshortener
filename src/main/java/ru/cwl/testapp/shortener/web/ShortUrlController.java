package ru.cwl.testapp.shortener.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vadim.tishenko
 * on 25.05.2017 21:25.
 */
@Controller
public class ShortUrlController {

    //redirect to external URL
    @RequestMapping(value = "/forwardExample", method = RequestMethod.GET)
    public String redirectExample(HttpServletRequest request) {
        //request.getScheme() - if you don't know where was the request sent: http, https, ftp..
        // 302 код
        return "redirect:" + request.getScheme() + "://javastudy.ru";
    }

    @RequestMapping(value = "/wells/{apiValue}", method = RequestMethod.GET)
    public ResponseEntity<?> fetchWellData(@PathVariable String apiValue) {
        if (apiValue.equals("333")) {
            return new ResponseEntity<>("ongardWell333", HttpStatus.OK);
        } else {
        //ok
            String errorMessage;
            errorMessage = "http://javastudy.ru/" + apiValue;
            return ResponseEntity
                    .status(301)
                    .header("Location", errorMessage)
                    .header("Connection", "close")
                    .body("3333");
        }
    }


    @RequestMapping(value = {"/devices"}, method = RequestMethod.GET)
    private void initGetForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
//работает
        String newUrl = request.getContextPath() + "http://javastudy.ru";
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", newUrl);
        response.setHeader("Connection", "close");

    }

    @RequestMapping(value = {"/dev"}, method = RequestMethod.GET)
    private void initGetForm2(HttpServletRequest request, HttpServletResponse response) throws Exception {
// работает
        String newUrl = request.getContextPath() + "http://javastudy.ru";
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", newUrl);
        response.setHeader("Connection", "close");

    }


}
