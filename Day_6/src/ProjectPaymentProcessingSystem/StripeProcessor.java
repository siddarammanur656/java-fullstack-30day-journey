package ProjectPaymentProcessingSystem;

import java.util.List;

class StripeProcessor extends BasePaymentProcessor {

    private static final double MAX_AMOUNT = 999999.99;
    private static final double FEE_RATE   = 0.029; // 2.9%

    public StripeProcessor(PaymentValidator validator, PaymentLogger logger) {
        super(validator, logger);
    }

    @Override
    protected PaymentResult executePayment(PaymentRequest req, String txnId) {
        // Simulate Stripe-specific logic
        if (req.getAmount() > MAX_AMOUNT) {
            return new PaymentResult(txnId, PaymentResult.Status.DECLINED,
                    "Amount exceeds Stripe limit", req.getAmount(),
                    req.getCurrency(), getProviderName());
        }
        double fee = req.getAmount() * FEE_RATE;
        System.out.printf("  Stripe fee: $%.2f%n", fee);
        // Simulate occasional failure (10% chance)
        if (Math.random() < 0.1) {
            return new PaymentResult(txnId, PaymentResult.Status.FAILED,
                    "Card declined by bank", req.getAmount(),
                    req.getCurrency(), getProviderName());
        }
        return new PaymentResult(txnId, PaymentResult.Status.SUCCESS,
                "Payment processed via Stripe", req.getAmount(),
                req.getCurrency(), getProviderName());
    }

    @Override
    public RefundResult refund(String transactionId, double amount) {
        String refundId = "REF-STR-" + System.currentTimeMillis() % 10000;
        return new RefundResult(true, refundId,
                "Stripe refund initiated (3-5 business days)", amount);
    }

    @Override public boolean supports(String method) {
        return List.of("CREDIT_CARD","DEBIT_CARD","APPLE_PAY","GOOGLE_PAY")
                   .contains(method.toUpperCase());
    }

    @Override public String getProviderName() {
        return "Stripe";
    }
}