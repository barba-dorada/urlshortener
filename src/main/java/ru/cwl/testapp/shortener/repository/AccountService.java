package ru.cwl.testapp.shortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cwl.testapp.shortener.model.UserAccount;
import ru.cwl.testapp.shortener.repository.h2.AccountDao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by vadim.tishenko
 * on 21.05.2017 9:47.
 */
@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    public String createAccount(String accountId) {
        if (isAccountExist(accountId)) {
            throw new AccountService.DuplicateAccount();
        }
        String newPass = generatePass();
        UserAccount ua = new UserAccount();
        ua.setLogin(accountId);
        ua.setPassword(newPass);
        accountDao.create(ua);
        return newPass;
    }

    private String generatePass() {
        return "P@SsWorD";
    }

    public boolean isAccountExist(String accountId) {
        return accountDao.getById(accountId)!=null;
    }

    public boolean checkPassword(String accId,String pass){
        UserAccount userAccount = accountDao.getById(accId);
        return userAccount!=null && pass.equals(userAccount.getPassword());
    }

    public static class DuplicateAccount extends RuntimeException {
    }
}
