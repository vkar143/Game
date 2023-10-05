package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A class that represents an interface for selling items
 */
public interface Consumable {

    /**
     * A method that can be personalised for each item that can be consumed
     * @param actor The actor which can consume the item
     */
    void consume (Actor actor);
}
