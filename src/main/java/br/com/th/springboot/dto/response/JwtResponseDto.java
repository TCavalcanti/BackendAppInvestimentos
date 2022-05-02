package br.com.th.springboot.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponseDto {

    private String type;
    private String token;

    private List<FieldError> erros;
    
    public static JwtResponseDto fromEntity(String type, String token, List<FieldError> erros){
        var response = new JwtResponseDto();
        
        response.setType(type);
        response.setToken(token);
        response.setErros(erros);
        
        return response;
    }
    
}
