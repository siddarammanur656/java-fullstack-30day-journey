//functional interface is with exactly one abstract method
//it can have extra 'default' or 'static ' methods nut only one abstract method

public interface MathOperation {
    double operate(double a, double b);
}

//java already gives you many functional interfaces in java.util.function
//examples:
    // Predicate<T>  -> return true / false
    // Function<T,R> -> it transform input into output
    // Consumer<T>   -> It takes input, and does something  (no return)
    // Supplier<T>   -> It gives  you a value (no input)

@FunctionalInterface
interface Validator<T> {
    boolean validate(T value);
}

@FunctionalInterface
interface Transformer<T, R> {
    R transform(T input);
}





