package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.items.OldKey;

/**
 * A class that represents the action of unlocking a gate by an actor
 * Created by:
 * @author Vasi Karabourniotis
 */
public class UnlockAction extends Action {
    /**
     * Constructor used for making an instance of UnlockAction
     */
    public UnlockAction() {}

    /**
     * It will add the ability "CAN_UNLOCK_DOOR" to the actor if it has a key in its inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string explaining the action that was executed based on whether the actor has the item or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getItemInventory() != null){
            for (Item item : actor.getItemInventory()) {
                if (item.getClass() == OldKey.class){
                    actor.addCapability(Ability.CAN_UNLOCK_DOOR);
                    return "Gate is now unlocked.";
                }
            }
        }
        return "Gate is locked shut.";
    }
    /**
     * This will display a description of UnlockAction to the user through the actor's menu
     * @param actor The actor performing the action.
     * @return A String summarising the action of UnlockAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Gate";
    }
}
