package br.com.gabriel.processaDados.model;

import br.com.gabriel.processaDados.model.enums.TransactionTypeEnum;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(int step, TransactionTypeEnum type, BigDecimal amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud, boolean isFlaggedFraud) {
    //construtor em Records
    public Transaction {
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(recipient);

        if (step <= 0) throw new IllegalArgumentException("O valor de step deve ser positivo: " + step);
        if (amount.signum() < 0) throw new IllegalArgumentException("O valor de amount deve ser positivo ou zero: " + amount);
    }
}
