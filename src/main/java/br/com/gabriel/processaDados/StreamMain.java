import br.com.gabriel.processaDados.model.Transaction;
import br.com.gabriel.processaDados.model.enums.TransactionTypeEnum;
import br.com.gabriel.processaDados.util.FraudAnalyzer;
import br.com.gabriel.processaDados.util.TransactionIngestor;

void main() {
    TransactionIngestor transactionIngestor = new TransactionIngestor();

    List<Transaction> transactions = transactionIngestor.read("data/PS_20174392719_1491204439457_log.csv");

    var fraudAnalyzer = new FraudAnalyzer(transactions);

    //     Apenas transações onde isFraud == true, imprima o tamanho da lista.
    long fraudCount = fraudAnalyzer.countFrauds();
    IO.println("Total de fraudes: " + fraudCount);

    //     Imprima as 3 fraudes de maior valor (amount).
    List<BigDecimal> highestFraudAmounts = fraudAnalyzer.findHighestValueFraudAmounts(3);
    IO.println("Top 3 fraudes de maior valor:");
    highestFraudAmounts.forEach(amount -> IO.println("- %.2f".formatted(amount)));


    //     Obter apenas os nomes dos clientes de origem (nameOrig) dessas fraudes e depois gere uma lista sem repetições (Set ou distinct) com os 5 maiores clientes suspeitos.
    List<String> suspiciousClients = fraudAnalyzer.findTopSuspiciousClients(5);
    IO.println("Top 5 clientes suspeitos:");
    suspiciousClients.forEach(IO::println);

    //     Calcule o prejuízo total causado pelas fraudes (soma dos amount).
    BigDecimal totalFraudLoss = fraudAnalyzer.calculateTotalFraudLoss();
    IO.println("Prejuízo total: " + totalFraudLoss);

    // Conte quantas fraudes ocorreram por tipo de transação (CASH_OUT, TRANSFER, etc...)
    Map<TransactionTypeEnum, Long> fraudCountByType = fraudAnalyzer.countFraudsByType();
    IO.println("Fraudes por tipo:");
    fraudCountByType.forEach((type, count) -> IO.println("- %s: %d".formatted(type, count)));
}