package br.com.th.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.th.springboot.entity.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControlllerTests {
    
    private static final String API_PATH_USERS = "/api/users";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void userTestGetAll() throws Exception{
        mockMvc.perform(get(API_PATH_USERS))
            .andExpect(status().isOk());
    }


    // @Test 
    // void usersCreate() throws Exception{

    //     User u = new User();
    //     u.setName("nome mock");
    //     u.setUsername("mock");
    //     u.setPassword("123456");
    //     u.setEmail("email@teste.com");

    //     //testt se a qnt de usuarios aumentou no banco e o ultimo usuario é igual ao criado

    //     mockMvc.perform(post(API_PATH_USERS))
    //         .andExpect(status().isCreated());
        

    // }

}
