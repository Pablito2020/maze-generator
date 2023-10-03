package game;

import board.Board;
import board.Cell;
import board.Position;

import java.util.Set;
import java.util.stream.IntStream;

public class GameBoard {

    private final Board board;

    public GameBoard(Board board) {
        this.board = board;
    }

    public boolean isWalkable(Position position) {
        return board.isValid(position) && board.get(position).isWalkable();
    }


    public void eatFruitOn(Position position) {
        if (!isWalkable(position))
            throw new IllegalArgumentException("Invalid position");
        // TODO: Here check if there is a fruit here
        board.set(position, Cell.PATH);
    }

    public boolean hasFruit(Position pacmanPosition) {
        // TODO: Here check if there is a fruit here
        return false;
    }

    public Set<Position> getWalkablePositions() {
        return IntStream.generate(() -> 0)
                .limit(board.getRows())
                .boxed()
                .flatMap(row -> IntStream.generate(() -> 0)
                        .limit(board.getColumns())
                        .mapToObj(column -> new Position(row, column)))
                .filter(pos -> board.get(pos).isWalkable())
                .collect(java.util.stream.Collectors.toSet());
    }

    public Board getBoard() {
        return this.board.deepCopy();
    }

}
