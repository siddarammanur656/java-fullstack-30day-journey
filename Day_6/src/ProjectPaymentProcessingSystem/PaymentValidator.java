package ProjectPaymentProcessingSystem;

interface PaymentValidator {
    ValidationResult validate(PaymentRequest request);
}