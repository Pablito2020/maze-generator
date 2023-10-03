package game;

import board.Board;
import board.Direction;
import game.pacman.PacmanDirectionInitializer;

public class GameState {

    private final Game game;
    private Direction currentDirection;

    public GameState(Board board, PacmanDirectionInitializer initializer) {
         this.game = Game.from(board);
         this.currentDirection = initializer.getDirectionFromNeighbours(game);
    }

    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }

    public void move() {
        if (game.canApply(currentDirection))
            game.move(currentDirection);
    }

    public void print() {
        game.print();
    }

}
