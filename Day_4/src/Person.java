//Same name as the class
//No return type (not even void)
//Called only once — when object is created with new
//If you write no constructor, Java provides a default no-arg one
//Once you write ANY constructor, Java removes the default one

public class Person {
    private String name;
    private int  age;
    private String email;
    private String role;

    //Types of constructor
    //type 1: No-arg Constructor
    //used when you want to create an empty object and set fields later
    public Person(){
        this.name="Unknown";
        this.age=0;
        this.email="";
        this.role="Guest";

        System.out.println("No-arg constructor called");
    }

    //Type 2:Parameterized constructor
    //Most  common - initialization with specific values at creation
    public Person(String name, int age){
        this.name  = name;
        this.age   = age;
        this.email = "";
        this.role  = "User";
        System.out.println("2-param constructor called");
    }
    //Type 3: Full constructor
    //All fields provided - complete initialization
    public Person(String name, int  age , String email,String role){
        this.name  = name;
        this.age   = age;
        this.email = "";
        this.role  = "User";
        System.out.println("Full constructor called");
    }

    //type 4:Copy Constructor
    //Creates a new object as a copy of an existing one
    public Person(Person other){
        this.name=other.name;
        this.age=other.age;
        this.email=other.email;
        this.role=other.role;
        System.out.println("Copy constructor called");
    }

    public String toString(){
        return  "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Email: " + email + "\n" +
                "Role: " + role;

    }
    public static void main(String[] args) {
        Person p1 = new Person();                            // No-arg
        Person p2 = new Person("Alice", 25);                 // 2-param
        Person p3 = new Person("Bob", 30, "bob@x.com", "Admin"); // Full
        Person p4 = new Person(p3);                          // Copy
        System.out.println("---------------------------");
        System.out.println(p1);
        System.out.println("---------------------------");
        System.out.println(p2);
        System.out.println("---------------------------");
        System.out.println(p3);
        System.out.println("---------------------------");
        System.out.println(p4);
        System.out.println("---------------------------");
        //Prove copy is Independent
        p4.name="Siddarama";
        System.out.println(p3.name);//Bob - Original unchanged
        System.out.println(p4.name);//Siddarama -copy changed
    }
}