package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actions.StabAndStepAction;
import game.general.Ability;
import game.general.Status;

import java.util.Random;

/**
 * Action for the player to use the "Great Slam" skill with the Giant Hammer.
 *
 * Created by:
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see WeaponItem
 * @see SellableItem
 * @see BuyableItem
 */

public class GreatKnife extends WeaponItem implements SellableItem, BuyableItem{
    /**
     * Constant representing the selling amount of the Great Knife.
     */
    private final int SELLING_AMOUNT = 175;
    /**
     * Constant representing the damage inflicted by the Great Knife.
     */
    private static final int DAMAGE = 75;
    /**
     * Constant representing the hit rate of the Great Knife.
     */
    private static final int HIT_RATE = 70;
    /**
     * Constant representing the bound of the buyItem method.
     */
    private static final int BUYING_BOUND = 20;
    /**
     * Constant representing the bound of the sellItem method.
     */
    private static final int SELLING_BOUND = 10;
    /**
     * Constant representing the chance that the price of a Great Knife is tripled.
     */
    private static final int BUYING_CHANCE = 1;
    /**
     * Constant representing the chance that an actor's runes are stolen.
     */
    private static final int SELLING_CHANCE = 1;

    /**
     * Constructor for the Great Knife.
     */
    public GreatKnife() {
        super("Great Knife", '>', DAMAGE, "stabs", HIT_RATE);
        this.capabilitySet.addCapability(Ability.ATTACK);
    }

    /**
     * Gives the actions that can be performed using the Great Knife.
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
            actions.add(new StabAndStepAction(target, location.toString(), this));
        }
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actions.add(new SellAction("sells the Giant Hammer", this, SELLING_AMOUNT));
        }
        return actions;
    }

    /**
     * Allows an actor to buy the Great Knife from another actor.
     *
     * @param actor Actor buying the Great Knife.
     * @param buyingAmount Cost of the Great Knife.
     * @return Returns a string describing the transaction.
     */
    @Override
    public String buyItem(Actor actor, int buyingAmount) {
        Random random = new Random();
        int chance = random.nextInt(BUYING_BOUND);
        if (actor.getBalance() < buyingAmount) {
            return "cannot afford " + this;
        }
        if (chance < BUYING_CHANCE) {
            buyingAmount *= 3;
            if (actor.getBalance() < buyingAmount) {
                return "cannot afford " + this + ", price was tripled!";
            } else {
                actor.addItemToInventory(this);
                actor.deductBalance(buyingAmount);
                return "buys the Great Knife for " + buyingAmount + " runes";
            }
        } else {
            actor.addItemToInventory(this);
            actor.deductBalance(buyingAmount);
            return "buys the Great Knife for " + buyingAmount + " runes";
        }
    }

    /**
     * Allows an actor to sell the Great Knife to another actor that can trade.
     *
     * @param actor Actor with the Great Knife.
     * @param sellingAmount Cost of the Great Knife.
     * @return Returns a string describing the transaction.
     */
    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        Random random = new Random();
        int chance = random.nextInt(SELLING_BOUND);

        if (chance < SELLING_CHANCE) {
            int stolenAmount = Math.min(sellingAmount, actor.getBalance());
            actor.deductBalance(stolenAmount);
            return stolenAmount + " runes were stolen!";
        } else {
            actor.addBalance(sellingAmount);
            actor.removeItemFromInventory(this);
            return "sells the Great Knife for " + sellingAmount + " runes";
        }
    }
}
