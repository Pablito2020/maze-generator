package agent;

import board.Maze;
import board.Position;
import collections.CollectionsHelper;

import java.util.Set;
import java.util.TreeSet;

public class Agents {

    private final int numberAgents;
    private final int maxSteps;

    private final float walkThreshold;

    public Agents(int numberAgents, int maxSteps, float walkThreshold) {
        this.numberAgents = numberAgents;
        this.maxSteps = maxSteps;
        this.walkThreshold = walkThreshold;
    }

    public void walk(Maze maze, Set<Position> startPositions) {
        var poolInitialPositions = new PositionPool(startPositions);
        var positionsVisited = new TreeSet<>(startPositions);
        for (int i = 0; i < numberAgents; i++) {
            var initialPosition = poolInitialPositions.pull().orElse(CollectionsHelper.getRandomFrom(positionsVisited));
            var agent = new Agent(maze, walkThreshold, maxSteps);
            positionsVisited.addAll(agent.walkFrom(initialPosition, positionsVisited));
        }
    }

}
