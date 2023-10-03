package board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private final List<List<Cell>> board;
    private final int size;

    public Board(int rows, int columns) {
        this.board = new ArrayList<>();
        this.size = rows * columns;
        populateMaze(rows, columns);
    }

    private void populateMaze(int rows, int columns) {
        for (int i = 0; i < rows; i++)
            board.add(Stream.generate(Cell.WALL).limit(columns).collect(Collectors.toList()));
    }

    public boolean isValid(Position position) {
        return isInsideRow(position) && isInsideColumn(position);
    }

    private boolean isInsideRow(Position position) {
        return position.row() >= 0 && position.row() < board.size();
    }

    private boolean isInsideColumn(Position position) {
        return position.column() >= 0 && position.column() < board.get(0).size();
    }

    public void set(Position position, Cell cell) {
        if (!isValid(position))
            throw new IllegalArgumentException("Position is not valid");
        board.get(position.row()).set(position.column(), cell);
    }

    public Cell get(Position position) {
        if (!isValid(position))
            throw new IllegalArgumentException("Position is not valid");
        return board.get(position.row()).get(position.column());
    }

    public List<List<Cell>> getPrimitive() {
        return deepCopy().board;
    }

    public void print() {
        board.stream().
                map(row -> row.stream()
                        .map(Cell::toString)
                        .collect(Collectors.joining()))
                .forEach(System.out::println);
    }

    public Board deepCopy() {
        var newBoard = new Board(board.size(), board.get(0).size());
        for (int i = 0; i < board.size(); i++)
            for (int j = 0; j < board.get(0).size(); j++)
                newBoard.set(new Position(i, j), board.get(i).get(j));
        return newBoard;
    }

    public int getColumns() {
        return board.get(0).size();
    }

    public int getRows() {
        return board.size();
    }

    public List<Cell> getAllCells() {
        return board.stream().flatMap(List::stream).collect(Collectors.toList());
    }
    public int getSize() {
        return size;
    }

}
