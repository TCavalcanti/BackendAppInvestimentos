package br.com.th.springboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.th.springboot.dto.request.UserRequest;
import br.com.th.springboot.dto.response.UserResponseDto;
import br.com.th.springboot.entity.User;
import br.com.th.springboot.repository.UserRepository;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> listAll(){

        List<User> list = this.userRepository.findAll();

        List<UserResponseDto> userList = list.stream().map(e -> UserResponseDto.fromEntity(e)).collect(Collectors.toList());
       
        return ResponseEntity.ok().body(userList);
        
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> listById(@PathVariable Long id){
    	
        Optional<User> user = this.userRepository.findById(id);

        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(UserResponseDto.fromEntity(user.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



    @PostMapping("")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequest user){

        
//        if(bindingResult.hasErrors()){
//
//            List<FieldError> errors =  bindingResult.getFieldErrors().stream().map(e->new FieldError(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserResponseDto.fromEntity(new User(), errors, null));
//        }
//
//    
            
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());

        UserResponseDto response = UserResponseDto.fromEntity(this.userRepository.save(newUser));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> edit(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest){
    	
        Optional<User> opUser = this.userRepository.findById(id);

        if(opUser.isPresent()){
            User user = opUser.get();
            user.setEmail(userRequest.getEmail());
            user.setName(userRequest.getName());
            user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
            user.setUsername(userRequest.getUsername());

            UserResponseDto response = UserResponseDto.fromEntity(this.userRepository.save(user));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
    	
        Optional<User> user = this.userRepository.findById(id);

        if(user.isPresent()){
        	
        	this.userRepository.deleteById(id);
        	
            return ResponseEntity.status(HttpStatus.OK).build();

        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
