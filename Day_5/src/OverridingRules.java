public class OverridingRules {
    static class Animal{
        //Methods to override
        public Animal create(){
            return new Animal();
        }
        public void makeSound(){
            System.out.println("...");
        }
        protected int getLegs(){
            return 0;
        }
        public String toString(){
            return "Animal";
        }

        //these cannot be overridden
        private void secret(){
            // private — invisible to child
        }
        public static void breathe(){
            // static — hidden, not overridden
        }
        public final void alive(){
            // final — locked
        }
    }

    static class Dog extends Animal {
        // RULE 1: Same name, same parameters
        @Override
        public void makeSound() {
            System.out.println("Woof!");
        }
        // RULE 2: Return type must be same OR a subtype (covariant return)
        @Override
        public Dog create() {  // Dog IS-A Animal — covariant return
            return new Dog();
        }
        // RULE 3: Access modifier can only WIDEN, never restrict
        @Override
        public int getLegs() { // protected → public  (widened)
            return 4;
        }

        // public → protected would be a COMPILE ERROR

        // RULE 4: @Override annotation — always use it!
        // Catches typos at compile time (makeSound vs makesound)
        @Override
        public String toString() {
            return "Dog";
        }

        // RULE 5: Cannot override private/static/final
        // private void secret() {} ← this is a NEW method, not override
        // static void breathe() {} ← this is method HIDING, not override
        // void alive() {}          ← COMPILE ERROR

        // RULE 6: Overriding method can throw FEWER checked exceptions
        // (but not more) — covered in exceptions topic


    }


}