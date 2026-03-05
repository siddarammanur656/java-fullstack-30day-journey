public class ControlFlow {
    public static void main(String[] args) {
        int score = 85;

        //Basic if-else
        if(score>=90){
            System.out.println("Grade: A");
        }else if(score >=80){
            System.out.println("Grade: B");
        }else if(score>=70){
            System.out.println("Grade: C");
        }else if(score >=60){
            System.out.println("Grade: D");
        }else {
            System.out.println("Grade: F");
        }

        //Compound Conditions
        int age=20;
        boolean hasID=true;

        if(age>=18 && hasID){  //AND - Both must be true
            System.out.println("Entry Allowed");
        }

        boolean isWeekend=true;
        boolean isHoliday=true;
        if (isWeekend || isHoliday){ //OR-at least one true
            System.out.println("Day off!");
        }

        boolean isRaising = true;  //NOT
        if(!isRaising){
            System.out.println("Go outside!");
        }else {
            System.out.println("Stay in and code java");
        }
    }
}