package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.general.Status;

/**
 * an action that can be used by any class that uses an item
 */
public class ConsumableAction extends Action {

    private BaseActorAttributes attribute;
    private int percentage;
    private String description;
    private Item item;

    /**
     * sets all of the params for this class
     * @param attribute
     * @param percentage
     * @param description
     * @param item
     */
    public ConsumableAction(BaseActorAttributes attribute, int percentage, String description, Item item) {
        this.attribute = attribute;
        this.percentage = percentage;
        this.description = description;
        this.item = item;
    }

    /**
     * takes an actor and increases their stats in the way specified by the init returns a string description of the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.modifyAttribute(attribute, ActorAttributeOperations.INCREASE,actor.getAttributeMaximum(attribute)*percentage/100);
        if(!(item.hasCapability(Status.REUSABLE))){
            actor.removeItemFromInventory(item);
            }

        return actor + " " + description + " Replenished";
    }

    /**
     * returns a string description of the action
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Replenish " + actor + " " + description;
    }
}
