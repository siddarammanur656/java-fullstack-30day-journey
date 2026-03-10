// ── CONCRETE SUBCLASS: Truck
public class Truck extends Vehicle {

    private double payloadCapacityTons;
    private int    numAxles;

    public Truck(String brand, String model, int year, double payloadTons, int numAxles) {
        super(brand, model, year);
        this.payloadCapacityTons = payloadTons;
        this.numAxles            = numAxles;
    }

    @Override
    public void startEngine() {
        engineOn = true;
        System.out.println("  🚛 " + brand + " " + model + ": ROAR! Diesel engine started.");
    }

    @Override
    public void stopEngine() {
        engineOn = false;
        System.out.println("  🚛 " + brand + " " + model + ": Engine shutdown.");
    }

    @Override
    public double getFuelConsumption() { return 28.0; } // trucks drink fuel

    @Override
    public String getVehicleType()     { return "Truck"; }

    @Override
    public int getPassengerCapacity()  { return 2; } // cab only

    @Override
    protected double getTankCapacity() { return 300.0; } // big tank

    @Override
    protected void checkBrakes() {
        System.out.println("  [✓] Air brakes: OK");
        System.out.println("  [✓] Brake fluid: OK");
    }

    public double getPayloadCapacity() { return payloadCapacityTons; }
}