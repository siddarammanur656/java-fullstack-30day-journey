//Static Initializer Block & Constants
public class Config {

    // Constants — static final: ONE value, never changes
    public static final double PI          = 3.14159265358979;
    public static final int    MAX_USERS   = 1000;
    public static final String APP_NAME    = "MyApp";

    // Static field that needs complex initialization
    public static final java.util.Map<String, Integer> ERROR_CODES;

    // Static initializer block — runs ONCE when class is first loaded
    static {
        ERROR_CODES = new java.util.HashMap<>();
        ERROR_CODES.put("NOT_FOUND", 404);
        ERROR_CODES.put("UNAUTHORIZED", 401);
        ERROR_CODES.put("SERVER_ERROR", 500);
        ERROR_CODES.put("OK", 200);
        System.out.println("Config class loaded!");
    }

    public static void main(String[] args) {
        System.out.println(Config.APP_NAME);
        System.out.println(Config.ERROR_CODES.get("NOT_FOUND")); // 404
        System.out.println(Config.MAX_USERS);
    }
}