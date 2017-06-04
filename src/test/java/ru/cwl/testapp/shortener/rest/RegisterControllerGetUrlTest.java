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
public class RegisterControllerGetUrlTest {

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
        when(registerServiceMock.getLongUrl("1")).thenReturn(new RegisterService.Pair<>("http://yandex.ru",301));
        when(registerServiceMock.getLongUrl("2")).thenReturn(new RegisterService.Pair<>("http://google.ru",302));
        when(registerServiceMock.getLongUrl("3")).thenReturn(null);
        registerController=new RegisterController(registerServiceMock,accountService);

    }

    @Test
    public void getUrl301(){
        String shortUrl = "1";
        RegisterController.LongUrlResponse result = registerController.getLongUrl(shortUrl);
        assertThat(result.longUrl,is("http://yandex.ru"));
        assertThat(result.redirectType,is(301));
    }

    @Test
    public void getUrl302(){
        String shortUrl = "2";
        RegisterController.LongUrlResponse result = registerController.getLongUrl(shortUrl);
        assertThat(result.longUrl,is("http://google.ru"));
        assertThat(result.redirectType,is(302));
    }

    @Test
    public void getUrlNotFound(){
        String shortUrl = "3";
        boolean res = false;
        try {
            RegisterController.LongUrlResponse result = registerController.getLongUrl(shortUrl);
        }catch (URLNotFoundException e){
            res=true;
        }
        assertTrue(res);
    }


}