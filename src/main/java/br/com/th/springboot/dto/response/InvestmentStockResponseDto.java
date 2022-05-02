package br.com.th.springboot.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import br.com.th.springboot.entity.Investment;
import lombok.Data;

@Data
public class InvestmentStockResponseDto {

    private InvestimentResponseDto investment;
    private List<StockInvestmentResponseDto> stocks;
    

    public static InvestmentStockResponseDto fromEntity(Investment investment){
        
        var response = new InvestmentStockResponseDto();
        
        response.setInvestment(InvestimentResponseDto.fromEntity(investment));

        response.setStocks(
            investment.getInvestmentStock().stream().map(e -> StockInvestmentResponseDto.fromEntity(e)).collect(Collectors.toList())
        );

        
        return response;
    }
    
}
