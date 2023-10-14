package game;

import board.Board;
import board.Direction;
import board.Position;
import game.food.FoodSubscriber;


public class GameState {

    private final GameBoard board;
    private final FoodSubscriber foodEventEmitter;
    private Position pacmanPosition;

    public GameState(Board board, Position pacmanPosition, FoodSubscriber foodEventEmitter) {
        this.board = new GameBoard(board);
        this.pacmanPosition = pacmanPosition;
        this.foodEventEmitter = foodEventEmitter;
    }

    public boolean canApply(Direction direction) {
        var newPosition = pacmanPosition.applyDirection(direction);
        return board.isWalkable(newPosition);
    }

    public void move(Direction direction) {
        if (!canApply(direction))
            throw new IllegalArgumentException("Invalid direction");
        this.pacmanPosition = pacmanPosition.applyDirection(direction);
        if (board.hasFruit(pacmanPosition)) {
            board.eatFruitOn(pacmanPosition);
            foodEventEmitter.eatFoodOn(pacmanPosition);
        }
    }

    public boolean hasFinished() {
        return !board.hasFood();
    }

    public Position getPacmanPosition() {
        return this.pacmanPosition;
    }

}