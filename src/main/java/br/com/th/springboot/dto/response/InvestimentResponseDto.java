package br.com.th.springboot.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import br.com.th.springboot.entity.Investment;
import br.com.th.springboot.entity.Stock;
import lombok.Data;

@Data
public class InvestimentResponseDto {

    private Long id;
    private Long clientId;
    private String name;
    private double amount;
    private Boolean indicadorCarencia;
    private List<Stock> stocks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    

    public static InvestimentResponseDto fromEntity(Investment investimento){
        
        var response = new InvestimentResponseDto();
        
        response.setId(investimento.getId());
        response.setClientId(investimento.getCliente().getId());
        response.setName(investimento.getName());
        response.setAmount(investimento.getAmount());
        response.setIndicadorCarencia(investimento.getIndicadorCarencia());
        
        response.setCreatedAt(investimento.getCreatedAt());
        response.setUpdatedAt(investimento.getUpdatedAt());

        // System.out.println("Ações dos Investimentos: "+investimento.getInvestmentStock().toString());

        return response;
    }
    
}
