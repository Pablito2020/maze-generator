public class Main {
    public static void main(String[] args) {
        var maze = new Maze(10, 10);
        var positionsWalked = maze.addCycle();
        int maxSteps = 100;
        int maxAgents = 40;
        for (int i = 0; i < maxAgents; i++) {
            var agent = new Agent(maze, positionsWalked, maxSteps);
            positionsWalked.addAll(agent.run());
        }
        maze.print();
    }
}