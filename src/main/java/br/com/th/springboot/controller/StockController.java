package br.com.th.springboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.th.springboot.dto.request.StockRequest;
import br.com.th.springboot.dto.response.StockResponseDto;
import br.com.th.springboot.entity.Stock;
import br.com.th.springboot.repository.StockRepository;

@RestController
@RequestMapping(value = "/api/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("")
    public ResponseEntity<List<StockResponseDto>> listAll() {

        List<Stock> list = this.stockRepository.findAll();

        List<StockResponseDto> stocks = list.stream().map(e -> StockResponseDto.fromEntity(e))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDto> listById(@PathVariable Long id) {

        Optional<Stock> opStock = this.stockRepository.findById(id);

        if (opStock.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(StockResponseDto.fromEntity(opStock.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<StockResponseDto> create(@Valid @RequestBody StockRequest stockRequest) {

        Stock newStock = new Stock();
        newStock.setName(stockRequest.getName());

        StockResponseDto response = StockResponseDto.fromEntity(this.stockRepository.save(newStock));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDto> edit(@PathVariable Long id, @Valid @RequestBody StockRequest stockRequest) {

        Optional<Stock> opStock = this.stockRepository.findById(id);

        if (opStock.isPresent()) {
            Stock stock = opStock.get();
            stock.setName(stockRequest.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(StockResponseDto.fromEntity(this.stockRepository.save(stock)));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Stock> opStock = this.stockRepository.findById(id);

        if (opStock.isPresent()) {
            this.stockRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
