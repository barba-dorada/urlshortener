package ru.cwl.testapp.shortener.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.cwl.testapp.shortener.ShortenerTestAppApplication;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by vadim.tishenko
 * on 22.05.2017 21:44.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, ShortenerTestAppApplication.class})
@WebAppConfiguration
public class RegisterControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RegisterController registerControllerMock;

/*    @Test
    public void registerUrl() throws Exception {
    }

    @Test
    public void getUserId() throws Exception {
    }*/

    @Test
    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {
///*        Todo first = new TodoBuilder()
//                .id(1L)
//                .description("Lorem ipsum")
//                .title("Foo")
//                .build();
//        Todo second = new TodoBuilder()
//                .id(2L)
//                .description("Lorem ipsum")
//                .title("Bar")
//                .build();*/
//
//        when(registerControllerMock.registerUrl()).thenReturn(RequestEntity<>());
//
//        mockMvc.perform(get("/api/todo"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[0].title", is("Foo")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[1].title", is("Bar")));
//
//        verify(registerControllerMock, times(1)).findAll();
//        verifyNoMoreInteractions(registerControllerMock);
    }
    @Test
    public void findAll() throws Exception {
/*        Todo first = new TodoBuilder()
                .id(1L)
                .description("Lorem ipsum")
                .title("Foo")
                .build();
        Todo second = new TodoBuilder()
                .id(2L)
                .description("Lorem ipsum")
                .title("Bar")
                .build();*/

       /* when(registerControllerMock.registerUrl()).thenReturn(RequestEntity<>());

        mockMvc.perform(post("/account"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[0].title", is("Foo")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[1].title", is("Bar")));

        verify(registerControllerMock, times(1)).findAll();
        verifyNoMoreInteractions(registerControllerMock);*/
    }
}