package br.com.fiap.creditcard.repository;

import br.com.fiap.creditcard.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("select s from StudentEntity s where s.name LIKE %:name%")
    List<StudentEntity> findAllByName(@Param("name") String name);

}