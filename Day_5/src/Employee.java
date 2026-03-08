//Parent class
public class Employee {

    // protected — visible to same class , same package subclasses (unlike private)
    protected String name;
    protected int    age;
    protected String email;
    protected double salary;

    public Employee(String name,int age, String email,double salary){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Name is Required");
        }
        if (age < 18 || age > 100)
            throw new IllegalArgumentException("Age must be 18–100");
        if (!email.contains("@"))
            throw new IllegalArgumentException("Invalid email");
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative");

        this.name   = name;
        this.age    = age;
        this.email  = email;
        this.salary = salary;
    }
    //Methods
    public void work() {
        System.out.println(name + " is working...");
    }
    public double calculateBonus() {
        return salary * 0.05; // default 5%
    }
    public void displayInfo() {
        System.out.printf("%-15s | Age: %2d | Email: %-25s | Salary: $%,.2f%n",
                name, age, email, salary);
    }
    // Getters
    public String getName(){
        return name;
    }
    public int    getAge(){
        return age;
    }
    public String getEmail(){
        return email;
    }
    public double getSalary(){
        return salary;
    }
    // Setter with validation
    public void setSalary(double salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }

    public String toString() {
        return String.format("Employee{name='%s', age=%d, salary=$%.2f}",
                name, age, salary);
    }

}