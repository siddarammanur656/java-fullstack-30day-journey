public class ArrayBasics {
    public static void main(String[] args) {
        //Declaration and initialization
        int [] scores=new int[5];
        scores[0]=85;
        scores[0]=92;
        scores[0]=78;
        scores[0]=96;
        scores[0]=88;

        //Shorthand initialization
        int [] grades={85,92,78,96,88};

        //Array properties
        System.out.println(grades.length);
        System.out.println(grades[0]);
        System.out.println(grades[grades.length-1]);

        // ⚠️ ArrayIndexOutOfBoundsException — most common array bug
        // System.out.println(grades[5]); // Exception! Valid indices: 0–4

        //Iterating And computing
        int sum=0;
        int max=grades[0];
        int min=grades[0];

        for (int grade:grades) {
            sum +=grade;
            if(grade>max){
                max=grade;
            }
            if(grade<min){
                min=grade;
            }
        }
        double avg=(double) sum/grades.length;

        System.out.println("-----------------");
        System.out.println("Sum:"+sum);
        System.out.println("Average: "+avg);
        System.out.println("Max:"+max);
        System.out.println("Min: "+min);

    }
}