package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.RefreshingFlask;

/**
 * A class that represents the action of consuming a Refreshing Flask
 * Created by:
 * @author Vasi Karabourniotis
 */
public class ConsumeFlaskAction extends Action {
    /**
     * Constructor
     */
    public ConsumeFlaskAction() {}

    /**
     * It will increase the actors stamina if they have Refreshing Flask item in their inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string explaining the action that was executed based on whether the actor has the item or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getItemInventory() != null) {
            for (Item item : actor.getItemInventory()) {
                if (item.getClass() == RefreshingFlask.class) {
                    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 20);
                    return actor + " consumes Healing Vial and Healing Vial restores the stamina of " + actor + " by 40 points";
                }
            }
        }
        return actor + " does not have a Refreshing Flask";
    }

    /**
     * Displays a description of ConsumeFlaskAction to the user through the actor's menu
     * @param actor The actor performing the action.
     * @return A String summarising the action of ConsumeFlaskAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Refreshing Flask";
    }
}
