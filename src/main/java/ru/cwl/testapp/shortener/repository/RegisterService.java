package ru.cwl.testapp.shortener.repository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by vadim.tishenko
 * on 21.05.2017 12:43.
 */
@Service
public class RegisterService {
    Map<String,String> redirectMap=new HashMap<>();
    Map<String,Set<String>> userMap=new HashMap<>();
    Map<String,Long> counterMap=new ConcurrentHashMap<>();

    AtomicLong idGenerator =new AtomicLong();
    public String add(String userId, String longUrl, int redirectType) {
        long id = idGenerator.incrementAndGet();
        String shortUrl = Long.toString(id, Character.MAX_RADIX);
        redirectMap.put(shortUrl,longUrl);
        Set<String> urlSet = userMap.getOrDefault(userId, new HashSet<>());
        urlSet.add(longUrl);
        counterMap.put(longUrl,0L);
        return shortUrl;
    }
    public String getLongUrl(String shortUrl){
        return redirectMap.get(shortUrl);
    }
}
