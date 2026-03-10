package ProjectPaymentProcessingSystem;
//VALIDATOR IMPLEMENTATION (SRP: only validates)
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class PaymentValidatorImpl implements PaymentValidator {

    private static final double MAX_SINGLE_PAYMENT = 500_000.00;
    private static final Set<String> SUPPORTED_CURRENCIES =
            Set.of("USD", "EUR", "GBP", "INR", "JPY");

    @Override
    public ValidationResult validate(PaymentRequest request) {
        List<String> errors = new ArrayList<>();

        if (request.getAmount() > MAX_SINGLE_PAYMENT)
            errors.add("Amount exceeds maximum $" + MAX_SINGLE_PAYMENT);

        if (!SUPPORTED_CURRENCIES.contains(request.getCurrency()))
            errors.add("Unsupported currency: " + request.getCurrency());

        if (request.getCustomerId().length() < 3)
            errors.add("Invalid customer ID");

        return errors.isEmpty()
               ? ValidationResult.ok()
               : ValidationResult.fail(errors.toArray(new String[0]));
    }
}
