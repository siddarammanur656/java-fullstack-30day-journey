// ── CONCRETE SUBCLASS: ElectricCar
public class ElectricCar extends Vehicle {

    private double batteryCapacityKwh;
    private double chargeLevel; // 0.0 to 1.0
    private int    rangeKm;

    public ElectricCar(String brand, String model, int year, double batteryKwh, int rangeKm) {
        super(brand, model, year);
        this.batteryCapacityKwh = batteryKwh;
        this.chargeLevel        = 1.0;
        this.rangeKm            = rangeKm;
        this.fuelLevel          = 1.0; // reuse fuelLevel as chargeLevel
    }

    @Override
    public void startEngine() {
        engineOn = true;
        System.out.println("  ⚡ " + brand + " " + model + ": Silent start. Motor ready.");
    }

    @Override
    public void stopEngine() {
        engineOn = false;
        System.out.println("  ⚡ " + brand + " " + model + ": Motor stopped silently.");
    }

    @Override
    public double getFuelConsumption() {
        return 0; // electric — no fuel!
    }

    @Override
    public String getVehicleType()    { return "Electric Car"; }

    @Override
    public int getPassengerCapacity() { return 5; }

    // Override to use charge-based driving
    @Override
    public void drive(double distanceKm) {
        if (!engineOn) {
            System.out.println("  ⚠ Motor is off!"); return;
        }
        double range = chargeLevel * rangeKm;
        if (distanceKm > range) {
            System.out.printf("  ⚠ Not enough charge! Range: %.0fkm%n", range);
            return;
        }
        chargeLevel -= distanceKm / rangeKm;
        fuelLevel = chargeLevel;
        System.out.printf("  ⚡ %s %s drove %.1fkm. Charge: %.0f%%%n", brand, model, distanceKm, chargeLevel * 100);
    }

    public void charge(double hours) {
        chargeLevel = Math.min(1.0, chargeLevel + hours * 0.2);
        fuelLevel   = chargeLevel;
        System.out.printf("  🔋 Charged for %.1fh. Level: %.0f%%%n", hours, chargeLevel * 100);
    }

    // Override service check for EV-specific steps
    @Override
    protected void checkEngine() {
        System.out.println("  [✓] Electric Motor: OK");
        System.out.printf ("  [✓] Battery: %.0f%% capacity%n",
                            chargeLevel * 100);
    }

    public double getBatteryCapacity() { return batteryCapacityKwh; }
    public int    getRangeKm()         { return rangeKm; }
}