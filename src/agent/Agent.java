package agent;

import board.Cell;
import board.Maze;
import board.Position;
import collections.CollectionsHelper;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Agent {

    private final Maze maze;

    public Agent(Maze maze) {
        this.maze = maze;
    }

    public Set<Position> run(Position position, Set<Position> visited, int maxSteps) {
        var positionsVisited = new TreeSet<>(visited);
        for (int steps = 0; steps < maxSteps; steps++) {
            var newPosition = getNextPositionFrom(position, visited);
            if (newPosition.isEmpty()) {
                break;
            } else {
                positionsVisited.add(newPosition.get());
                maze.setAsPath(newPosition.get());
                position = newPosition.get();
            }
        }
        return Collections.unmodifiableSet(positionsVisited);
    }

    private Optional<Position> getNextPositionFrom(Position position, Set<Position> visited) {
        var possiblePositions = getPossibleNextPositionsFrom(position, visited);
        return possiblePositions.isEmpty() ? Optional.empty() : Optional.of(CollectionsHelper.getRandomFrom(possiblePositions));
    }

    private Set<Position> getPossibleNextPositionsFrom(Position position, Set<Position> positionsVisited) {
        var directions = maze.getDirectionsFrom(position);
        return directions.stream()
                .map(position::applyDirection)
                .filter(pos -> !positionsVisited.contains(pos) && !maze.positionIsPath(pos))
                .collect(Collectors.toSet());
    }

}
