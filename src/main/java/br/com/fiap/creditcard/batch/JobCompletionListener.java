package br.com.fiap.creditcard.batch;

import br.com.fiap.creditcard.dto.TransactionCreateDTO;
import br.com.fiap.creditcard.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


public class JobCompletionListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(JobCompletionListener.class);

    @Autowired
    private TransactionService transactionService;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Job execution completed successfully");

            // Gera massa de transações para o primeiro estudante
            for (int i = 0; i < 100; i++) {
                transactionService.create(new TransactionCreateDTO(1L, new BigDecimal(Math.random() * 100)));
            }

        } else {
            logger.error("Job execution failed");
        }
    }
}