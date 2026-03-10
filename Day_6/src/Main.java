public class Main {

    public static void main(String[] args) {

        System.out.println("🚘 VEHICLE SYSTEM DEMO");
        System.out.println("================================");

        // Create objects
        Car car = new Car("Toyota", "Camry", 2022, 4, "Automatic");

        ElectricCar ev = new ElectricCar("Tesla", "Model 3", 2023, 75.0, 500);

        Truck truck = new Truck("Volvo", "FH16", 2021, 25.0, 4);

        // Store in Vehicle reference (Polymorphism)
        Vehicle[] vehicles = { car, ev, truck };

        for (Vehicle v : vehicles) {

            System.out.println("\n--------------------------------");
            System.out.println(v);

            // Start engine
            v.startEngine();

            // Drive
            v.drive(50);

            // Refuel / Charge example
            if (v instanceof ElectricCar) {
                ((ElectricCar) v).charge(2);
            } else {
                v.refuel(0.2);
            }

            // Service check
            v.performServiceCheck();

            // Stop engine
            v.stopEngine();
        }

        System.out.println("\n================================");
        System.out.println("✅ Demo Completed");
    }
}