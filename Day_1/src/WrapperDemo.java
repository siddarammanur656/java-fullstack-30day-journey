public class WrapperDemo {
    public static void main(String[] args) {
        //Each Primitive has a Wrapper class (capital letter)
        Integer a=42;  //int->Integer
        Double b=3.14; // double->Double
        Boolean c= true; //boolean->Boolean

        //AutoBoxing : Java auto-converts primitive <-> objects
        int primitive=5;
        Integer wrapped = primitive; //autoboxing(primitive -> object)
        int BackAgain=wrapped; //Auto-unboxing(object -> primitive)

        //Useful methods on wrapper classes
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Double.parseDouble("3.14"));
    }
}