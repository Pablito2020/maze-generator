package game.food;

import board.Position;

import java.util.HashSet;
import java.util.Set;

public class InMemoryFoodSubscriber implements FoodSubscriber {

    private int foodEaten = 0;
    private Set<Position> eatenPositions = new HashSet<Position>();
    @Override
    public void eatFoodOn(Position position) {
        this.foodEaten += 1;
        this.eatenPositions.add(position);
    }

}
