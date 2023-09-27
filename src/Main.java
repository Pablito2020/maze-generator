import agent.Agents;
import board.Cycle;
import board.Maze;
import board.Rectangle;

public class Main {

    private static final int MAX_AGENTS = 4;
    private static final int MAX_STEPS_AGENT = 300;
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final int INITIAL_CYCLE_WIDTH = 10;
    private static final int INITIAL_CYCLE_HEIGHT = 9;
    private static final Rectangle INITIAL_RECTANGLE = new Rectangle(INITIAL_CYCLE_WIDTH, INITIAL_CYCLE_HEIGHT);

    public static void main(String[] args) {
        var maze = new Maze(ROWS, COLUMNS);
        var initialPositions = Cycle.apply(INITIAL_RECTANGLE, maze);
        var agents = new Agents(MAX_AGENTS, MAX_STEPS_AGENT);
        agents.walk(maze, initialPositions);
        maze.print();
    }

}