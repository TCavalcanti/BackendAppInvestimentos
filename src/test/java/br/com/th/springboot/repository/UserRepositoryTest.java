package br.com.th.springboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvcBuilder;

import br.com.th.springboot.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @MockBean
	private UserRepository mockRepository;

    @MockBean
    private UserRepository userRepository;
    


    @Test
    public void shouldCreateMockMvc(){
        assertNotNull(userRepository);
    }


    @Test
    public void shouldReturnListOfUsers(){
        
//        setup();
//
//        List<User> list = userRepository.findAll();
//        
//        //temos que ter 10 elementos na lista 
//        assertEquals(list.size(), 10);
//
//        // o nome do primento elemento deve ser "Nome User 0"
//        assertEquals(list.get(0).getName(), "Nome User 0");
        
    }


    @Test
    public void shouldCreateANewUser(){
        
//        User u = new User();
//                u.setName("nome mock");
//                u.setUsername("mock");
//                u.setPassword("123456");
//                u.setEmail("emailfff@teste.com");
//
//         User newUser = userRepository.save(u);
//
//        assertEquals(newUser.getEmail(), u.getEmail());
//        // já existia 10 elementos previamente no bd. foi add mais 1 temos que ter 1
//        assertEquals(11, this.userRepository.count());
      
    }   


    @Test
    public void showUpdateUser(){
 
//        User u = userRepository.getById((long)1);
//
//        String novoEmail = "testeemail@teste.com";
//
//        u.setEmail(novoEmail);
//        User userAtualizado = userRepository.save(u);
//
//        assertNotNull(userAtualizado);
//
//        assertEquals(novoEmail, userAtualizado.getEmail());

    }



}
