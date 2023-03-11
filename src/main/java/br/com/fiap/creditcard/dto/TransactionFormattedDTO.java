package br.com.fiap.creditcard.dto;

import br.com.fiap.creditcard.entity.TransactionEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionFormattedDTO(
        Long id,
        Long studentId,
        String price,
        String createdDate
) {
}