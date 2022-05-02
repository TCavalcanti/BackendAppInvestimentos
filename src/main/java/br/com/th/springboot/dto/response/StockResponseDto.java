package br.com.th.springboot.dto.response;

import java.time.LocalDateTime;

import br.com.th.springboot.entity.Stock;
import lombok.Data;

@Data
public class StockResponseDto {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static StockResponseDto fromEntity(Stock stock){
        
        var response = new StockResponseDto();
        response.setId(stock.getId());
        response.setName(stock.getName());
        response.setCreatedAt(stock.getCreatedAt());
        response.setUpdatedAt(stock.getUpdatedAt());

        return response;
    }
    
}
