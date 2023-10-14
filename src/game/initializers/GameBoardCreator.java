package game.initializers;

import board.Board;
import board.Cell;
import board.Position;

public class GameBoardCreator {

    public static Board fillWithFood(Board board, Position initialPosition) {
        var resultBoard = board.deepCopy();
        for (int row = 0; row < board.getRows(); row ++) {
            for (int column = 0; column < board.getColumns(); column++) {
                var currentPosition = new Position(row, column);
                if (board.get(currentPosition).isWalkable() && !currentPosition.equals(initialPosition)) {
                    resultBoard.set(currentPosition, Cell.FOOD);
                }
            }
        }
        return resultBoard;
    }

}
