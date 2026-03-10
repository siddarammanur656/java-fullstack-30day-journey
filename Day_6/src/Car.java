// ── CONCRETE SUBCLASS: Car
public class Car extends Vehicle {

    private int    numDoors;
    private String transmission; // Manual / Automatic

    public Car(String brand, String model, int year, int numDoors, String transmission) {
        super(brand, model, year); // parent constructor first
        this.numDoors     = numDoors;
        this.transmission = transmission;
    }

    // ── IMPLEMENT all abstract methods
    @Override
    public void startEngine() {
        engineOn = true;
        System.out.println("  🚗 " + brand + " " + model + ": Vroom! Engine started.");
    }

    @Override
    public void stopEngine() {
        engineOn = false;
        System.out.println("  🚗 " + brand + " " + model + ": Engine stopped.");
    }

    @Override
    public double getFuelConsumption() { return 8.5; } // 8.5L/100km

    @Override
    public String getVehicleType()     { return "Car"; }

    @Override
    public int getPassengerCapacity()  { return numDoors == 2 ? 2 : 5; }

    @Override
    protected double getTankCapacity() { return 55.0; } // 55L tank

    // Car-specific
    public String getTransmission()    { return transmission; }
    public int    getNumDoors()        { return numDoors; }
}