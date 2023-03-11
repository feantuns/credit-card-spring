package br.com.fiap.creditcard.dto;

import java.math.BigDecimal;

public record TransactionCreateDTO(
        Long studentId,
        BigDecimal price
){
}
