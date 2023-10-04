package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.OpenGateAction;
import game.general.Ability;

/**
 * transports the actor between levels if they have the key
 */
public class Gate extends Ground {
    private Action action;
    private boolean unlocked;

    /***
     * Constructor.
     */
    public Gate() {
        super('=');
        this.unlocked = false;
    }

    /**
     * allows you to add a movement action
     *
     * @param action action added.
     */
    public void addAllowableAction(Action action) {
        this.action = action;
    }

    /**
     * checks to see if the actor has a key with the unlock ability if so it returns a Move action
     * @return returns a list of allowable actions
     */

    public void Unlock() {
        this.unlocked = true;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = super.allowableActions(actor, location, direction);
        if (unlocked) {
            actionList.add(action);
            return actionList;
        }
        for (Item item : actor.getItemInventory()) {
            if (item.hasCapability(Ability.UNLOCK)) {
                actionList.add(new OpenGateAction(this));
                return actionList;
            }
        }
        return actionList;
    }
}

