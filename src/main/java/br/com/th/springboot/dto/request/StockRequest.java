package br.com.th.springboot.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StockRequest {

    private Long id;

    @NotBlank(message = "O nome da ação não pode ser em branco.")
    private String name;

    private double percent;

}
