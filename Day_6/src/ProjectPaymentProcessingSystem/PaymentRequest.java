package ProjectPaymentProcessingSystem;

import java.util.HashMap;
import java.util.Map;

class PaymentRequest {
    private final String customerId;
    private final double amount;
    private final String currency;
    private final String paymentMethod;
    private final Map<String, String> metadata;

    public PaymentRequest(String customerId, double amount, String currency, String paymentMethod) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID required");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException("Invalid currency code");
        }

        this.customerId    = customerId;
        this.amount        = amount;
        this.currency      = currency.toUpperCase();
        this.paymentMethod = paymentMethod;
        this.metadata      = new HashMap<>();
    }

    public void addMetadata(String key, String value) {
        metadata.put(key, value);
    }

    public String  getCustomerId()    { return customerId; }
    public double  getAmount()        { return amount; }
    public String  getCurrency()      { return currency; }
    public String  getPaymentMethod() { return paymentMethod; }
    public String  getMetadata(String key) { return metadata.getOrDefault(key, ""); }

    @Override
    public String toString() {
        return String.format("PaymentRequest{customer=%s, amount=%.2f %s, method=%s}",
                              customerId, amount, currency, paymentMethod);
    }
}