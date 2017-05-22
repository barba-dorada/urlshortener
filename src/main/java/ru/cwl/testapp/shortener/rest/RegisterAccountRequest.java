package ru.cwl.testapp.shortener.rest;

/**
 * Created by vadim.tishenko
 * on 20.05.2017 16:05.
 */
public class RegisterAccountRequest {
    public RegisterAccountRequest(){    }

    /**
     * JSON object with the following parameters:
     *  AccountId (String, mandatory)
     * Example: { AccountId : 'myAccountId'}
     */
    String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String a2ccountId) {
        accountId = a2ccountId;
    }
}
