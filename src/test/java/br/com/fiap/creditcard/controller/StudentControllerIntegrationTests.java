package br.com.fiap.creditcard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.fiap.creditcard.CreditCardApplication;
import br.com.fiap.creditcard.dto.StudentCreateUpdateDTO;
import br.com.fiap.creditcard.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = CreditCardApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTests
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetStudent()
    {
        StudentDTO student = this.restTemplate
                .getForObject("http://localhost:" + port + "/students/1", StudentDTO.class);
        assertTrue(student.id() == 1);
    }

    @Test
    public void testAddStudent() {
        StudentCreateUpdateDTO student = new StudentCreateUpdateDTO("Estudante Teste", "123");
        ResponseEntity<StudentCreateUpdateDTO> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/students", student, StudentCreateUpdateDTO.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testEditStudent() {
        StudentCreateUpdateDTO student = new StudentCreateUpdateDTO("Estudante Teste", "123");
        this.restTemplate
                .put("http://localhost:" + port + "/students/1", student, StudentCreateUpdateDTO.class);
        StudentDTO edittedStudent = this.restTemplate
                .getForObject("http://localhost:" + port + "/students/1", StudentDTO.class);
        assertEquals(edittedStudent.name(), student.name());
    }

    @Test
    public void testDeleteStudent() {
        this.restTemplate
                .delete("http://localhost:" + port + "/students/2");
        ResponseEntity<StudentDTO> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/students/2", StudentDTO.class);
        assertEquals(404, responseEntity.getStatusCodeValue());
    }
}