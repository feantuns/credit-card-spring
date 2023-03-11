package br.com.fiap.creditcard.service;

import br.com.fiap.creditcard.dto.*;

import java.util.List;

public interface TransactionService {

    List<TransactionFormattedDTO> list (Long studentId);
    TransactionDTO get(Long id);
    TransactionDTO create(TransactionCreateDTO transactionCreateDTO);
    void delete(Long id);
}
