import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Agent {

    private final Maze maze;
    private final Set<Position> positionsVisited;
    private Position position;
    private final int maxSteps;

    public Agent(Maze maze, Set<Position> positionsCycle, int maxSteps) {
        this.maze = maze;
        this.position = getRandomFrom(positionsCycle);
        this.positionsVisited = new TreeSet<>(Collections.singleton(position));
        this.maxSteps = maxSteps;
    }

    private boolean executeStep() {
        var possibleDirections = maze.getDirectionsFrom(position);
        if (possibleDirections.isEmpty()) {
            return false;
        }

        var direction = getRandomFrom(possibleDirections);
        var newPosition = position.applyDirection(direction);
        if (!maze.positionIs(newPosition, Cell.PATH)) {
            System.out.println("Position: " + position + " newPosition: " + newPosition);
            System.out.println("Path!");
//            return false;
            addPositionToPath(newPosition);
        }

        return true;
    }

    public void addPositionToPath(Position newPosition) {
        positionsVisited.add(newPosition);
        maze.setPosition(newPosition, Cell.PATH);
    }

    public Set<Position> run() {
        for (int steps = 0; steps < maxSteps; steps++)
            if (!executeStep()) break;
        return Collections.unmodifiableSet(positionsVisited);
    }


    private <T> T getRandomFrom(Set<T> set) {
        var rand = new Random();
        var row = rand.nextInt(0, set.size());
        return set.stream().toList().get(row);
    }

}
