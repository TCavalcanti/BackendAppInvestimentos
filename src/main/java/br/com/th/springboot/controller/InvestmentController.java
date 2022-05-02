package br.com.th.springboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
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

import br.com.th.springboot.dto.request.InvestimentRequest;
import br.com.th.springboot.dto.request.StockRequest;
import br.com.th.springboot.dto.response.InvestimentResponseDto;
import br.com.th.springboot.dto.response.InvestmentStockResponseDto;
import br.com.th.springboot.entity.Investment;
import br.com.th.springboot.entity.InvestmentStock;
import br.com.th.springboot.entity.Stock;
import br.com.th.springboot.repository.ClientRepository;
import br.com.th.springboot.repository.InvestmentRepository;
import br.com.th.springboot.repository.StockRepository;

@RestController
@RequestMapping(value = "/api/investments")
public class InvestmentController {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("")
    public ResponseEntity<List<InvestimentResponseDto>> listAll() {

        List<Investment> list = this.investmentRepository.findAll();

        List<InvestimentResponseDto> investments = list.stream().map(e -> InvestimentResponseDto.fromEntity(e))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(investments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentResponseDto> listById(@PathVariable Long id) {

        Optional<Investment> investment = this.investmentRepository.findById(id);

        if (investment.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(InvestimentResponseDto.fromEntity(investment.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<InvestimentResponseDto> create(@Valid @RequestBody InvestimentRequest investmentRequest) {

        Investment newInvestment = new Investment();
        newInvestment.setName(investmentRequest.getName());
        newInvestment.setAmount(investmentRequest.getAmount());
        newInvestment.setIndicadorCarencia(investmentRequest.getIndicadorCarencia());
        newInvestment.setCliente(clientRepository.findById(investmentRequest.getClientId()).get());

        Investment investment = this.investmentRepository.save(newInvestment);

        // System.out.println("########## Investimento");
        // System.out.println(investment.getName());

        InvestimentResponseDto response = InvestimentResponseDto.fromEntity(investment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestimentResponseDto> edit(@PathVariable Long id,
            @Valid @RequestBody InvestimentRequest investmentRequest) {

        Optional<Investment> opInvestment = this.investmentRepository.findById(id);

        if (opInvestment.isPresent()) {
            Investment investment = opInvestment.get();
            investment.setName(investmentRequest.getName());
            investment.setAmount(investmentRequest.getAmount());
            investment.setIndicadorCarencia(investmentRequest.getIndicadorCarencia());
            investment.setCliente(clientRepository.findById(investmentRequest.getClientId()).get());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(InvestimentResponseDto.fromEntity(this.investmentRepository.save(investment)));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Investment> opInvestment = this.investmentRepository.findById(id);

        if (opInvestment.isPresent()) {
            this.investmentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // sub recurso
    @GetMapping("/{id}/stocks")
    public ResponseEntity<InvestmentStockResponseDto> listStocksById(@PathVariable Long id) {

        Optional<Investment> investment = this.investmentRepository.findById(id);

        if (investment.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(
                    InvestmentStockResponseDto.fromEntity(investment.get()));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/{id}/stocks/{stockId}")
    public ResponseEntity<InvestmentStockResponseDto> addStockInvestment(@PathVariable Long id,
            @PathVariable Long stockId) {

        Optional<Investment> investment = this.investmentRepository.findById(id);

        if (investment.isPresent()) {

            Stock stock = stockRepository.getById(stockId);

            InvestmentStock newInvStock = new InvestmentStock();
            newInvStock.setInvestment(investment.get());
            newInvStock.setStock(stock);
            newInvStock.setPercent(0);

            investment.get().getInvestmentStock().add(newInvStock);

            // salvar o investimento
            this.investmentRepository.save(investment.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                    InvestmentStockResponseDto.fromEntity(investment.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping("/{id}/stocks")
    public ResponseEntity<InvestmentStockResponseDto> updatePercentStockInvestment(@PathVariable Long id,
            @Valid @RequestBody StockRequest stockRequest) {

        Optional<Investment> investment = this.investmentRepository.findById(id);

        if (investment.isPresent()) {

            investment.get().getInvestmentStock().stream().filter(e -> e.getStock().getId() == stockRequest.getId())
                    .forEach(e -> e.setPercent(stockRequest.getPercent()));

            // salvar o investimento
            this.investmentRepository.save(investment.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                    InvestmentStockResponseDto.fromEntity(investment.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}/stocks/{stockId}")
    public ResponseEntity<InvestmentStockResponseDto> removeStockInvestment(@PathVariable Long id,
            @PathVariable Long stockId) {

        Optional<Investment> investment = this.investmentRepository.findById(id);

        if (investment.isPresent()) {

            // selecionar as ações que serao removidas
            List<InvestmentStock> stocks = investment.get().getInvestmentStock().stream()
                    .filter(e -> e.getStock().getId() == stockId).collect(Collectors.toList());

            investment.get().getInvestmentStock().removeAll(stocks);
            // salvar o investimento
            this.investmentRepository.save(investment.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                    InvestmentStockResponseDto.fromEntity(investment.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping("/{id}/balancear")
    public ResponseEntity<?> balancearInvestment(@PathVariable Long id,
            @Valid @RequestBody List<StockRequest> stockRequest) {

        Optional<Investment> investment = this.investmentRepository.findById(id);

        if (investment.isPresent()) {

            investment.get().getInvestmentStock().stream().forEach(
                    e -> {

                        Optional<StockRequest> sr = stockRequest.stream()
                                .filter(item -> item.getId() == e.getStock().getId())
                                .findFirst();

                        if (sr.isPresent()) {
                            e.setPercent(sr.get().getPercent());
                        }

                    }

            );

            this.investmentRepository.save(investment.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                    InvestmentStockResponseDto.fromEntity(investment.get()));

            // return ResponseEntity.ok(stockRequest);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
