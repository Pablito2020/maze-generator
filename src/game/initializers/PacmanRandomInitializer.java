package game.initializers;

import board.Board;
import board.Direction;
import board.Position;
import game.GameState;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PacmanRandomInitializer implements PacmanInitializer {

    @Override
    public Position getInitialPosition(Board board) {
        return getRandomValidPosition(board);
    }


    private Position getRandomValidPosition(Board board) {
        return getRandomValidPosition(board, new HashSet<>());
    }

    private Position getRandomValidPosition(Board board, Set<Position> visited) {
        var random = new Random();
        var position = new Position(random.nextInt(board.getRows()), random.nextInt(board.getColumns()));
        if (board.get(position).isWalkable())
            return position;
        visited.add(position);
        return getRandomValidPosition(board, visited);
    }

    @Override
    public Direction getInitialDirection(GameState state) {
        return getRandomValidDirection(state);
    }

    private Direction getRandomValidDirection(GameState state) {
        return getRandomValidDirection(state, new HashSet<>());
    }

    private Direction getRandomValidDirection(GameState state, Set<Direction> visited) {
        var random = new Random();
        var direction = Direction.values()[random.nextInt(Direction.values().length)];
        if (state.canApply(direction))
            return direction;
        visited.add(direction);
        return getRandomValidDirection(state, visited);
    }

}
