package br.com.fiap.creditcard.util;

import br.com.fiap.creditcard.repository.StudentRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentEndpointTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listStudentsWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
//        restTemplate = restTemplate.withBasicAuth("1", "1");
//        ResponseEntity<String> response = restTemplate.getForEntity("students",String.class);
//        Assertions.assertEquals(response.getStatusCodeValue(),401);
    }

    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthentication("raquel", "devraquel");

        }
    }

}
