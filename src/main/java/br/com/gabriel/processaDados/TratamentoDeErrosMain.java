import br.com.gabriel.processaDados.model.Transaction;
import br.com.gabriel.processaDados.util.TransactionIngestor;

void main() {
    TransactionIngestor transactionIngestor = new TransactionIngestor();

    List<Transaction> transactionsBadData = transactionIngestor.read("data/paysim_with_bad_data.csv");

    transactionsBadData.forEach(IO::println);
}