package board;

public record Position(
        int row, int column
)implements Comparable<Position> {
    public Position applyDirection(Direction direction) {
        return switch (direction) {
            case UP -> new Position(row - 1, column);
            case DOWN -> new Position(row + 1, column);
            case LEFT -> new Position(row, column - 1);
            case RIGHT -> new Position(row, column + 1);
        };
    }

    // Comparable implementation for board.Position
    @Override
    public int compareTo(Position other) {
        if (row() == other.row())
            return column() - other.column();
        return row() - other.row();
    }

    public Position plus(Position pos) {
        return new Position(row() + pos.row(), column() + pos.column());
    }
}
