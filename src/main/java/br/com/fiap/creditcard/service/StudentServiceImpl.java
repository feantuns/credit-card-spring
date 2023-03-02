package br.com.fiap.creditcard.service;

import br.com.fiap.creditcard.dto.StudentCreateUpdateDTO;
import br.com.fiap.creditcard.dto.StudentDTO;
import br.com.fiap.creditcard.entity.StudentEntity;
import br.com.fiap.creditcard.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private ObjectMapper objectMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                                  ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<StudentDTO> list(String name) {
        List<StudentEntity> studentEntities;

        if (name != null) {
            studentEntities = studentRepository.findAllByName(name);
        } else {
            studentEntities = studentRepository.findAll();
        }
        return studentEntities
                .stream()
                .map(studentEntity -> new StudentDTO(studentEntity.getId(), studentEntity.getName(), studentEntity.getRm()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO get(Long id) {
        StudentEntity studentEntity = getStudentEntity(id);
        return new StudentDTO(studentEntity);
    }

    @Override
    public StudentDTO create(StudentCreateUpdateDTO studentCreateUpdateDTO) {

        StudentEntity studentEntity = new StudentEntity(studentCreateUpdateDTO);

        return saveStudentEntityAndGetStudentDTO(studentEntity);

    }

    @Override
    public StudentDTO update(Long id, StudentCreateUpdateDTO studentCreateUpdateDTO) {
        StudentEntity studentEntity = getStudentEntity(id);

        studentEntity.setName(studentCreateUpdateDTO.name());
        studentEntity.setRm(studentCreateUpdateDTO.rm());

        return saveStudentEntityAndGetStudentDTO(studentEntity);
    }

    @Override
    public void delete(Long id) {
        StudentEntity studentEntity = getStudentEntity(id);

        studentRepository.delete(studentEntity);
    }

    private StudentEntity getStudentEntity(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "student.not.found"));
    }

    private StudentDTO saveStudentEntityAndGetStudentDTO(StudentEntity studentEntity) {
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

        return objectMapper.convertValue(savedStudentEntity, StudentDTO.class);
    }

}