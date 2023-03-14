package br.com.fiap.creditcard.service;

import br.com.fiap.creditcard.dto.StudentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

    @Test
    void get() {
        StudentDTO student = new StudentDTO(1L, "Laura Teste", "3005564 111-11");
        Assertions.assertEquals(1, student.id());
    }
}


