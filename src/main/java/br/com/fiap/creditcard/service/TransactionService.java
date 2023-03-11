package br.com.fiap.creditcard.service;

import br.com.fiap.creditcard.dto.StudentCreateUpdateDTO;
import br.com.fiap.creditcard.dto.StudentDTO;
import br.com.fiap.creditcard.dto.TransactionCreateDTO;
import br.com.fiap.creditcard.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> list (Long studentId);
    TransactionDTO get(Long id);
    TransactionDTO create(TransactionCreateDTO transactionCreateDTO);
    void delete(Long id);
}
