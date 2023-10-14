package board;

import java.util.function.Supplier;

public enum Cell implements Supplier<Cell> {
    WALL, FOOD, PATH;

    @Override
    public String toString() {
        return switch (this) {
            case WALL -> "#";
            case PATH -> ".";
            case FOOD -> "*";
        };
    }

    public boolean isWalkable() {
        return this != WALL;
    }

    @Override
    public Cell get() {
        return WALL;
    }
}
