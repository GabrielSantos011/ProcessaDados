package br.com.gabriel.processaDados.util;

import br.com.gabriel.processaDados.model.Transaction;
import br.com.gabriel.processaDados.model.TransactionCustomer;
import br.com.gabriel.processaDados.model.enums.TransactionTypeEnum;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionIngestor {

    public List<Transaction> read(String fileName) {
        Path path = Path.of(fileName);

        //ler o arquivo como uma única string para depois fazer split
        //String conteudo = Files.readString(path);
        //ou le o arquivo completo e já entrega separado por linhas
        //List<String> lines = Files.readAllLines(path);
        //os dois casos custam bastante memória e são ideais para arquivos curtos.

        //para arquivos grandes prefira o lines que vai
        //consumindo memória somente conforme for sendo lido
        try (Stream<String> lines = Files.lines(path)) {

            return lines
                    .skip(1)
                    .limit(50_000)
                    .map(this::parseTransaction)
                    .flatMap(Optional::stream)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + fileName, e);
        }
    }

    private Optional<Transaction> parseTransaction(String line) {
        try {
            String[] chunks = line.split(",", -1);

            validateColumnCount(chunks);

            int step = parseInt(chunks[0], "step");

            TransactionTypeEnum type = parseTransactionType(chunks[1]);

            BigDecimal amount = parseBigDecimal(chunks[2], "amount");

            TransactionCustomer origin = new TransactionCustomer(
                    require(chunks[3], "origin.name"),
                    parseBigDecimal(chunks[4], "origin.oldBalance"),
                    parseBigDecimal(chunks[5], "origin.newBalance")
            );

            TransactionCustomer recipient = new TransactionCustomer(
                    require(chunks[6], "recipient.name"),
                    parseBigDecimal(chunks[7], "recipient.oldBalance"),
                    parseBigDecimal(chunks[8], "recipient.newBalance")
            );

            boolean isFraud = parseBoolean(chunks[9], "isFraud");

            boolean isFlaggedFraud = parseBoolean(chunks[10], "isFlaggedFraud");

            return Optional.of(new Transaction(step, type, amount, origin, recipient, isFraud, isFlaggedFraud));
        } catch (Exception e) {
            System.err.printf("""
                    Erro ao processar a linha:
                    %s
                    Motivo: %s

                    """, line, e.getMessage());

            return Optional.empty();
        }
    }

    private void validateColumnCount(String[] chunks) {

        if (chunks.length != 11) {
            throw new IllegalArgumentException(
                    "Esperadas 11 colunas, mas foram encontradas " + chunks.length
            );
        }
    }

    private String require(String value, String field) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "O campo '" + field + "' é obrigatório."
            );
        }

        return value.trim();
    }

    private int parseInt(String value, String field) {

        value = require(value, field);

        try {
            return Integer.parseInt(value);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "O campo '" + field + "' deve ser um número inteiro. Valor recebido: " + value
            );
        }
    }

    private BigDecimal parseBigDecimal(String value, String field) {

        value = require(value, field);

        try {
            return new BigDecimal(value);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "O campo '" + field + "' deve ser um número decimal. Valor recebido: " + value
            );
        }
    }

    private TransactionTypeEnum parseTransactionType(String value) {

        value = require(value, "type");

        try {
            return TransactionTypeEnum.valueOf(value);

        } catch (IllegalArgumentException e) {

            String tipos = Arrays.stream(TransactionTypeEnum.values())
                    .map(Enum::name)
                    .sorted()
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");

            throw new IllegalArgumentException(
                    "Tipo de transação inválido: '" + value +
                            "'. Valores permitidos: " + tipos
            );
        }
    }

    private boolean parseBoolean(String value, String field) {

        value = require(value, field);

        return switch (value) {
            case "0" -> false;
            case "1" -> true;
            default -> throw new IllegalArgumentException(
                    "O campo '" + field + "' deve conter apenas 0 ou 1. Valor recebido: " + value
            );
        };
    }

}