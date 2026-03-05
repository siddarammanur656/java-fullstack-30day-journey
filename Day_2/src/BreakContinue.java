//break, continue, and Labels
public class BreakContinue {
    public static void main(String[] args) {
        //break -  exit loop immediately
        for (int i = 0; i <10; i++) {
            if(i==5){
                break;
            }
            System.out.print(i +" ");
        }

        System.out.println();

        //continue - skip the current iteration and continue the loop
        for (int i = 0; i < 10; i++) {
            if(i%2==0){
                continue;
            }
            System.out.print(i+" ");
        }

        System.out.println();

        //Labeled break - break out of outer loop from inner loop
        outer:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(i==2 && j==2){
                    System.out.println("\n Breaking oouter loop at i=2, j=2");
                    break outer;
                }
                System.out.println("("+i+","+j+")");
            }
        }
    }
}