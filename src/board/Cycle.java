package board;

import java.util.Random;
import java.util.Set;

public final class Cycle {

    public static Set<Position> apply(Donut donut, Maze maze) {
        return apply(donut, maze.board);
    }

    private static Set<Position> apply(Donut donut, Board board) {
        if (!isViable(donut, board))
            throw new IllegalArgumentException("Size of cycle is not viable");
        var cyclePosition = getCycleInitialPosition(donut, board);
        var positions = donut.getPositions(cyclePosition);
        positions.forEach(pos -> board.set(pos, Cell.PATH));
        return positions;
    }

    private static Position getCycleInitialPosition(Donut donut, Board board) {
        var rowsLeft = rowsLeftOverAfterCycle(donut, board);
        var columnsLeft = columnsLeftOverAfterCycle(donut, board);
        var rand = new Random();
        var row = rowsLeft > 0 ? rand.nextInt(0, rowsLeft) : 0;
        var column = columnsLeft > 0 ? rand.nextInt(0, columnsLeft) : 0;
        return new Position(row, column);
    }

    public static boolean isViable(Donut donut, Maze maze) {
        return isViable(donut, maze.board);
    }

    public static boolean isViable(Donut donut, Board board) {
        return rowsLeftOverAfterCycle(donut, board) >= 0 && columnsLeftOverAfterCycle(donut, board) >= 0;
    }

    private static int rowsLeftOverAfterCycle(Donut donut, Board board) {
        return board.getRows() - donut.height();
    }
    private static int columnsLeftOverAfterCycle(Donut donut, Board board) {
        return board.getColumns() - donut.width();
    }
}
