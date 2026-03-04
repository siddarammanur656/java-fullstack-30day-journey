import java.util.Scanner;

public class Calculator {
    //Methods for each operation-practice method structure
    static double add(double a, double b){
        return a+b;
    }
    static double subtract(double a, double b){
        return a-b;
    }
    static double multiply(double a, double b){
        return a*b;
    }
    static double divide(double a, double b){
        if(b==0){
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;  // Not a Number- a valid  sentinel value
        }
        return a/b;
    }
    static double modulo(double a, double b){
        if(b==0){
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;  // Not a Number- a valid  sentinel value
        }
        return a%b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("╔══════════════════════╗");
        System.out.println("║   Java Calculator    ║");
        System.out.println("╚══════════════════════╝");

        while (keepRunning) {
            System.out.println("\nOperations: +  -  *  /  %  quit");
            System.out.print("Enter first number: ");

            // Validate first number
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid! Enter a number: ");
                scanner.next();
            }
            double num1 = scanner.nextDouble();

            System.out.print("Enter operator: ");
            String operator = scanner.next();

            if (operator.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            System.out.print("Enter second number: ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid! Enter a number: ");
                scanner.next();
            }
            double num2 = scanner.nextDouble();

            double result;

            switch (operator) {
                case "+" -> result = add(num1, num2);
                case "-" -> result = subtract(num1, num2);
                case "*" -> result = multiply(num1, num2);
                case "/" -> result = divide(num1, num2);
                case "%" -> result = modulo(num1, num2);
                default  -> {
                    System.out.println("Unknown operator: " + operator);
                    continue; // skip to next loop iteration
                }
            }

            if (!Double.isNaN(result)) {
                System.out.printf("Result: %.4f %s %.4f = %.4f%n",
                        num1, operator, num2, result);
            }
        }

        scanner.close();
    }
}