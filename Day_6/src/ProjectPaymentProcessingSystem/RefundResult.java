package ProjectPaymentProcessingSystem;

class RefundResult {
    private final boolean success;
    private final String  refundId;
    private final String  message;
    private final double  refundedAmount;

    public RefundResult(boolean success, String refundId,
                        String message, double refundedAmount) {
        this.success        = success;
        this.refundId       = refundId;
        this.message        = message;
        this.refundedAmount = refundedAmount;
    }

    public boolean isSuccess()        { return success; }
    public String  getRefundId()      { return refundId; }
    public String  getMessage()       { return message; }
    public double  getRefundedAmount(){ return refundedAmount; }

    @Override
    public String toString() {
        return String.format("  Refund[%s] %s | %.2f refunded | %s",
                success ? "✅" : "❌", refundId, refundedAmount, message);
    }
}