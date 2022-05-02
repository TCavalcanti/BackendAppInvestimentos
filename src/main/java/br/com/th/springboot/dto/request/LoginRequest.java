package br.com.th.springboot.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty(message = "Username não pode ser vazio.")
    private String username;
    
  //  @NotEmpty(message = "O email não pode ser vazio.")
    @Email(message = "O email informado não é válido.")
    private String email;

    @NotEmpty(message = "O password não pode ser vazio.")
    private String password;
    
}
