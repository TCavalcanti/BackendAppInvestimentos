package br.com.th.springboot.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class UserRequest {

    @Positive(message = "O id do usuário deve ser um número positivo.")
    private Long id;
    
    @NotBlank(message = "O nome do usuário não pode ser em branco.")
    @NotNull(message = "O nome do usuário não pode ser em branco.")
    private String name;

    @NotBlank(message = "O username do usuário não pode ser em branco.")
    private String username;

    @NotBlank(message = "O email do usuário não pode ser em branco.")
    @Email(message = "Informe um email válido.")
    private String email;

    @NotBlank(message = "O password do usuário não pode ser em branco.")
    private String password;

    
}
