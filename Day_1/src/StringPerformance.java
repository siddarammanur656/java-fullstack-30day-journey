public class StringPerformance {
    public static void main(String[] args) {
        //Bad for Strings  because it creates thousands of strings objects
        String result="";
        for (int i = 0; i < 1000; i++) {
            result +=i;  //Each += creates a new string object in memory
        }
        System.out.println(result);

        //Good : StringBuilder mutates in-place, much faster
        //Means StringBuilder allows to modify the string it does not creates new  objects instead it modify the existing String object
        StringBuilder sb=  new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
        }
        String finalResult = sb.toString();
        System.out.println(finalResult);

        //StringBuilder useful methods
        StringBuilder greeting = new StringBuilder("Hello");
        greeting.append(", World"); // Hello, World
        greeting.insert(5,"there"); // Hello there, World
        greeting.reverse();  // dlroW ,ereht olleH
        greeting.delete(0,5);// , ereht olleH
        System.out.println(greeting.toString());
    }
}