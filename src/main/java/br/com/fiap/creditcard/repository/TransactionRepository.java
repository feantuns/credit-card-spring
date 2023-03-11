package br.com.fiap.creditcard.repository;

import br.com.fiap.creditcard.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("select t, s from TransactionEntity t, StudentEntity s where s.id = :studentId and s.id = t.studentId")
    List<TransactionEntity> findAllByStudentId(Long studentId);

}