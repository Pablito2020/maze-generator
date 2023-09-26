package board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    public final static int MINIMUM_ROW_SIZE = 3;
    public final static int MINIMUM_COLUMN_SIZE = 3;
    private final List<List<Cell>> maze;

    public Board(int rows, int columns) {
        assertSize(rows, columns);
        this.maze = new ArrayList<>();
        populateMaze(rows, columns);
    }

    private void assertSize(int rows, int columns) {
        if (rows < MINIMUM_ROW_SIZE || columns < MINIMUM_COLUMN_SIZE)
            throw new IllegalArgumentException("The minimum size should be " + MINIMUM_ROW_SIZE + "x" + MINIMUM_COLUMN_SIZE);
    }

    private void populateMaze(int rows, int columns) {
        for (int i = 0; i < rows; i++)
            maze.add(Stream.generate(Cell.WALL).limit(columns).collect(Collectors.toList()));
    }

    public boolean isValid(Position position) {
        return isInsideRow(position) && isInsideColumn(position);
    }

    private boolean isInsideRow(Position position) {
        return position.row() >= 0 && position.row() < maze.size();
    }

    private boolean isInsideColumn(Position position) {
        return position.column() >= 0 && position.column() < maze.get(0).size();
    }

    public void set(Position position, Cell cell) {
        if (!isValid(position))
            throw new IllegalArgumentException("Position is not valid");
        maze.get(position.row()).set(position.column(), cell);
    }

    public Cell get(Position position) {
        if (!isValid(position))
            throw new IllegalArgumentException("Position is not valid");
        return maze.get(position.row()).get(position.column());
    }

    public void print() {
        maze.stream().
                map(row -> row.stream()
                        .map(Cell::toString)
                        .collect(Collectors.joining()))
                .forEach(System.out::println);
    }

    public int getColumns() {
        return maze.get(0).size();
    }

    public int getRows() {
        return maze.size();
    }

}
