public class TryCatchBasics {
    public static void main(String[] args) {

        try{
            int result=10/0;
            System.out.println(result);
        }catch (ArithmeticException e){
            System.out.println("Caught: "+e.getMessage());
            System.out.println("Type: "+e.getClass().getSimpleName());
        }
        System.out.println("Program continue...");
    }
}
