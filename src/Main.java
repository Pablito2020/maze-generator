public class Main {

    public static int MAX_STEPS_AGENT = 300;
    public static int MAX_AGENTS = 3;

    public static int ROWS = 10;
    public static int COLUMNS = 10;

    public static void main(String[] args) {
        var maze = new Maze(ROWS, COLUMNS);
        var initialPositions = maze.addCycle();
        var agents = new Agents(MAX_AGENTS, MAX_STEPS_AGENT);
        agents.run(maze, initialPositions);
        maze.print();
    }

}