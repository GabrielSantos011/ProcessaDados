package br.com.gabriel.processaDados.model;

import br.com.gabriel.processaDados.model.enums.TransactionTypeEnum;

import java.math.BigDecimal;

public record Transaction(int step, TransactionTypeEnum type, BigDecimal amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud, boolean isFlaggedFraud) { }
