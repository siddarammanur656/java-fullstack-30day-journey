package ProjectPaymentProcessingSystem;

import java.util.List;

class PayPalProcessor extends BasePaymentProcessor {

    public PayPalProcessor(PaymentValidator validator, PaymentLogger logger) {
        super(validator, logger);
    }

    @Override
    protected PaymentResult executePayment(PaymentRequest req, String txnId) {
        // PayPal charges higher fees
        double fee = req.getAmount() * 0.034 + 0.30;
        System.out.printf("  PayPal fee: $%.2f%n", fee);

        return new PaymentResult(txnId, PaymentResult.Status.SUCCESS,
                "Payment processed via PayPal", req.getAmount(),
                req.getCurrency(), getProviderName());
    }

    @Override
    protected void preProcess(PaymentRequest request) {
        super.preProcess(request);
        System.out.println("  → Redirecting to PayPal checkout...");
    }

    @Override
    public RefundResult refund(String transactionId, double amount) {
        String refundId = "REF-PP-" + System.currentTimeMillis() % 10000;
        return new RefundResult(true, refundId,
                "PayPal refund processed immediately", amount);
    }

    @Override
    public boolean supports(String method) {
        return List.of("PAYPAL","CREDIT_CARD","BANK_TRANSFER")
                   .contains(method.toUpperCase());
    }

    @Override
    public String getProviderName() {
        return "PayPal";
    }
}