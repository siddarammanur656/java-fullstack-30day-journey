public class FinallyTrap {

    static String tricky(){
        try{
            return "from try";
        }finally {
            return "from finally";
        }
    }
    static void dangerous(){
        try {
            throw new RuntimeException("real error");
        }finally {
            return;
        }
    }

    public static void main(String[] args) {
        System.out.println(tricky());
        dangerous();
    }
}
