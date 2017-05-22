package ru.cwl.testapp.shortener.repository;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by vadim.tishenko
 * on 21.05.2017 9:47.
 */
@Service
public class AccountService {
    private Map<String,String> accounts=new ConcurrentHashMap<>();

    public String createAccount(String accountId) {
        String newPass=generatePass();
        accounts.put(accountId,newPass);
        return newPass;
    }

    private String generatePass() {
        return "P@SsWorD";
    }

    public boolean isAccountExist(String accountId) {
        return accounts.containsKey(accountId);
    }

    public boolean checkPassword(String accId,String pass){
        return pass.equals(accounts.get(accId));
    }
}
