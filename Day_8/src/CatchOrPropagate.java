import java.io.IOException;

public class CatchOrPropagate {

    //wrong-catching and doing nothing
    static void bad(){
        try{
            riskyOperation();
        }catch (Exception e){

        }
    }
    //wrong-catching too broadly too early
    static void alsoBad(){
        try{
            step1();
            step2();
            step2();
        }catch(Exception e){
            System.out.println("Something failed");
        }
    }


    // correct — catch only what you can handle at this level
    static String loadUserConfig(String userId) throws IOException {

        try {

            int id = Integer.parseInt(userId);

            return readConfigFile(id);

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException("Invalid user ID format: " + userId, e);
        }
        // IOException propagates naturally
    }

    //correct — catch, log, re-throw
    static void processPayment(double amount) throws Exception {

        try {

            executePayment(amount);

        } catch (Exception e) {

            System.err.println("[ERROR] Payment failed for amount: " + amount);

            throw e;
        }
    }

    static void riskyOperation() throws Exception {

    }
    static void step1() throws Exception {

    }
    static void step2() throws Exception {

    }
    static void step3() throws Exception {

    }
    static String readConfigFile(int id) throws IOException {
        return "";
    }
    static void executePayment(double a) throws Exception {

    }



    public static void main(String[] args) {
        // bad() swallows exceptions silently
        bad();
        System.out.println("bad() finished");

        // alsoBad() catches too broadly
        alsoBad();
        System.out.println("alsoBad() finished");

        // loadUserConfig with valid input
        try {
            System.out.println("Config: " + loadUserConfig("123"));
        } catch (Exception e) {
            System.out.println("Error loading config: " + e.getMessage());
        }

        // loadUserConfig with invalid input
        try {
            loadUserConfig("abc");
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // processPayment
        try {
            processPayment(100.0);
            System.out.println("Payment processed");
        } catch (Exception e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }
}
