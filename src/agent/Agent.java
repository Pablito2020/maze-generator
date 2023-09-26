package agent;

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

    public Set<Position> walkFrom(Position position, Set<Position> walkedPositions, int maxSteps) {
        var positionsVisited = new TreeSet<>(walkedPositions);
        walkFrom(position, 0, maxSteps, positionsVisited);
        return Collections.unmodifiableSet(positionsVisited);
    }

    // NOTE: Be aware that this method is recursive and it can cause a StackOverflowError, check it on C# limits
    private void walkFrom(Position position, int numberOfSteps, int maxSteps, Set<Position> walkedPositions) {
        if (numberOfSteps == maxSteps) return;
        var nextStep = stepFromPosition(position, walkedPositions);
        if (nextStep.isEmpty()) return;
        markAsWalked(nextStep.get(), walkedPositions);
        walkFrom(nextStep.get(), numberOfSteps + 1, maxSteps, walkedPositions);
    }

    private void markAsWalked(Position step, Set<Position> walkedPositions) {
        walkedPositions.add(step);
        maze.setAsWalked(step);
    }

    private Optional<Position> stepFromPosition(Position position, Set<Position> visited) {
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
