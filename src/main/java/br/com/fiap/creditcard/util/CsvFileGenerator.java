package br.com.fiap.creditcard.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import br.com.fiap.creditcard.dto.TransactionFormattedDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

@Component
public class CsvFileGenerator {
    public void writeTransactionsToCsv(List<TransactionFormattedDTO> transactions, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (TransactionFormattedDTO transaction : transactions) {
                printer.printRecord(transaction.price(), transaction.createdDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
