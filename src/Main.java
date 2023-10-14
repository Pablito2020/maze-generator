import agent.Agents;
import board.Cycle;
import board.Direction;
import board.Donut;
import board.Maze;
import game.Game;
import game.food.InMemoryFoodSubscriber;

import java.util.Random;

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
        var maze = getMaze();
        var game = new Game(maze.getBoard(), new InMemoryFoodSubscriber());
        var movements = 0;
        while (!game.hasFinished()) {
            var random = new Random();
            var direction = Direction.values()[random.nextInt(Direction.values().length)];
            game.setDirection(direction);
            game.move();
            movements += 1;
        }
        System.out.println("Finished maze on: " + movements + " movements");
    }

    public static Maze getMaze() {
        var maze = new Maze(ROWS, COLUMNS);
        var initialPositions = Cycle.apply(DEFAULT_DONUT_SIZE, maze);
        var agents = new Agents(MAX_AGENTS, MAX_STEPS_AGENT, WALK_THRESHOLD);
        agents.walk(maze, initialPositions);
        return maze;
    }

}