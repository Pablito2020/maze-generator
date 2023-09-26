package board;

import java.util.Random;
import java.util.Set;

public class Cycle {

    public static Set<Position> apply(int size, Maze maze) {
        return apply(size, maze.board);
    }

    private static Set<Position> apply(int size, Board board) {
        if (!isViable(size, board))
            throw new IllegalArgumentException("Size of cycle is not viable");
        var cyclePosition = getCycleInitialPosition(size, board);
        var donut = new Donut(cyclePosition, size);
        var positions = donut.getPositions();
        positions.forEach(pos -> board.set(pos, Cell.PATH));
        return positions;
    }

    private static Position getCycleInitialPosition(int size, Board board) {
        var maxRowsRandom = board.getRows() - size;
        var maxColumnsRandom = board.getColumns() - size;
        var rand = new Random();
        var row = maxRowsRandom > 0 ? rand.nextInt(0, maxRowsRandom) : 0;
        var column = maxColumnsRandom > 0 ? rand.nextInt(0, maxColumnsRandom) : 0;
        return new Position(row, column);
    }

    public static boolean isViable(int sizeCycle, Maze maze) {
        return isViable(sizeCycle, maze.board);
    }

    public static boolean isViable(int sizeCycle, Board board) {
        int maxOfCycle = Integer.min(board.getRows(), board.getColumns());
        return sizeCycle <= maxOfCycle && sizeCycle >= Board.MINIMUM_ROW_SIZE && sizeCycle >= Board.MINIMUM_COLUMN_SIZE;
    }

}
