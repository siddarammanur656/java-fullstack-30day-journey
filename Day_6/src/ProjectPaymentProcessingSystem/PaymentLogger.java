package ProjectPaymentProcessingSystem;

interface PaymentLogger {
    void logPayment(PaymentResult result);
    void logRefund(RefundResult result);
}