import java.util.function.Supplier;

public enum Cell implements Supplier<Cell> {
    WALL, PATH;

    @Override
    public String toString() {
        return switch (this) {
            case WALL -> "#";
            case PATH -> ".";
        };
    }

    @Override
    public Cell get() {
        return WALL;
    }
}
