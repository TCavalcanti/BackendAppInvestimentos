package br.com.th.springboot.dto.response;

import java.time.LocalDateTime;

import br.com.th.springboot.entity.InvestmentStock;
import lombok.Data;

@Data
public class StockInvestmentResponseDto {

    private Long   id;
    private String name;
    private double percent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static StockInvestmentResponseDto fromEntity(InvestmentStock investmentStock){
        
        var response = new StockInvestmentResponseDto();

        response.setId(investmentStock.getStock().getId());
        response.setName(investmentStock.getStock().getName());
        response.setPercent(investmentStock.getPercent());
        response.setCreatedAt(investmentStock.getCreatedAt());
        response.setUpdatedAt(investmentStock.getUpdatedAt());
        
        return response;
    }
    
}
