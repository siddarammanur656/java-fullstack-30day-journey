// ── SIDE BY SIDE COMPARISON ────────────────────────────────────

// Abstract class — when there's shared state + partial behavior
abstract class DatabaseConnection {
    protected String url;          // state — shared
    protected String username;     // state — shared
    protected boolean connected;   // state — shared

    public DatabaseConnection(String url, String username) {
        this.url      = url;
        this.username = username;
    }

    // Shared behavior — every DB connection does this same way
    public void logQuery(String sql) {
        System.out.println("[" + username + "@" + url + "] " + sql);
    }

    // Abstract — each DB type implements differently
    public abstract void connect();
    public abstract void disconnect();
    public abstract Object executeQuery(String sql);
}

// Interface — when only a CONTRACT is needed, across unrelated classes
interface Loggable {
    void log(String message);
    void logError(String error);

    default void logInfo(String info) {
        log("[INFO] " + info);
    }
}

// Now: DatabaseConnection can be abstract class AND implement Loggable
class MySQLConnection extends DatabaseConnection implements Loggable {
    public MySQLConnection(String url, String username) {
        super(url, username);
    }

    @Override
    public void   connect(){
        connected = true;  System.out.println("MySQL connected");
    }
    @Override
    public void   disconnect(){
        connected = false; System.out.println("MySQL disconnected");
    }
    @Override
    public Object executeQuery(String sql){
        logQuery(sql); return "MySQL result";
    }
    @Override
    public void   log(String message){
        System.out.println("[MYSQL] " + message);
    }
    @Override
    public void   logError(String error){
        System.out.println("[MYSQL][ERROR] " + error);
    }
}