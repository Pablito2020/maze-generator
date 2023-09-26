package agent;

import board.Position;
import collections.CollectionsHelper;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class PositionPool {

    private final Set<Position> pool;
    public PositionPool(Set<Position> pool) {
        this.pool = new TreeSet<>(pool);
    }

    public Optional<Position> pull() {
        if (pool.isEmpty())
            return Optional.empty();
        var position = CollectionsHelper.getRandomFrom(pool);
        pool.remove(position);
        return Optional.of(position);
    }
}
