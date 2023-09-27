package board;

import java.util.HashSet;
import java.util.Set;

public record Rectangle(int width, int height) {
    public int area() {
        return width * height;
    }

    public Set<Position> getPositionsFromOrigin() {
        var set = new HashSet<Position>();
        for (int row = 0; row < height; row++)
            for (int column = 0; column < width; column++)
                set.add(new Position(row, column));
        return set;
    }

}
