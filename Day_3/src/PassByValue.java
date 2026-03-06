//Case 1: Primitive — a COPY is passed
public class PassByValue {

    static void tryToChange(int x){
        x=999;
        System.out.println("Inside method: "+x); //999
    }

    //- With objects, the reference (memory address) is copied.
    //That means methods can change the contents of the object, but not which object the variable points to.
    static void changeName(StringBuilder sb) {
        sb.append(" World");
        System.out.println(sb);
    }


    public static void main(String[] args) {
        int num=42;
        tryToChange(num);
        System.out.println("After method: "+num);  //42


        StringBuilder text = new StringBuilder("Hello");
        changeName(text);
        System.out.println(text); // "Hello World"
    }
}