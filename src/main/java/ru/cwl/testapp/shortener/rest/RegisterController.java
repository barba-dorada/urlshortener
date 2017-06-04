package ru.cwl.testapp.shortener.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cwl.testapp.shortener.repository.AccountService;
import ru.cwl.testapp.shortener.repository.RegisterService;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/**
 * Created by vadim.tishenko
 * on 20.05.2017 11:34.
 */
@RestController
public class RegisterController {
    private static final String UNAUTHORIZED = "UNAUTHORIZED";
    private RegisterService registerService;
    private AccountService accountService;

    public RegisterController(RegisterService registerService, AccountService accountService) {
        this.registerService = registerService;
        this.accountService = accountService;
    }

    @RequestMapping("/register")
    public ResponseEntity<?> registerUrl(@RequestBody RequestRegister requestRegister, @RequestHeader("Authorization") String authToken) {
        String userId = getUserId(authToken);
        if(userId!=UNAUTHORIZED) {
            String longUrl = requestRegister.url;
            int redirectType = 302;
            String shortUrl = registerService.add(userId, longUrl, redirectType);
            RegisterResult result = new RegisterResult();
            result.shortUrl = shortUrl;
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            String errorMessage;
            errorMessage = " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
    }

    String getUserId(String authToken){
        String authHeader = authToken;
        System.out.println(authToken);
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();

                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
                        //LOG.debug("Credentials: " + credentials);
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            String _username = credentials.substring(0, p).trim();
                            String _password = credentials.substring(p + 1).trim();
                            System.out.printf("u:%s, p:%s\n",_username,_password);
                            if(accountService.checkPassword(_username,_password)){
                                return  _username;  //unauthorized(response, "Bad credentials");
                            }
                        } else {
                            return UNAUTHORIZED;
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
                }
            }
        }
        return UNAUTHORIZED;
    }

    public LongUrlResponse getLongUrl(String shortUrl) {
        RegisterService.Pair<String, Integer> res = registerService.getLongUrl(shortUrl);
        if(res==null){
            throw new URLNotFoundException();
        }
        LongUrlResponse r = new LongUrlResponse();
        r.longUrl=res.getFirst();
        r.redirectType=res.getSecond();
        return r;
    }

    public class LongUrlResponse {
        String longUrl;
        int redirectType;
    }
}
