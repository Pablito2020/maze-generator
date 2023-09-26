import java.util.Set;

public enum SquarePosition {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;


    public Set<Position> getNeighBours(Position position) {
        return switch (this) {
            case TOP_LEFT:
                yield Set.of(
                        new Position(position.row(), position.column() + 1),
                        new Position(position.row() + 1, position.column() + 1),
                        new Position(position.row() + 1, position.column())
                );
            case TOP_RIGHT:
                yield Set.of(
                        new Position(position.row(), position.column() - 1),
                        new Position(position.row() + 1, position.column() - 1),
                        new Position(position.row() +1, position.column())
                );
            case BOTTOM_LEFT:
                yield Set.of(
                        new Position(position.row(), position.column() + 1),
                        new Position(position.row() - 1, position.column()),
                        new Position(position.row() - 1, position.column() + 1)
                );
            case BOTTOM_RIGHT:
                yield Set.of(
                        new Position(position.row() - 1, position.column() - 1),
                        new Position(position.row() - 1, position.column()),
                        new Position(position.row(), position.column() - 1)
                );
        };
    }

}

