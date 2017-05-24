package ru.cwl.testapp.shortener.rest;

import org.junit.Before;
import org.junit.Test;
import ru.cwl.testapp.shortener.repository.AccountService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by vadim.tishenko
 * on 22.05.2017 22:08.
 */

public class AccountControllerTest {

    private AccountService accountServiceMock;
    AccountController accountController;


    @Before
    public void setUp() {

        accountServiceMock = mock(AccountService.class);
        when(accountServiceMock.createAccount("newUserId")).thenReturn("newPass");
        when(accountServiceMock.isAccountExist("newUserId")).thenReturn(false);

        accountController = new AccountController(accountServiceMock);
    }

    @Test
    public void registerAccount() throws Exception {
        RegisterAccountRequest req = new RegisterAccountRequest();
        req.setAccountId("newUserId");
        AccountController.RegisterAccountResponse result2 = accountController.registerAccount(req);
        //assertThat(result2,isA(SuccessResp.class));
        SuccessResp result = (SuccessResp) result2;
        assertTrue(result.success);
        assertThat(result.description, is("Your account is opened"));
        assertThat(result.password, is("newPass"));

        verify(accountServiceMock, times(1)).isAccountExist("newUserId");
        verify(accountServiceMock, times(1)).createAccount("newUserId");
        verifyNoMoreInteractions(accountServiceMock);
    }


}