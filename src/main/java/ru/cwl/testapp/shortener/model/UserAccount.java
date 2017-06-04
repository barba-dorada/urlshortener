package ru.cwl.testapp.shortener.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vadim.tishenko
 * on 04.06.2017 20:12.
 */
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
