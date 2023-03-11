package br.com.fiap.creditcard.service;

import br.com.fiap.creditcard.dto.TransactionCreateDTO;
import br.com.fiap.creditcard.dto.TransactionDTO;
import br.com.fiap.creditcard.entity.TransactionEntity;
import br.com.fiap.creditcard.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private ObjectMapper objectMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                              ObjectMapper objectMapper) {
        this.transactionRepository = transactionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<TransactionDTO> list(Long studentId) {
        List<TransactionEntity> transactionEntities;

        if (studentId != null) {
            transactionEntities = transactionRepository.findAllByStudentId(studentId);
        } else {
            transactionEntities = transactionRepository.findAll();
        }
        return transactionEntities
                .stream()
                .map(transactionEntity -> new TransactionDTO(transactionEntity.getId(), transactionEntity.getStudentId(), transactionEntity.getPrice(), transactionEntity.getCreatedDate()))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO get(Long id) {
        TransactionEntity transactionEntity = getTransactionEntity(id);
        return new TransactionDTO(transactionEntity);
    }

    @Override
    public TransactionDTO create(TransactionCreateDTO transactionCreateDTO) {

        TransactionEntity transactionEntity = new TransactionEntity(transactionCreateDTO);

        return saveTransactionEntityAndGetTransactionDTO(transactionEntity);

    }

    @Override
    public void delete(Long id) {
        TransactionEntity transactionEntity = getTransactionEntity(id);

        transactionRepository.delete(transactionEntity);
    }

    private TransactionEntity getTransactionEntity(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "student.not.found"));
    }

    private TransactionDTO saveTransactionEntityAndGetTransactionDTO(TransactionEntity transactionEntity) {
        TransactionEntity savedTransactionEntity = transactionRepository.save(transactionEntity);

        return objectMapper.convertValue(savedTransactionEntity, TransactionDTO.class);
    }

}