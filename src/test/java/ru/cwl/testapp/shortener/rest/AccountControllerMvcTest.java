package ru.cwl.testapp.shortener.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.cwl.testapp.shortener.ShortenerTestAppApplication;
import ru.cwl.testapp.shortener.repository.AccountService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vadim.tishenko
 * on 22.05.2017 22:08.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShortenerTestAppApplication.class})
@WebAppConfiguration
public class AccountControllerMvcTest {

    private MockMvc mockMvc;

    private AccountService accountServiceMock;

//    @Autowired
//    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
// We have to reset our mock between tests because the mock objects
// are managed by the Spring container. If we would not reset them,
// stubbing and verified behavior would "leak" from one test to another.
        //Mockito.reset(userService);

        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        accountServiceMock = mock(AccountService.class);
        when(accountServiceMock.createAccount("newUserId")).thenReturn("newPass");
        when(accountServiceMock.isAccountExist("newUserId")).thenReturn(false);

        /*
        1.замокать сервис
        2.собрать рестконтроллер
        3.подставить через

        mockMvc = MockMvcBuilders.standaloneSetup(webApplicationContext).build();
        */
        AccountController accountController = new AccountController(accountServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void registerAccount() throws Exception {
 /*       RegisterAccountRequest req=new RegisterAccountRequest();
        req.accountId="userId1";

        SuccessResp resp=new SuccessResp();
        resp.success=true;
        resp.description="";
        resp.password="QQQ";*/


//        when(accountServiceMock.createAccount("newUserId")).thenReturn("paaaaas");

        mockMvc.perform(post("/account").content("{ \"accountId\" : \"newUserId\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content()
                .json("{" +
                        " description: \"Your account is opened\"," +
                        "success: true," +
                        "password: \"newPass\"" +
                        "}"
                ));

        verify(accountServiceMock,times(1)).isAccountExist("newUserId");
        verify(accountServiceMock, times(1)).createAccount("newUserId");
        verifyNoMoreInteractions(accountServiceMock);

   /*     mockMvc.perform(post("/account").content("{ \"accountId\" : \"newUserId\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content()
                .json("{" +
                        " \"description\": \"Your account is opened\"," +
                        "\"success\": true," +
                        "\"password\": \"P@SsWorD\"" +
                        "}"
                ));*/

    /*            .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[0].title", is("Foo")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[1].title", is("Bar")));

        verify(accountControllerMock, times(1)).registerAccount(req);
        verifyNoMoreInteractions(accountControllerMock);*/

//        verify(accountServiceMock, times(1)).createAccount("newUserId");
//        verifyNoMoreInteractions(accountServiceMock);
//        System.out.printf("QQQQQQ");


    }




}