package br.com.th.springboot.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class ClientRequest {

    @Positive(message = "O id do cliente deve ser um número positivo.")
    private Long id;
    
    @NotBlank(message = "O nome do cliente não pode ser em branco.")
    @NotNull(message = "O nome do cliente não pode ser em branco.")
    private String name;


    @NotBlank(message = "O email do cliente não pode ser em branco.")
    @Email(message = "Informe um email válido.")
    private String email;

}
