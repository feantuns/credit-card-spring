package br.com.fiap.creditcard.controller;

import br.com.fiap.creditcard.dto.StudentCreateUpdateDTO;
import br.com.fiap.creditcard.dto.StudentDTO;
import br.com.fiap.creditcard.dto.TransactionDTO;
import br.com.fiap.creditcard.service.StudentService;
import br.com.fiap.creditcard.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;
    private TransactionService transactionService;

    public StudentController(StudentService studentService, TransactionService transactionService){
        this.studentService = studentService;
        this.transactionService = transactionService;
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


    @GetMapping(value = "{id}/transactions")
    public List<TransactionDTO> listAllTransactionsByStudentId(@PathVariable Long id) {
        return transactionService.list(id);
    }

}