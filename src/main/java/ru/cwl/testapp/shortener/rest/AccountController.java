package ru.cwl.testapp.shortener.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cwl.testapp.shortener.repository.AccountService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by vadim.tishenko
 * on 20.05.2017 11:34.
 */

/**
 * a) Opening of accounts
 * HTTP method
 * POST
 * URI
 * /account
 * Request type
 * application/json
 * Request Body
 * JSON object with the following parameters:
 *  AccountId (String, mandatory)
 * Example: { AccountId : 'myAccountId'}
 * Reponse Type
 * application/json
 * Response
 * We distinguish the successful from the unsuccessful registration.
 * Unsuccessful registration occurs only if the concerned account ID
 * already exists. The parameters are as follows:
 *  success: true | false
 *  description: Description of status, for example: account with that
 * ID already exists
 *  password: Returns only if the account was successfully created.
 * Automatically generated password length of 8 alphanumeric
 * characters
 * Example {success: 'true', description: 'Your account is opened',
 * password: 'xC345Fc0'}
 * www
 */
@RestController
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService repository) {
        accountService = repository;
    }

    @RequestMapping(value = "/account", method = POST)
    public RegisterAccountResponse registerAccount(@RequestBody RegisterAccountRequest request) {
        String accountId = request.getAccountId();
        try {
            String pass = accountService.createAccount(accountId);
            SuccessResp resp = new SuccessResp();
            resp.success = true;
            resp.description = "Your account is opened";
            resp.password = pass;
            return resp;

        } catch (AccountService.DuplicateAccount e) {
            RegisterAccountResponse response = new RegisterAccountResponse();
            response.success = false;
            response.description = "account with that ID already exists";
            return response;
        }

    }


    /**
     * We distinguish the successful from the unsuccessful registration.
     * Unsuccessful registration occurs only if the concerned account ID
     * already exists. The parameters are as follows:
     *  success: true | false
     *  description: Description of status, for example: account with that
     * ID already exists
     *  password: Returns only if the account was successfully created.
     * Automatically generated password length of 8 alphanumeric
     * characters
     * Example {success: 'true', description: 'Your account is opened',
     * password: 'xC345Fc0'}
     * www
     */
    static public class RegisterAccountResponse {
        public RegisterAccountResponse() {
        }

        public boolean success = true;
        public String description = "Your account is opened";
    }

}
