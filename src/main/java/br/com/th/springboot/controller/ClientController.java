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

import br.com.th.springboot.dto.request.ClientRequest;
import br.com.th.springboot.dto.response.ClientResponseDto;
import br.com.th.springboot.dto.response.InvestimentResponseDto;
import br.com.th.springboot.entity.Client;
import br.com.th.springboot.repository.ClientRepository;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("")
    public ResponseEntity<List<ClientResponseDto>> listAll() {

        List<Client> list = this.clientRepository.findAll();

        List<ClientResponseDto> userList = list.stream().map(e -> ClientResponseDto.fromEntity(e, null))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(userList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> listById(@PathVariable Long id) {

        Optional<Client> client = this.clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ClientResponseDto.fromEntity(client.get(), null));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<ClientResponseDto> create(@Valid @RequestBody ClientRequest user) {

        // if(bindingResult.hasErrors()){
        //
        // List<FieldError> errors = bindingResult.getFieldErrors().stream().map(e->new
        // FieldError(e.getField(),
        // e.getDefaultMessage())).collect(Collectors.toList());
        //
        // return
        // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserResponseDto.fromEntity(new
        // User(), errors, null));
        // }
        //
        //

        Client newUser = new Client();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());

        ClientResponseDto response = ClientResponseDto.fromEntity(this.clientRepository.save(newUser), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> edit(@PathVariable Long id,
            @Valid @RequestBody ClientRequest userRequest) {

        Optional<Client> opUser = this.clientRepository.findById(id);

        if (opUser.isPresent()) {
            Client user = opUser.get();
            user.setEmail(userRequest.getEmail());
            user.setName(userRequest.getName());

            ClientResponseDto response = ClientResponseDto.fromEntity(this.clientRepository.save(user), null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Client> user = this.clientRepository.findById(id);

        if (user.isPresent()) {

            this.clientRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).build();

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Sub recurso
    @GetMapping("/{id}/investments")
    public ResponseEntity<List<InvestimentResponseDto>> investimentosOfClient(@PathVariable Long id) {

        Optional<Client> client = this.clientRepository.findById(id);

        if (client.isPresent()) {

            List<InvestimentResponseDto> investimentos = client.get().getInvestments().stream()
                    .map(e -> InvestimentResponseDto.fromEntity(e)).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(investimentos);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
