package game.initializers;

import board.Board;
import board.Direction;
import board.Position;
import game.GameState;

public interface PacmanInitializer {
    Position getInitialPosition(Board board);
    Direction getInitialDirection(GameState state);

}
