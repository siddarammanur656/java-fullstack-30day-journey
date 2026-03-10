package ProjectPaymentProcessingSystem;
//MAIN — Wire everything together
public class PaymentSystem {
    public static void main(String[] args) {

        // Build components (Dependency Injection manually)
        PaymentValidator    validator = new PaymentValidatorImpl();
        PaymentLogger       logger    = new ConsolePaymentLogger();

        StripeProcessor  stripe   = new StripeProcessor (validator, logger);
        PayPalProcessor  paypal   = new PayPalProcessor (validator, logger);
        RazorpayProcessor razorpay = new RazorpayProcessor(validator, logger);

        PaymentGateway gateway = new PaymentGateway(logger,
                                                    stripe, paypal, razorpay);

        // ── Process various payments ───────────────────────────
        PaymentRequest r1 = new PaymentRequest("CUST001", 299.99, "USD", "CREDIT_CARD");
        gateway.pay(r1);

        PaymentRequest r2 = new PaymentRequest("CUST002", 49.99, "USD", "PAYPAL");
        gateway.pay(r2);

        PaymentRequest r3 = new PaymentRequest("CUST003", 1500.00, "INR", "UPI");
        gateway.pay(r3);

        PaymentRequest r4 = new PaymentRequest("CUST004", 89.99, "EUR", "APPLE_PAY");
        gateway.pay(r4);

        // ── Test validation failure ────────────────────────────
        PaymentRequest invalid = new PaymentRequest("CUST005",
                600_000.00, "USD", "CREDIT_CARD"); // exceeds max
        gateway.pay(invalid);

        // ── Test unsupported method ────────────────────────────
        PaymentRequest unsupported = new PaymentRequest(
                "CUST006", 100.00, "USD", "BITCOIN");
        gateway.pay(unsupported);

        // ── Refund ─────────────────────────────────────────────
        RefundResult refund = gateway.refund("Stripe", "STR-1001-xxxx", 299.99);
        System.out.println("\n" + refund);

        // ── Summary ────────────────────────────────────────────
        gateway.printSummary();
    }
}