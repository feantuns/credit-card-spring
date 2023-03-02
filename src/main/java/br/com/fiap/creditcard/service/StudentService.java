package br.com.fiap.creditcard.service;

import br.com.fiap.creditcard.dto.StudentCreateUpdateDTO;
import br.com.fiap.creditcard.dto.StudentDTO;
import br.com.fiap.creditcard.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    List<StudentDTO> list (String name);
    StudentDTO get(Long id);
    StudentDTO create(StudentCreateUpdateDTO studentCreateUpdateDTO);
    StudentDTO update(Long id, StudentCreateUpdateDTO studentCreateUpdateDTO);
    void delete(Long id);
}
