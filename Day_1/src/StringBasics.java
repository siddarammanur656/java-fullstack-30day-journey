public class StringBasics {
    public static void main(String[] args) {
        String name="Alice";  //Strings literal are stored in String pool
        String copy="Alice";  //reuse the Same object in pool
        String obj = new String("Alice"); //Forces new object on heap memory

        // == compares  References(Memory addresses), not values
        System.out.println(name==copy); //true (same pool object)
        System.out.println(name==obj);  //false(different objects)
        System.out.println(name.equals(obj));  //true always use equals()
        System.out.println(name.equalsIgnoreCase("alice"));  //true
    }
}