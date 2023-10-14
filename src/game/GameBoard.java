package game;

import board.Board;
import board.Cell;
import board.Position;

public record GameBoard(Board board) {

    public boolean isWalkable(Position position) {
        return board.isValid(position) && board.get(position).isWalkable();
    }

    public void eatFruitOn(Position position) {
        if (!isWalkable(position) || !hasFruit(position))
            throw new IllegalArgumentException("Invalid position");
        board.set(position, Cell.PATH);
    }

    public boolean hasFruit(Position pacmanPosition) {
        return board.get(pacmanPosition) == Cell.FOOD;
    }

    public boolean hasFood() {
        return board.getAllCells().stream().anyMatch(cell -> cell == Cell.FOOD);
    }

    public Board board() {
        return this.board.deepCopy();
    }

}
