import java.util.function.Supplier;

public enum Cell implements Supplier<Cell> {
    EMPTY, WALL, PATH;

    @Override
    public Cell get() {
        return EMPTY;
    }

    @Override
    public String toString() {
        return switch (this) {
            case EMPTY -> "#";
            case WALL -> "#";
            case PATH -> ".";
        };
    }

}
