package br.com.gabriel.processaDados.util;

import br.com.gabriel.processaDados.model.Transaction;
import br.com.gabriel.processaDados.model.TransactionCustomer;
import br.com.gabriel.processaDados.model.enums.TransactionTypeEnum;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionIngestor {

    public List<Transaction> read(String fileName) {
        try {
            Path path = Path.of(fileName);

            //le o arquivo completo -> gasto maior de memória
            List<String> lines = Files.readAllLines(path);

            return lines.stream().skip(1).limit(1_000).map(this::parseTransaction).toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + fileName, e);
        }
    }
    public List<Transaction> readOldSchool(String fileName) {
        List<Transaction> transactions = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(fileName);
            Scanner scanner = new Scanner(fis)) {

            int lineCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                lineCount++;

                if (lineCount == 1) {
                    continue;
                } else if (lineCount > 1_001) {
                    break;
                }

                var transaction = parseTransaction(line);

                transactions.add(transaction);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + fileName, e);
        }

        return transactions;
    }

    private Transaction parseTransaction(String line) {
        String[] chunks = line.split(",");

        int step = Integer.parseInt(chunks[0]);
        TransactionTypeEnum type = TransactionTypeEnum.valueOf(chunks[1]);
        BigDecimal amount = new BigDecimal(chunks[2]);
        var origin = new TransactionCustomer(chunks[3], new BigDecimal(chunks[4]), new BigDecimal(chunks[5]));
        var recipient = new TransactionCustomer(chunks[6], new BigDecimal(chunks[7]), new BigDecimal(chunks[8]));
        boolean isFraud = "1".equals(chunks[9]);
        boolean isFlaggedFraud = "1".equals(chunks[10]);

        return new Transaction(step, type, amount, origin, recipient,isFraud, isFlaggedFraud);
    }

}