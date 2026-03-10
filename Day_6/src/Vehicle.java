// Abstract Class — Complete Example with All Features
// ── ABSTRACT CLASS: Vehicle
public abstract class Vehicle {

    // ── FIELDS — can have state (unlike interfaces)
    protected String brand;
    protected String model;
    protected int    year;
    protected double fuelLevel;   // 0.0 to 1.0
    protected boolean engineOn;

    // ── CONSTRUCTOR — called by subclasses via super() ─────────
    public Vehicle(String brand, String model, int year) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand required");
        }
        if (year < 1886 || year > 2100) { // first car was 1886
            throw new IllegalArgumentException("Invalid year: " + year);
        }
        this.brand     = brand;
        this.model     = model;
        this.year      = year;
        this.fuelLevel = 1.0;  // start full
        this.engineOn  = false;
    }

    // ABSTRACT METHODS — no body, MUST be overridden
    // Each vehicle type has its own way of doing these:
    public abstract void startEngine();
    public abstract void stopEngine();
    public abstract double getFuelConsumption(); // liters per 100km
    public abstract String getVehicleType();
    public abstract int    getPassengerCapacity();

    // CONCRETE METHODS — shared logic, subclasses inherit
    public void refuel(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Fuel amount must be positive");
        }
        fuelLevel = Math.min(1.0, fuelLevel + amount);
        System.out.printf("  %s %s refueled. Level: %.0f%%%n",
                           brand, model, fuelLevel * 100);
    }

    public boolean canDrive(double distanceKm) {
        double fuelNeeded = (getFuelConsumption() * distanceKm) / 100;
        double fuelAvailable = fuelLevel * getTankCapacity();
        return fuelAvailable >= fuelNeeded;
    }

    public void drive(double distanceKm) {
        if (!engineOn) {
            System.out.println("  ⚠ Engine is off! Start it first.");
            return;
        }
        if (!canDrive(distanceKm)) {
            System.out.println("  ⚠ Not enough fuel!");
            return;
        }
        double consumed = (getFuelConsumption() * distanceKm) / 100;
        fuelLevel -= consumed / getTankCapacity();
        System.out.printf("  %s %s drove %.1fkm. Fuel used: %.2fL. Level: %.0f%%%n",
                           brand, model, distanceKm, consumed, fuelLevel * 100);
    }

    // Template Method Pattern — defines algorithm, subclasses fill steps
    public final void performServiceCheck() {
        System.out.println("\n   SERVICE CHECK: " + brand + " " + model);
        System.out.println("  ─────────────────────────────");
        checkEngine();       // abstract or overridable step
        checkBrakes();       // abstract or overridable step
        checkFluids();       // concrete shared step
        checkTires();        // concrete shared step
        System.out.printf("   Service complete for %s%n", getVehicleType());
    }

    // These can be overridden for vehicle-specific checks
    protected void checkEngine() {
        System.out.println("  [✓] Engine: OK");
    }
    protected void checkBrakes() {
        System.out.println("  [✓] Brakes: OK");
    }
    private void checkFluids() { // private — not overridable
        System.out.println("  [✓] Fluids: OK");
    }
    private void checkTires() {
        System.out.println("  [✓] Tires: OK");
    }

    // Hook method — subclasses override to add extra checks
    protected double getTankCapacity() { return 50.0; } // default 50L

    // Getters
    public String  getBrand()    { return brand; }
    public String  getModel()    { return model; }
    public int     getYear()     { return year; }
    public double  getFuelLevel(){ return fuelLevel; }
    public boolean isEngineOn()  { return engineOn; }

    @Override
    public String toString() {
        return String.format("%s %s %s (%d) - Fuel: %.0f%%",
                getVehicleType(), brand, model, year, fuelLevel * 100);
    }
}