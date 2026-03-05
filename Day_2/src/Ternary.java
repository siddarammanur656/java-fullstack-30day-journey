public class Ternary {
    public static void main(String[] args) {
        int score=75;

        //condition ? valueIfTrue : valueIfFalse  ->Ternary operator One-liner Conditionals
        String result=(score>=60) ? "Pass" : "False";
        System.out.println(result);

        //Nested ternary -readable only for simple cases
        String grade=   score>=90 ?  "A"
                        :score >=80 ? "B"
                        :score >=70 ? "C"
                        :score >=60 ? "D"     : "F";
        System.out.println(grade);

        //Ternary in print
        int items=1;
        System.out.println("You have "+ items+" item"+(items==1 ? "":"s"));
        // "You have 1 item" (not "items")
    }
}