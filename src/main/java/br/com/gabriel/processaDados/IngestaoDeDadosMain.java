import br.com.gabriel.processaDados.util.TransactionIngestor;

void main() {
    TransactionIngestor transactionIngestor = new TransactionIngestor();

    var transacoes = transactionIngestor.read("data/PS_20174392719_1491204439457_log.csv");

    transacoes.stream().limit(10).forEach(IO::println);
}
