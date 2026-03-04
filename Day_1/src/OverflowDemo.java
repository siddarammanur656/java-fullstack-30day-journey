public class OverflowDemo {
    public static void main(String[] args) {
        int max=Integer.MAX_VALUE; //2,147,483,647
        System.out.println(max); //2147483647
        System.out.println(max+1);  // -2147483648 ← WRAPS AROUND!

        //Fix : use long when numbers might get large
        long safeMax=Integer.MAX_VALUE;
        System.out.println(safeMax+1);  //2147483648
    }
}