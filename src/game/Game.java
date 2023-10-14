package game;

import board.Board;
import board.Direction;
import board.Position;
import game.food.FoodSubscriber;
import game.initializers.GameBoardCreator;
import game.initializers.PacmanInitializer;
import game.initializers.PacmanRandomInitializer;

public class Game {

    private final GameState state;
    private Direction currentDirection;

    public Game(Board board, FoodSubscriber foodEventEmitter) {
        this(board, new PacmanRandomInitializer(), foodEventEmitter);
    }

    public Game(Board board, PacmanInitializer initializer, FoodSubscriber foodEventEmitter) {
        var initialPosition = initializer.getInitialPosition(board);
        var boardWithFood = GameBoardCreator.fillWithFood(board, initialPosition);
        this.state = new GameState(boardWithFood, initialPosition, foodEventEmitter);
        this.currentDirection = initializer.getInitialDirection(this.state);
    }

    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }

    public void move() {
        if (state.canApply(currentDirection))
            state.move(currentDirection);
    }

    public boolean hasFinished() {
        return state.hasFinished();
    }

    public Position getPacmanPosition() {
        return state.getPacmanPosition();
    }

}
