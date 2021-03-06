package ru.cwl.testapp.shortener.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.cwl.testapp.shortener.ShortenerTestAppApplication;
import ru.cwl.testapp.shortener.repository.AccountService;
import ru.cwl.testapp.shortener.repository.RegisterService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by vadim.tishenko
 * on 22.05.2017 21:44.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShortenerTestAppApplication.class})
@WebAppConfiguration
public class RegisterControllerTest {

    //private MockMvc mockMvc;

    @Autowired
    private RegisterController registerController;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private AccountService accountService;

    @Before
    public void before(){
        RegisterService registerServiceMock = mock(RegisterService.class);
        registerController=new RegisterController(registerServiceMock,accountService);
    }

    @Test
    public void addUrlBadAuthToken() {
        RequestRegister req = new RequestRegister();
        req.url = "";
        req.redirectType = "301";
        String auth = "";
        ResponseEntity<?> resp = registerController.registerUrl(req, auth);

        assertTrue(resp.getBody() instanceof String);
        assertThat((String) (resp.getBody()), is(" <== error"));

        assertThat(resp.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
        assertThat(resp.getStatusCodeValue(), is(401));
    }

    @Test
    public void addUrlWithAuth() {
        AccountService accountServiceMock=mock(AccountService.class);
        when(accountServiceMock.checkPassword("newUserId", "P@SsWorD")).thenReturn(true);
        registerController = new RegisterController(registerService, accountServiceMock);

        RequestRegister req = new RequestRegister();
        req.url = "http://yandex.ru";
        req.redirectType = "301";
        String auth = "Basic bmV3VXNlcklkOlBAU3NXb3JE";
        ResponseEntity<?> resp = registerController.registerUrl(req, auth);
        assertTrue(resp.getBody() instanceof RegisterResult);
        RegisterResult res= (RegisterResult) resp.getBody();

        assertThat(res.getShortUrl(), is("1"));

        assertThat(resp.getStatusCode(), is(HttpStatus.OK));
    }

}