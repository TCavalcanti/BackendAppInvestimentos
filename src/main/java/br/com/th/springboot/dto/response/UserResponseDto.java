package br.com.th.springboot.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import br.com.th.springboot.entity.User;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<FieldError> erros;

    public static UserResponseDto fromEntity(User user){
        var response = new UserResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());


        return response;
    }
    
}
