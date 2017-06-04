package ru.cwl.testapp.shortener.repository.h2;

import org.springframework.stereotype.Repository;
import ru.cwl.testapp.shortener.model.ApiRequest;
import ru.cwl.testapp.shortener.model.UserAccount;
import ru.cwl.testapp.shortener.repository.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by vadim.tishenko
 * on 04.06.2017 20:34.
 */
@Repository
public class AccountDao {
    @PersistenceContext
    private EntityManager entityManager;

/*    public String createAccount(String accountId) {
        if (isAccountExist(accountId)) {
            throw new AccountService.DuplicateAccount();
        }
        String newPass = generatePass();
        UserAccount ua = new UserAccount();
        ua.setLogin(accountId);
        ua.setPassword(newPass);
        entityManager.persist(ua);
        return newPass;
    }*/



 /*   public boolean isAccountExist(String accountId) {
        UserAccount res = entityManager.find(UserAccount.class, accountId);
        return res != null;
    }

    public boolean checkPassword(String accountId, String pass) {
        UserAccount account = entityManager.find(UserAccount.class, accountId);
        return pass.equals(account.getPassword());
    }*/

    public UserAccount getById(String accountId){
        UserAccount account = entityManager.find(UserAccount.class, accountId);
        return account;
    }

    public void create(UserAccount userAccount) {
        entityManager.persist(userAccount);
    }
}

