public class TypeCasting {
    public static void main(String[] args) {
        //Widening (Automatic)- smaller -> larger, noo data loss
        int i=100;
        long l=i;  // int -> long , automatic
        double d= l; //long -> double , automatic
        System.out.println(d);

        //narrowing (manual) - larger ->smaller, possible data loss
        double pi=3.99999;
        int piInt=(int)pi; //cast with (int)
        System.out.println(piInt);

        //real-world use case: getting a percentage
        int correct = 17;
        int total= 20;
        double percentage=(double) correct/total*100;
        System.out.println(percentage);
    }
}