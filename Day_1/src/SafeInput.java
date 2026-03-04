import java.util.Scanner;

public class SafeInput{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number: ");
        while(!sc.hasNextInt()){
            System.out.println("That's not a number! Try again: ");
            sc.next();
        }
        int number=sc.nextInt();
        System.out.println("You entered: "+number);

        sc.close();

    }
}