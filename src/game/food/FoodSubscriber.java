package game.food;

import board.Position;

public interface FoodSubscriber {
    void eatFoodOn(Position position);
}
