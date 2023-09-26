package board;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze {

    private final static int MINIMUM_ROW_SIZE = 3;
    private final static int MINIMUM_COLUMN_SIZE = 3;

    private final Board board;

    public Maze(int rows, int columns) {
        this.board = new Board(rows, columns);
    }

    public Set<Position> addCycle() {
        var positionsCycle = getCycle();
        positionsCycle.forEach(this::setAsPath);
        return positionsCycle;
    }

    private Set<Position> getCycle() {
        var cyclePosition = initialCyclePosition();
        var donut = new Donut(cyclePosition, MINIMUM_ROW_SIZE);
        return donut.getPositions();
    }

    private Position initialCyclePosition() {
        var rand = new Random();
        var row = rand.nextInt(0, board.getRows() - MINIMUM_ROW_SIZE);
        var column = rand.nextInt(0, board.getColumns() - MINIMUM_COLUMN_SIZE);
        return new Position(row, column);
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

    public void setAsPath(Position position) {
        if (board.get(position) == Cell.PATH)
            throw new IllegalArgumentException("The position is already occupied");
        board.set(position, Cell.PATH);
    }
    
    public boolean positionIsPath(Position position) {
        return board.get(position) == Cell.PATH;
    } 

    public void print() {
        board.print();
    }
}