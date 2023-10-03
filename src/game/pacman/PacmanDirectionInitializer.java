package game.pacman;

import board.Direction;
import board.Position;
import game.Game;

public interface PacmanDirectionInitializer {
    Direction getDirectionFromNeighbours(Game game);

}
