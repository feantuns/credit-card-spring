package br.com.fiap.creditcard.dto;

import br.com.fiap.creditcard.entity.StudentEntity;

public record StudentDTO(
        Long id,
        String name,
        String rm
) {
    public StudentDTO(StudentEntity studentEntity) {
        this(
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getRm()
        );
    }
}