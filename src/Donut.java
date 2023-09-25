import java.util.HashSet;
import java.util.Set;

public class Donut {

    private final Position initialPosition;
    private final int size;

    public Donut(Position initialPosition, int size) {
        this.initialPosition = initialPosition;
        this.size = size;
    }

    public Set<Position> getPositions() {
        var set = new HashSet<>(Set.of(initialPosition));
        // Add everything
        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                set.add(new Position(initialPosition.row() + i, initialPosition.column() + j));
            }
        }
        // Remove middle positions
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                set.remove(new Position(initialPosition.row() + i, initialPosition.column() + j));
            }
        }
        return set;
    }
}
