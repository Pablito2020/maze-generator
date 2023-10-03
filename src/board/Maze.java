package board;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze {
    final Board board;

    public Maze(int rows, int columns) {
        this.board = new Board(rows, columns);
    }

    public Set<Direction> getDirectionsFrom(Position position) {
        return Stream.of(Direction.values())
                .filter(direction -> isPositionValid(position.applyDirection(direction)))
                .collect(Collectors.toSet());
    }

    private boolean isPositionValid(Position position) {
        return board.isValid(position) && !createsPathSquare(position);
    }

    private boolean createsPathSquare(Position position) {
        return Arrays.stream(SquarePosition.values()).anyMatch(squarePosition -> {
            var neighBours = squarePosition.getNeighBours(position);
            return neighBours.stream().allMatch(neighBour -> board.isValid(neighBour) && board.get(neighBour) == Cell.PATH);
        });
    }

    public void setAsWalked(Position position) {
        if (board.get(position) == Cell.PATH)
            throw new IllegalArgumentException("The position is already occupied");
        board.set(position, Cell.PATH);
    }

    public boolean positionIsPath(Position position) {
        return board.get(position) == Cell.PATH;
    }

    public Board getBoard() {
        return this.board.deepCopy();
    }

    public boolean walkedIsBelowThreshold(float threshold) {
        var pathCells = board.getAllCells().stream().filter(cell -> cell == Cell.PATH).count();
        var allCells = board.getSize();
        return (float) pathCells / allCells < threshold;
    }

}