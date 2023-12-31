package board;

import java.util.Set;

public class Donut {

    private static final int MINIMUM_SIDE_SIZE = 3;
    static final int MINIMUM_AREA = MINIMUM_SIDE_SIZE * MINIMUM_SIDE_SIZE;
    private final Rectangle rectangle;

    public Donut(int width, int height) {
        this(new Rectangle(width, height));
    }

    private Donut(Rectangle rectangle) {
        if (rectangle.area() < MINIMUM_AREA)
            throw new IllegalArgumentException("You are not creating a donut, the donut should have a hole");
        this.rectangle = rectangle;
    }

    public Set<Position> getPositions(Position initialPosition) {
        return rectangle.getPositionsFromOrigin().stream()
                .filter(pos -> !isInsideDonutHole(pos, rectangle))
                .map(initialPosition::plus)
                .collect(java.util.stream.Collectors.toSet());
    }

    public int width() {
        return rectangle.width();
    }

    public int height() {
        return rectangle.height();
    }

    private boolean isInsideDonutHole(Position currentPosition, Rectangle rectangle) {
        return !firstOrLastRowOf(rectangle, currentPosition) && !firstOrLastColumnOf(rectangle, currentPosition);
    }

    private boolean firstOrLastColumnOf(Rectangle rectangle, Position currentPosition) {
        return currentPosition.column() == 0 || currentPosition.column() == rectangle.width() - 1;
    }

    private boolean firstOrLastRowOf(Rectangle rectangle, Position currentPosition) {
        return currentPosition.row() == 0 || currentPosition.row() == rectangle.height() - 1;
    }

}
