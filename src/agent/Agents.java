package agent;

import board.Maze;
import board.Position;
import collections.CollectionsHelper;

import java.util.Set;
import java.util.TreeSet;

public class Agents {

    private final int numberAgents;
    private final int maxSteps;

    public Agents(int numberAgents, int maxSteps) {
        this.numberAgents = numberAgents;
        this.maxSteps = maxSteps;
    }

    public void run(Maze maze, Set<Position> startPositions) {
        var poolInitialPositions = new PositionPool(startPositions);
        var positionsVisited = new TreeSet<>(startPositions);
        for (int i = 0; i < numberAgents; i++) {
            var initialPosition = poolInitialPositions.pull().orElse(CollectionsHelper.getRandomFrom(positionsVisited));
            var agent = new Agent(maze);
            positionsVisited.addAll(agent.run(initialPosition, positionsVisited, maxSteps));
        }
    }

}
