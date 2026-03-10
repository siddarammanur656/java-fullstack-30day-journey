package ProjectPaymentProcessingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class PaymentResult {
    public enum Status { SUCCESS, FAILED, PENDING, DECLINED }

    private final String    transactionId;
    private final Status    status;
    private final String    message;
    private final double    amount;
    private final String    currency;
    private final String    provider;
    private final LocalDateTime timestamp;

    public PaymentResult(String transactionId, Status status, String message,
                         double amount, String currency, String provider) {
        this.transactionId = transactionId;
        this.status        = status;
        this.message       = message;
        this.amount        = amount;
        this.currency      = currency;
        this.provider      = provider;
        this.timestamp     = LocalDateTime.now();
    }

    public boolean    isSuccess()       { return status == Status.SUCCESS; }
    public String     getTransactionId(){ return transactionId; }
    public Status     getStatus()       { return status; }
    public String     getMessage()      { return message; }
    public double     getAmount()       { return amount; }
    public String     getCurrency()     { return currency; }
    public String     getProvider()     { return provider; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("  [%s] %s | %s | %.2f %s | %s | TxnID: %s",
                fmt.format(timestamp), status, provider,
                amount, currency, message, transactionId);
    }
}