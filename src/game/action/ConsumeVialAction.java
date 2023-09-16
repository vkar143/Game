package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.HealingVial;
/**
 * A class that represents the action of consuming a Healing Vial
 * Created by:
 * @author Vasi Karabourniotis
 */
public class ConsumeVialAction extends Action {
    /**
     * Constructor
     */
    public ConsumeVialAction() {}

    /**
     * It will increase the actors health if they have Refreshing Flask item in their inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string explaining the action that was executed based on whether the actor has the item or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getItemInventory() != null) {
            for (Item item : actor.getItemInventory()) {
                if (item.getClass() == HealingVial.class) {
                    actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 20);
                    return actor + " consumes Healing Vial and Healing Vial restores the health of " + actor + " by 20 points";
                }
            }
        }
        return actor + " does not have a Healing Vial";
    }

    /**
     * Displays a description of ConsumeVialAction to the user through the actor's menu
     * @param actor The actor performing the action.
     * @return A String summarising the action of ConsumeVialAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Healing Vial";
    }
}
