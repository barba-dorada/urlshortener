package ru.cwl.testapp.shortener.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vadim.tishenko
 * on 20.05.2017 11:35.
 */

/**
 * The server responds with a JSON object, key:value map, where the key
 is the registered URL, and the value is the number of this URL redirects..
 Example:
 {
 Response
 'http://myweb.com/someverylongurl/thensomedirectory/: 10,
 'http://myweb.com/someverylongurl2/thensomedirectory2/: 4,
 'http://myweb.com/someverylongurl3/thensomedirectory3/: 91,
 }
 */
@RestController
public class StatisticController {

    @RequestMapping("/statistic")
    public Map<String,Integer> statistic(){
        Map result = new HashMap<String, Integer>();
        result.put("http://myweb.com/someverylongurl/thensomedirectory/", 10);
        result.put("http://myweb.com/someverylongurl2/thensomedirectory2/", 4);
        result.put("http://myweb.com/someverylongurl3/thensomedirectory3/", 91);
        return result;
    }

}
