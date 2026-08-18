import br.com.gabriel.processaDados.model.Transaction;
import br.com.gabriel.processaDados.model.TransactionCustomer;
import br.com.gabriel.processaDados.model.enums.TransactionTypeEnum;

void main() {
    var t1 = new Transaction(1, TransactionTypeEnum.PAYMENT, new BigDecimal("9839.64"),
            new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
            new TransactionCustomer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0")),
            false, false);

    var t2 = new Transaction(743, TransactionTypeEnum.CASH_OUT, new BigDecimal("850002.52"),
            new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")),
            new TransactionCustomer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")),
            true, false);

    IO.println(t1);
    IO.println(t2);
}
