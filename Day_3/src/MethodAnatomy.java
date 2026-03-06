public class MethodAnatomy {

    //static method - belongs to the class , not an object
    //We use static method in main() context because main() itself is static

    static double calculateAverage(int [] marks){
        double sum=0;
        for (int i = 0; i < marks.length; i++) {
            sum=sum+marks[i];
        }
        double avg=sum/ marks.length;
        return avg;
    }

    static void printWelcome(String name){
        //void means "returns nothing"
        System.out.println("Welcome ,"+name+"!");
    }


    public static void main(String[] args) {
        int [] marks ={85,92,78,96,88};

        //Calling the method Execution jumps to calculateAverage
        System.out.println(calculateAverage(marks));

        //calling printWelcome method
        printWelcome("Siddarama");
    }
}