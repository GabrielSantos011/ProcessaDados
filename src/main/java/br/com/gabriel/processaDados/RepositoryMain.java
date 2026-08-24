import br.com.gabriel.processaDados.model.Transaction;
import br.com.gabriel.processaDados.repository.TransactionListRepository;
import br.com.gabriel.processaDados.repository.TransactionMapRepository;
import br.com.gabriel.processaDados.repository.interfaces.TransactionRepository;
import br.com.gabriel.processaDados.util.TransactionIngestor;

void main() {
    TransactionIngestor transactionIngestor = new TransactionIngestor();

    List<Transaction> transactions = transactionIngestor.read("data/PS_20174392719_1491204439457_log.csv");

    TransactionRepository transactionRepository;

    transactionRepository = new TransactionListRepository(transactions);
    String notFoundOriginName = "C12345";
    transactionRepository.findByOriginName(notFoundOriginName)
            .ifPresentOrElse(IO::println, () -> IO.println("Transacao nao encontrada para " + notFoundOriginName));

    String existingOriginName = "C1868032458";

    long startTimeList = System.nanoTime();
    transactionRepository.findByOriginName(existingOriginName)
            .ifPresentOrElse(IO::println, () -> IO.println("Transacao nao encontrada para " + existingOriginName));
    long endTimeList = System.nanoTime();
    IO.println("Tempo de busca - List (ms): " + (endTimeList - startTimeList) / 1_000_000.0);

    transactionRepository = new TransactionMapRepository(transactions);
    startTimeList = System.nanoTime();
    transactionRepository.findByOriginName(existingOriginName)
            .ifPresentOrElse(IO::println, () -> IO.println("Transacao nao encontrada para " + existingOriginName));
    endTimeList = System.nanoTime();
    IO.println("Tempo de busca - Map (ms): " + (endTimeList - startTimeList) / 1_000_000.0);
}
