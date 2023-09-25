import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze {

    private final static int MINIMUM_ROW_SIZE = 3;
    private final static int MINIMUM_COLUMN_SIZE = 3;
    private final List<List<Cell>> maze;

    public Maze(int rows, int columns) {
        assertSize(rows, columns);
        this.maze = new ArrayList<>();
        populateMaze(rows, columns);
    }


    // The minimum size should be 3x3 because of the cycle restriction
    private void assertSize(int rows, int columns) {
        if (rows < MINIMUM_ROW_SIZE || columns < MINIMUM_COLUMN_SIZE)
            throw new IllegalArgumentException("The minimum size should be 3x3");
    }

    private void populateMaze(int rows, int columns) {
        for (int i = 0; i < rows; i++)
            maze.add(Stream.generate(Cell.WALL).limit(columns).collect(Collectors.toList()));
    }

    public Set<Position> addCycle() {
        var positionsCycle = getCycle();
        positionsCycle.forEach(position -> setPosition(position, Cell.PATH));
        return positionsCycle;
    }

    private Set<Position> getCycle() {
        var cyclePosition = initialCyclePosition();
        var donut = new Donut(cyclePosition, MINIMUM_ROW_SIZE);
        return donut.getPositions();
    }

    private Position initialCyclePosition() {
        var rand = new Random();
        var row = rand.nextInt(0, maze.size() - MINIMUM_ROW_SIZE);
        var column = rand.nextInt(0, maze.size() - MINIMUM_COLUMN_SIZE);
        return new Position(row, column);
    }

    public Set<Direction> getDirectionsFrom(Position position) {
        return Stream.of(Direction.values())
                .filter(direction -> isPositionValid(position.applyDirection(direction)))
                .collect(Collectors.toSet());
    }

    private boolean isPositionValid(Position position) {
        return isInsideMaze(position) && !positionIs(position, Cell.WALL) && positionDoesntCreateLoops(position);
    }

    private boolean isInsideMaze(Position position) {
        return isInsideRow(position) && isInsideColumn(position);
    }

    private boolean isInsideRow(Position position) {
        return position.row() >= 0 && position.row() < maze.size();
    }

    private boolean isInsideColumn(Position position) {
        return position.column() >= 0 && position.column() < maze.get(0).size();
    }

    void setPosition(Position position, Cell cell) {
        if (getCellFrom(position) == Cell.WALL || getCellFrom(position) == Cell.PATH) {
            throw new IllegalArgumentException("The position is already occupied");
        }
        maze.get(position.row()).set(position.column(), cell);
    }

    private Cell getCellFrom(Position position) {
        return maze.get(position.row()).get(position.column());
    }

    public boolean positionIs(Position position, Cell cell) {
        return getCellFrom(position) == cell;
    }

    private boolean positionDoesntCreateLoops(Position position) {
        return Arrays.stream(SquarePosition.values()).noneMatch(squarePosition -> {
            var neighBours = squarePosition.getNeighBours(position);
            return neighBours.stream().allMatch(neighBour -> isInsideMaze(neighBour) && positionIs(neighBour, Cell.PATH));
        });
    }

    public void print() {
        maze.stream().
                map(row -> row.stream()
                        .map(Cell::toString)
                        .collect(Collectors.joining()))
                .forEach(System.out::println);
    }
}