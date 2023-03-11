package br.com.fiap.creditcard.controller;

import br.com.fiap.creditcard.dto.TransactionCreateDTO;
import br.com.fiap.creditcard.dto.TransactionDTO;
import br.com.fiap.creditcard.dto.TransactionFormattedDTO;
import br.com.fiap.creditcard.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionFormattedDTO> listAll() {
        return transactionService.list(null);
    }

    @GetMapping("{id}")
    public TransactionDTO findById(@PathVariable Long id) {
        return transactionService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO create(@RequestBody TransactionCreateDTO transactionCreateDTO) {
        return transactionService.create(transactionCreateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }

}