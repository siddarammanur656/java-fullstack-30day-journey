package ProjectPaymentProcessingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//ABSTRACT BASE PROCESSOR (Template Method Pattern)
abstract class BasePaymentProcessor implements PaymentProcessor, RefundProcessor {

    protected final PaymentValidator validator;
    protected final PaymentLogger    logger;
    private   final List<PaymentResult> history = new ArrayList<>();
    private   static int txnCounter = 1000;

    public BasePaymentProcessor(PaymentValidator validator, PaymentLogger logger) {
        this.validator = validator;
        this.logger    = logger;
    }

    // Template Method — defines the algorithm, subclasses fill steps
    @Override
    public final PaymentResult process(PaymentRequest request) {
        // Step 1: Validate
        ValidationResult validation = validator.validate(request);
        if (!validation.isValid()) {
            PaymentResult result = new PaymentResult(
                "INVALID", PaymentResult.Status.FAILED,
                "Validation failed: " + validation.getErrors(),
                request.getAmount(), request.getCurrency(), getProviderName()
            );
            logger.logPayment(result);
            return result;
        }

        // Step 2: Pre-process (hook — subclasses can override)
        preProcess(request);

        // Step 3: Execute (abstract — subclasses MUST implement)
        PaymentResult result = executePayment(request, generateTxnId());

        // Step 4: Post-process (hook — subclasses can override)
        postProcess(result);

        // Step 5: Log
        logger.logPayment(result);
        history.add(result);

        return result;
    }

    // Abstract — each provider implements payment differently
    protected abstract PaymentResult executePayment(PaymentRequest req, String txnId);

    // Hooks — optional override
    protected void preProcess(PaymentRequest request) {
        System.out.printf("  → Processing via %s...%n", getProviderName());
    }

    protected void postProcess(PaymentResult result) {
        if (result.isSuccess()) {
            System.out.printf("  ✅ Payment successful! TxnID: %s%n",
                               result.getTransactionId());
        } else {
            System.out.printf("  ❌ Payment failed: %s%n", result.getMessage());
        }
    }

    protected String generateTxnId() {
        return getProviderName().substring(0, 3).toUpperCase()
             + "-" + (++txnCounter) + "-"
             + System.currentTimeMillis() % 10000;
    }

    public List<PaymentResult> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public double getTotalProcessed() {
        return history.stream()
                      .filter(PaymentResult::isSuccess)
                      .mapToDouble(PaymentResult::getAmount)
                      .sum();
    }
}