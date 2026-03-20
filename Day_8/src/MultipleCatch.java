public class MultipleCatch {

    public static  void riskyMethod(int choice) throws Exception{
        switch (choice){
            case 1:throw new NullPointerException("Null Value");
            case 2:throw new ArrayIndexOutOfBoundsException("Bad index");
            case 3:throw new IllegalArgumentException("Bad argument");
            case 4:throw new Exception("Generic checked exception");
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i <=5; i++) {
            try {
                riskyMethod(i);
                System.out.println("Choice"+i+": No Exception");
            }catch (NullPointerException e){
                System.out.println("NPE: "+e.getMessage());
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Array: "+e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Illegal : "+e.getMessage());
            }catch (Exception e){
                System.out.println("Generic : "+e.getMessage());
            }

        }
    }
}
