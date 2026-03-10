package ProjectPaymentProcessingSystem;
//PAYMENT GATEWAY — Orchestrator (DIP: depends on interfaces)
import java.util.Arrays;
import java.util.List;

class PaymentGateway {

    // Depends on ABSTRACTIONS, not concrete classes (DIP) ✅
    private final List<BasePaymentProcessor> processors;
    private final PaymentLogger logger;

    public PaymentGateway(PaymentLogger logger,
                          BasePaymentProcessor... processors) {
        this.processors = Arrays.asList(processors);
        this.logger     = logger;
    }

    public PaymentResult pay(PaymentRequest request) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  💳 PAYMENT GATEWAY");
        System.out.println("═".repeat(60));
        System.out.println("  " + request);

        BasePaymentProcessor processor = findProcessor(request.getPaymentMethod());
        if (processor == null) {
            System.out.println("  ❌ No processor for: " + request.getPaymentMethod());
            return new PaymentResult("NONE", PaymentResult.Status.FAILED,
                    "No processor available for " + request.getPaymentMethod(),
                    request.getAmount(), request.getCurrency(), "Gateway");
        }

        return processor.process(request);
    }

    public RefundResult refund(String processorName, String transactionId, double amount) {
        System.out.println("\n  🔄 REFUND REQUEST — TxnID: " + transactionId);
        BasePaymentProcessor processor = processors.stream()
                .filter(p -> p.getProviderName().equals(processorName))
                .findFirst().orElse(null);

        if (processor == null) {
            return new RefundResult(false, "N/A",
                    "Processor not found: " + processorName, 0);
        }

        RefundResult result = processor.refund(transactionId, amount);
        logger.logRefund(result);
        return result;
    }

    private BasePaymentProcessor findProcessor(String paymentMethod) {
        return processors.stream()
                .filter(p -> p.supports(paymentMethod))
                .findFirst().orElse(null);
    }

    public void printSummary() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  📊 GATEWAY SUMMARY");
        System.out.println("═".repeat(60));
        for (BasePaymentProcessor p : processors) {
            long  successCount = p.getHistory().stream()
                    .filter(PaymentResult::isSuccess).count();
            System.out.printf("  %-12s: %d transactions | $%.2f processed%n",
                    p.getProviderName(), p.getHistory().size(),
                    p.getTotalProcessed());
        }
        System.out.println("═".repeat(60));
    }
}