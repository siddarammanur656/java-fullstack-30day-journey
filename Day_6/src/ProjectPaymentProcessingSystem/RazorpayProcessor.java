package ProjectPaymentProcessingSystem;

import java.util.List;

class RazorpayProcessor extends BasePaymentProcessor {

    public RazorpayProcessor(PaymentValidator validator, PaymentLogger logger) {
        super(validator, logger);
    }

    @Override
    protected PaymentResult executePayment(PaymentRequest req, String txnId) {
        System.out.println("  → Initiating Razorpay order...");
        return new PaymentResult(txnId, PaymentResult.Status.SUCCESS,
                "Payment via Razorpay", req.getAmount(),
                req.getCurrency(), getProviderName());
    }

    @Override
    public RefundResult refund(String transactionId, double amount) {
        String refundId = "REF-RZP-" + System.currentTimeMillis() % 10000;
        return new RefundResult(true, refundId,
                "Razorpay refund in 5-7 days", amount);
    }

    @Override public boolean supports(String method) {
        return List.of("UPI","CREDIT_CARD","DEBIT_CARD","NETBANKING","WALLET")
                   .contains(method.toUpperCase());
    }

    @Override public String getProviderName() {
        return "Razorpay";
    }
}