package br.com.fiap.creditcard.controller;

import br.com.fiap.creditcard.dto.StudentCreateUpdateDTO;
import br.com.fiap.creditcard.dto.StudentDTO;
import br.com.fiap.creditcard.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> listAll(@RequestParam(required = false) String name) {
        return studentService.list(name);
    }

    @GetMapping("{id}")
    public StudentDTO findById(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO create(@RequestBody StudentCreateUpdateDTO studentCreateUpdateDTO) {
        return studentService.create(studentCreateUpdateDTO);
    }

    @PutMapping("{id}")
    public StudentDTO update(@PathVariable Long id,
                                 @RequestBody StudentCreateUpdateDTO studentCreateUpdateDTO) {
        return studentService.update(id, studentCreateUpdateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

}