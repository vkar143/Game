package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.GreatSlamAction;
import game.actions.SellAction;
import game.general.Ability;
import game.general.Status;

/**
 * Action for the player to use the "Great Slam" skill with the Giant Hammer.
 *
 * Created by:
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see WeaponItem
 * @see SellableItem
 */

public class GiantHammer extends WeaponItem implements SellableItem {
    /**
     * Constant representing the selling amount of the Giant Hammer.
     */
    private final int SELLING_AMOUNT = 250;
    /**
     * Constant representing the damage inflicted by the Giant Hammer.
     */
    private static final int DAMAGE = 160;
    /**
     * Constant representing the hit rate of the Giant Hammer.
     */
    private static final int HIT_RATE = 90;
    /**
     * Constructor for the Giant Hammer.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', DAMAGE, "slams", HIT_RATE);
        this.capabilitySet.addCapability(Ability.ATTACK);
    }
    /**
     * Gives the actions that can be performed using the Giant Hammer.
     *
     * @param target The target actor to attack.
     * @param location The location where the attack action occurs.
     * @return An ActionList containing allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actions = super.allowableActions(location);
        if(target.hasCapability(Status.ENEMY)){
            actions.add(new AttackAction(target, location.toString(), this));
            actions.add(new GreatSlamAction(target, location.toString(), this));
        }
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actions.add(new SellAction("sells the Giant Hammer", this, SELLING_AMOUNT));
        }
        return actions;
    }

    /**
     * Allows an actor to sell the Giant Hammer to another actor that can trade.
     *
     * @param actor Actor with the Giant Hammer.
     * @param sellingAmount Cost of the Giant Hammer.
     * @return Returns a string describing the transaction.
     */
    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        actor.addBalance(sellingAmount);
        actor.removeItemFromInventory(this);
        return "sells the Giant Hammer for " + sellingAmount + " runes";
    }
}
