package ProjectPaymentProcessingSystem;

interface RefundProcessor {
    RefundResult refund(String transactionId, double amount);
}