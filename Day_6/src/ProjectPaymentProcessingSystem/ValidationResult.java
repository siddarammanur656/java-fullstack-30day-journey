package ProjectPaymentProcessingSystem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ValidationResult {
    private final boolean valid;
    private final List<String> errors;

    public ValidationResult(boolean valid, List<String> errors) {
        this.valid  = valid;
        this.errors = errors;
    }

    public static ValidationResult ok() {
        return new ValidationResult(true, Collections.emptyList());
    }

    public static ValidationResult fail(String... errors) {
        return new ValidationResult(false, Arrays.asList(errors));
    }

    public boolean isValid()  {
        return valid;
    }
    public List<String> getErrors(){
        return errors;
    }
}