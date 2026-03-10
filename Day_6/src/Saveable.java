// ── INTERFACE: Saveable ────────────────────────────────────────
public interface Saveable {
    void save(String filename);
    void load(String filename);

    default String getDefaultExtension() {
        return ".dat";
    }
}