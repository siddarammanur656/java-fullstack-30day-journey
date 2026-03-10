public class LambdaDemo {
    public static void main(String[] args) {
        MyInterface add=(a,b)-> a+b;
        MyInterface multifly=(a,b)->a*b;
        System.out.println("Sum: "+add.operation(20,40));
        System.out.println("Product: "+multifly.operation(100,10));
    }
}
