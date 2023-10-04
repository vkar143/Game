package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Broadsword;

/**
 * focus action is used by the BroadSword Item
 */

public class FocusAction extends Action {
    /**
     * the item that is using this action
     */
    private final Broadsword weaponItem;
    private final float MULTIPLIER_INCREASE_PERCENTAGE = 0.10f;
    private final int FOCUS_HIT_RATE = 90;
    private final int STAMINA_DECREASE = 40;
    private final int FOCUS_DURATION = 5;

    /**
     * sets the weapon
     * @param weapon the weapon using the focus action
     */
    public FocusAction(Broadsword weapon){
        this.weaponItem = weapon;
    }

    /**
     * increases the stats of the weapon then decreases the actors stamina the sets its focus time to 5
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns a string describing the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        weaponItem.increaseDamageMultiplier(MULTIPLIER_INCREASE_PERCENTAGE);
        weaponItem.updateHitRate(FOCUS_HIT_RATE);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, STAMINA_DECREASE);
        weaponItem.setFocusDuration(FOCUS_DURATION);
        return "FOCUSING!";
    }

    /**
     * returns a string of the description of the action
     * @param actor The actor performing the action.
     * @return returns a string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses focus with " + weaponItem ;
    }
}
