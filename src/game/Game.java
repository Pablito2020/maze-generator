package game;

import board.Board;
import board.Direction;
import board.Position;


public class Game {

    private final GameBoard board;
    private Position pacmanPosition;

    public Game(Board board, Position pacmanPosition) {
        this.board = new GameBoard(board);
        this.pacmanPosition = pacmanPosition;
    }

    private Game(GameBoard board, Position pacmanPosition) {
        this.board = board;
        this.pacmanPosition = pacmanPosition;
    }

    public static Game from(Board board) {
        var gameBoard = new GameBoard(board);
        var initialPosition = gameBoard.getWalkablePositions().stream().findAny().orElseThrow();
        return new Game(board, initialPosition);
    }

    public boolean canApply(Direction direction) {
        var newPosition = pacmanPosition.applyDirection(direction);
        System.out.println(newPosition);
        return board.isWalkable(newPosition);
    }

    public void move(Direction direction) {
        if (!canApply(direction))
            throw new IllegalArgumentException("Invalid direction");
        this.pacmanPosition = pacmanPosition.applyDirection(direction);
        if (board.hasFruit(pacmanPosition))
            board.eatFruitOn(pacmanPosition);
    }

    public void print() {
        var board = this.board.getBoard().getPrimitive();
        System.out.println(pacmanPosition);
        StringBuilder b = new StringBuilder();
        for (int x = 0; x < board.size(); x++) {
            var column = board.get(x);
            for (int col = 0; col < column.size(); col++) {
                if (pacmanPosition.row() == x && pacmanPosition.column() == col)
                    b.append("P");
                else
                    b.append(column.get(col).toString());
            }
            b.append("\n");
        }
        System.out.println(b);
    }

}