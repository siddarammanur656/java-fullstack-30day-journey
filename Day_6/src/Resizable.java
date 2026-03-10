// ── INTERFACE: Resizable ───────────────────────────────────────
public interface Resizable {
    void   resize(double factor);
    double getWidth();
    double getHeight();

    default double getAspectRatio() {
        return getWidth() / getHeight();
    }

    default boolean isLandscape() {
        return getWidth() > getHeight();
    }
}