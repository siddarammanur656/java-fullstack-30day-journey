//public class Book {
public class Book {
    private String Title;
    private String Author;
    private int Year;
    private double Price;

    public Book(String Title,String Author,int Year, double Price ){
        this.Title=Title;
        this.Author=Author;
        this.Year=Year;
        this.Price=Price;

    }
    public String toString(){
        return String.format("Book {\n Title: '%s', \n Author: '%s',\n Year: %d,\n Price: $%.2f \n}",Title,Author,Year,Price);
    }

    public static void main(String[] args) {
        Book b= new Book("Sotavana Savira Kathegalu","Rannaraja",2014,240);
        System.out.println(b);// calls toString() automatically
        System.out.println("Book: " + b);// string concatenation calls toString()


    }
}