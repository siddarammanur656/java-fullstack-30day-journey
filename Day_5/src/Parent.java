class Parent {
    public void display() { System.out.println("Parent"); }
}
class Child extends Parent {

    //  Typo — this is a NEW method, NOT an override!
    // Code compiles but polymorphism won't work as expected
//    public void Display() {
//        System.out.println("Child");
//    }

    //  @Override catches the typo at COMPILE TIME
    //@Override
    public void Display() { } // COMPILE ERROR: method does not override
    //                          ← forces you to fix the typo
}