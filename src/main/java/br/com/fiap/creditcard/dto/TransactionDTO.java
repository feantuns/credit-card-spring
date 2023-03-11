package br.com.fiap.creditcard.dto;

import br.com.fiap.creditcard.entity.TransactionEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        Long id,
        Long studentId,
        BigDecimal price,
        LocalDateTime createdDate
) {
    public TransactionDTO(TransactionEntity transactionEntity) {
        this(
                transactionEntity.getId(),
                transactionEntity.getStudentId(),
                transactionEntity.getPrice(),
                transactionEntity.getCreatedDate()
        );
    }
}