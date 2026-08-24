package br.com.gabriel.processaDados.repository.interfaces;

import br.com.gabriel.processaDados.model.Transaction;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> findByOriginName(String originName);
}
