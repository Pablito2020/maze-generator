import agent.Agents;
import board.Cycle;
import board.Donut;
import board.Maze;

public class Main {

    private static final int MAX_AGENTS = 4;
    private static final int MAX_STEPS_AGENT = 300;
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final int INITIAL_DONUT_WIDTH = 10;
    private static final int INITIAL_DONUT_HEIGHT = 10;
    private static final float WALK_THRESHOLD = 0.5f;
    private static final Donut DEFAULT_DONUT_SIZE = new Donut(INITIAL_DONUT_WIDTH, INITIAL_DONUT_HEIGHT);

    public static void main(String[] args) {
        var maze = new Maze(ROWS, COLUMNS);
        var initialPositions = Cycle.apply(DEFAULT_DONUT_SIZE, maze);
        var agents = new Agents(MAX_AGENTS, MAX_STEPS_AGENT, WALK_THRESHOLD);
        agents.walk(maze, initialPositions);
        maze.print();
    }

}