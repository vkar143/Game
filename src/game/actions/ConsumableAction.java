package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.general.Status;
import game.items.Consumable;

/**
 * an action that can be used by any class that uses an item
 */
public class ConsumableAction extends Action {
    private String description;
    private Consumable consumable;


    /**
     * sets all the params for this class
     * @param consumable
     * @param description
     *
     */
    public ConsumableAction(String description, Consumable consumable) {
        this.consumable = consumable;
        this.description = description;

    }

    /**
     * takes an actor and increases their stats in the way specified by the init returns a string description of the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.consume(actor);
        return actor + description;
    }

    /**
     * returns a string description of the action
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + description;
    }
}
