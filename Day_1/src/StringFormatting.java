public class StringFormatting {
    public static void main(String[] args) {
        String name="Alice";
        int age=25;
        double score=95.678;

        //String .format(classic)
        String msg1= String.format("Name: %s, Age: %d, Score: %.2f",name,age,score);
        System.out.println(msg1); //Name: Alice, Age: 25, Score: 95.68

        //Text blocks (java 13+)-great for JSON, HTML,SQL
        String json = """
                {
                    "name": "%s",
                    "age": %d
                }
                """.formatted(name, age);
        System.out.println(json);

        //printf for direct output
        System.out.printf("%-15s %5d %8.2f%n",name,age,score);
    }
}