package game.actors;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public interface Droppable {
    void drop(Location location, Item item, float chance);
}
