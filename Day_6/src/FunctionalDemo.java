public class FunctionalDemo{
    public static void main(String[] args) {
        //before java 8 , we had write like below syntax
        MathOperation addOld=new MathOperation() {
            @Override
            public double operate(double a, double b) {
                return a+b;
            }
        };

        //New way - Lambda (java 8+) Expressions
        //Lambda  expression provides implementation for functional interface without anonymous inner classes
        //below Each lambda defines how two numbers are combined
        MathOperation add      = (a, b) -> a + b;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation power    = (a, b) -> Math.pow(a, b);
        MathOperation max      = (a, b) -> Math.max(a, b);

        System.out.println(add.operate(10, 5));       // 15.0
        System.out.println(subtract.operate(10, 5));  // 5.0
        System.out.println(multiply.operate(10, 5));  // 50.0
        System.out.println(power.operate(2, 10));     // 1024.0
        System.out.println(max.operate(2,10));


        // Validator lambda
        Validator<String> notEmpty   = s -> !s.isBlank();
        Validator<Integer> positive  = n -> n > 0;
        Validator<String> validEmail = s -> s.contains("@") && s.contains(".");

        System.out.println(notEmpty.validate("hello"));      // true
        System.out.println(notEmpty.validate("   "));        // false
        System.out.println(positive.validate(-5));           // false
        System.out.println(validEmail.validate("a@b.com"));  // true


        // Transformer lambda
        Transformer<String, Integer> length  = s -> s.length();
        Transformer<String, String>  upper   = s -> s.toUpperCase();
        Transformer<Integer, String> label   = n -> "Value: " + n;

        System.out.println(length.transform("Hello"));       // 5
        System.out.println(upper.transform("java"));         // JAVA
        System.out.println(label.transform(42));             // Value: 42

        System.out.println("--------------------------------------------------");
        // Java's built-in functional interfaces (java.util.function)
        java.util.function.Predicate<String>  isEmpty  = String::isEmpty;
        java.util.function.Function<String,Integer> len = String::length;
        java.util.function.Consumer<String>   printer  = System.out::println;
        java.util.function.Supplier<String>   greeting = () -> "Hello World";

        printer.accept(greeting.get()); // Hello World
        System.out.println(len.apply("Java")); // 4

    }

}