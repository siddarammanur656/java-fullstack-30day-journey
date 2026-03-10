package ProjectPaymentProcessingSystem;
//LOGGER IMPLEMENTATION (SRP: only logs)
class ConsolePaymentLogger implements PaymentLogger {
    @Override
    public void logPayment(PaymentResult result) {
        System.out.println("  [AUDIT] " + result);
    }

    @Override
    public void logRefund(RefundResult result) {
        System.out.println("  [AUDIT] Refund: " + result);
    }
}