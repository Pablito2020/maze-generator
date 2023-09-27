package board;

import java.util.Random;
import java.util.Set;

public class Cycle {

    public static Set<Position> apply(Rectangle rectangle, Maze maze) {
        return apply(rectangle, maze.board);
    }

    private static Set<Position> apply(Rectangle rectangle, Board board) {
        if (!isViable(rectangle, board))
            throw new IllegalArgumentException("Size of cycle is not viable");
        var cyclePosition = getCycleInitialPosition(rectangle, board);
        var donut = new Donut(rectangle);
        var positions = donut.getPositions(cyclePosition);
        positions.forEach(pos -> board.set(pos, Cell.PATH));
        return positions;
    }

    private static Position getCycleInitialPosition(Rectangle rectangle, Board board) {
        var rowsLeft = rowsLeftOverAfterCycle(rectangle, board);
        var columnsLeft = columnsLeftOverAfterCycle(rectangle, board);
        var rand = new Random();
        var row = rowsLeft > 0 ? rand.nextInt(0, rowsLeft) : 0;
        var column = columnsLeft > 0 ? rand.nextInt(0, columnsLeft) : 0;
        return new Position(row, column);
    }

    public static boolean isViable(Rectangle rectangle, Maze maze) {
        return isViable(rectangle, maze.board);
    }

    public static boolean isViable(Rectangle rectangle, Board board) {
        return rowsLeftOverAfterCycle(rectangle, board) >= 0 && columnsLeftOverAfterCycle(rectangle, board) >= 0;
    }

    private static int rowsLeftOverAfterCycle(Rectangle rectangle, Board board) {
        return board.getRows() - rectangle.height();
    }
    private static int columnsLeftOverAfterCycle(Rectangle rectangle, Board board) {
        return board.getColumns() - rectangle.width();
    }
}
