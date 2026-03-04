public class StringMethods {
    public static void main(String[] args) {
        String s = "  Hello, Java World!  ";
        //Length and Access
        System.out.println(s.length());  //22
        System.out.println(s.charAt(9)); //J (0-indexed)
        System.out.println(s.indexOf("Java"));  //9
        System.out.println(s.contains("World!"));

        //Transformation (return New String - Strings are immutable)
        System.out.println(s);
        System.out.println(s.trim()); //removes whitespace
        System.out.println(s.strip()); //same but handles unicode spaces too
        System.out.println(s.toLowerCase()); //converts all uppercase to lowercase
        System.out.println(s.toUpperCase()); //converts lowercase to uppercase
        System.out.println(s.replace("Java","Spring")); //replace helps replace the word using another word

        //Substring
        String clean=s.trim();
        System.out.println(clean);
        System.out.println(clean.substring(7));  //prints substring from start index up to till end
        System.out.println(clean.substring(7,11)); // Prints Substring from start index to end index

        //Split - very useful for parsing
        String csv="Alice,25,Engineer";
        String [] parts=csv.split(",");
        System.out.println(parts[0]); //Alice
        System.out.println(parts[1]); //25
        System.out.println(parts[2]); //Engineer

        //Check start//end
        System.out.println(clean.startsWith("Hello")); //true
        System.out.println(clean.endsWith("!")); //true

        //isEmpty vs isBlank
        System.out.println("".isEmpty()); //true(length :0)
        System.out.println("  ".isEmpty()); //false (has whitespace)
        System.out.println("  ".isBlank()); //true(only whitespace) <- java 11+

    }
}