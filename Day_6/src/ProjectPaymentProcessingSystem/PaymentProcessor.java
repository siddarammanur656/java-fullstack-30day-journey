package ProjectPaymentProcessingSystem;
//INTERFACES — Contracts (ISP: each has one purpose)
interface PaymentProcessor {
    PaymentResult process(PaymentRequest request);
    boolean       supports(String paymentMethod);
    String        getProviderName();
}