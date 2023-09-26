import agent.Agents;
import board.Cycle;
import board.Maze;

public class Main {

    private static final int MAX_AGENTS = 3;

    private static final int MAX_STEPS_AGENT = 300;
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final int INITIAL_CYCLE_SIZE = 4;

    public static void main(String[] args) {
        var maze = new Maze(ROWS, COLUMNS);
        var initialPositions = Cycle.apply(INITIAL_CYCLE_SIZE, maze);
        var agents = new Agents(MAX_AGENTS, MAX_STEPS_AGENT);
        agents.walk(maze, initialPositions);
        maze.print();
    }

}