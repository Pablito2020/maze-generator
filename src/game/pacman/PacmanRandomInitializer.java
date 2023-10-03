package game.pacman;

import board.Direction;
import game.Game;

public class PacmanRandomInitializer implements PacmanDirectionInitializer {
    @Override
    public Direction getDirectionFromNeighbours(Game game) {
        // TODO: implement this method
        return Direction.UP;
    }
}
