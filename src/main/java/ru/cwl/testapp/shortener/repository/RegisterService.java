package ru.cwl.testapp.shortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cwl.testapp.shortener.model.Urls;
import ru.cwl.testapp.shortener.repository.h2.UrlsDao;

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
    @Autowired
    UrlsDao urlsDao;

 /*   Map<String,Pair<String,Integer>> redirectMap=new HashMap<>();
    Map<String,Set<String>> userMap=new HashMap<>();
    Map<String,Long> counterMap=new ConcurrentHashMap<>();*/

    AtomicLong idGenerator =new AtomicLong();
    public String add(String userId, String longUrl, int redirectType) {

        long id = idGenerator.incrementAndGet();
        String shortUrl = Long.toString(id, Character.MAX_RADIX);
       /* redirectMap.put(shortUrl,new Pair<>(longUrl,redirectType));
        Set<String> urlSet = userMap.getOrDefault(userId, new HashSet<>());
        urlSet.add(longUrl);
        counterMap.put(longUrl,0L);*/

        Urls urls = new Urls();
        urls.setAccountName(userId);
        urls.setLongUrl(longUrl);
        urls.setShortUrl(shortUrl);
        urls.setRedirectType(redirectType);
        urls.setReqCount(0);

        urlsDao.create(urls);
        return shortUrl;
    }
    public Pair<String, Integer> getLongUrl(String shortUrl){
        Urls urls= urlsDao.getByShortUrl(shortUrl);
        Pair<String, Integer> pair = new Pair<>(urls.getLongUrl(),urls.getRedirectType());
        return pair;
    }

    public static class Pair<T,K>{
        T first;
        K second;
        public Pair(T t, K k){
            first=t;
            second =k;
        }
        public T getFirst() {
            return first;
        }

        public K getSecond() {
            return second;
        }
    }
}
