import java.util.Scanner;

//while & do-while — When You Don't Know the Count
public class WhileLoops {
    public static void main(String[] args) {
        //while - checks conditions before executing
        int count = 1;
        while(count<=5){
            System.out.println(count+" ");
            count++;
        }

        //do-while - executes At least once, checks condition after
        int num=10;
        do{
            System.out.println("Run at least once: "+num);
            num++;
        }while(num<5); //condition is false - but body ran once

        //real use case: menu that must show at least once
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("1. Play  2. Settings  3. Quit");
            System.out.println("Choice: ");
            choice = sc.nextInt();
        }while(choice !=3);
    }
}