package br.com.fiap.creditcard.entity;

import br.com.fiap.creditcard.dto.StudentCreateUpdateDTO;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TB_STUDENT")
@EntityListeners(AuditingEntityListener.class)

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String rm;


    public StudentEntity() {
    }

    public StudentEntity(StudentCreateUpdateDTO studentCreateUpdateDTO) {
        this.setName(studentCreateUpdateDTO.name());
        this.setRm(studentCreateUpdateDTO.rm());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }
}