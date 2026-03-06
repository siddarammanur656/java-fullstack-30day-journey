//Palindrome Checker — Recursion on Strings
public class PalindromeChecker {

    //Recursive: compare first and last char, then recurse on middle
    static boolean isPalindrome(String s){

        //Clean input
        s=s.toLowerCase().replaceAll("[^a-z0-9]","");

        //base cases
        if(s.length()<=1){
            return true;  //single char or empty = palindrome
        }
        //check outer chars
        if(s.charAt(0) != s.charAt(s.length()-1)){
            return false;
        }

        //Recurse on the substring without first and last characters
        return isPalindrome(s.substring(1,s.length()-1));
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));        // true
//        System.out.println(isPalindrome("hello"));          // false
//        System.out.println(isPalindrome("A man a plan a canal Panama")); // true
//        System.out.println(isPalindrome("Never odd or even")); // true
    }
}