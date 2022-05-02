package br.com.th.springboot.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import br.com.th.springboot.entity.Client;
import lombok.Data;

@Data
public class ClientResponseDto {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<InvestimentResponseDto> investimentos;

    private List<FieldError> erros;

    public static ClientResponseDto fromEntity(Client client, List<InvestimentResponseDto> investimentos){
        var response = new ClientResponseDto();
        response.setId(client.getId());
        response.setName(client.getName());
        response.setEmail(client.getEmail());
        response.setCreatedAt(client.getCreatedAt());
        response.setUpdatedAt(client.getUpdatedAt());
        response.setInvestimentos(investimentos);

        return response;
    }
    
}
