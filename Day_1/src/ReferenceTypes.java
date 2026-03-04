public class ReferenceTypes {
    public static void main(String[] args) {
        //Primitive Values Stored directly in the variable
        int x=10;
        int y=x; //y-gets a copy of the value
        y=99;

        System.out.println(x); //10-x is unchanged

        //Reference - variable stores a MEMORY ADDRESS, not the value
        int [] arr1={1,2,3};
        int [] arr2=arr1; //arr2 points to the same array in memory
        arr2[0]=999;
        System.out.println(arr1[0]); //999 <- arr1 changed too
    }
}