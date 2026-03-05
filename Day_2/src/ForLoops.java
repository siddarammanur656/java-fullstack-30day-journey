//for Loop — When You Know the Count
public class ForLoops {
    public static void main(String[] args) {
        //Basic for loop - 3 parts init; condition; update
        for (int i = 0; i < 5; i++) {
            System.out.print(i+" ");
        }

        System.out.println();

        //Counting Backwards
        for (int i = 10; i >=1 ; i--) {
            System.out.print(i+" ");
        }

        System.out.println();

        //step by 2
        for (int i = 0; i <=20; i+=2) {
            System.out.print(i+" ");
        }

        System.out.println("\n-------------");

        //Nested loops - multiplication table
        for (int i = 1; i <=10 ; i++) {
            for (int j = 1; j <=10; j++) {
                System.out.printf("%4d",i*j); // right-aligned, 4 chars wide
            }
            System.out.println();
        }
    }
}