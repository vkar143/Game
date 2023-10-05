package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.general.Ability;
import game.actions.FocusAction;
import game.general.Status;

import java.util.Random;

/**
 * A class that represents the weapon item Broadsword
 */
public class Broadsword extends WeaponItem implements SellableItem, BuyableItem {
    private static final int DAMAGE = 110;
    private static final int HIT_RATE = 80;
    private final int FOCUS_DURATION = 0;
    private final int NEW_DAMAGE_MULTIPLIER = 1;
    private final int SELLING_AMOUNT = 100;
    private final int BOUND_BUY_ITEM = 20;
    private final int CHANCE_BUY_ITEM = 1;
    private int focusDuration;

    /**
     * A constructor that creates an instance for Broadsword
     */
    public Broadsword() {
        super("BroadSword", '1', DAMAGE, "Swings at", HIT_RATE);
        this.focusDuration = FOCUS_DURATION;
        this.capabilitySet.addCapability(Ability.ATTACK);
//        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
    }

    /**
     * Returns focus duration
     */
    public int getFocusDuration() {
        return focusDuration;
    }

    /**
     * Decreases focus duration each turn and when focus duration runs out it sets the weapon stats back to normal
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        this.setFocusDuration(getFocusDuration()-1);
        if(getFocusDuration() == FOCUS_DURATION){
            updateDamageMultiplier(NEW_DAMAGE_MULTIPLIER);
            updateHitRate(HIT_RATE);
        }
    }

    /**
     * Sets focus duration
     */
    public void setFocusDuration(int focusDuration) {
        this.focusDuration = focusDuration;
    }

    /**
     * Gives the actions that can be performed using the broadsword.
     * @param target The target actor to attack.
     * @param location The location where the attack action occurs.
     * @return An ActionList containing allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actions = super.allowableActions(location);
        if(target.hasCapability(Status.ENEMY)){
            actions.add(new AttackAction(target, location.toString(), this));
        }
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actions.add(new SellAction("sells the Broadsword", this, SELLING_AMOUNT));
        }
        return actions;
    }

    /**
     * A list of actions that can be executed by the Broadsword
     * @param owner the actor that owns the item
     * @return A collection of actions specific to the Broadsword
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = super.allowableActions(owner);
        actionList.add(new FocusAction(this));
        return actionList;
    }

    /**
     * The specific actions that occur when Broadsword is sold
     * @param actor The actor which can sell the item
     * @param sellingAmount The amount the item will be sold for
     * @return A string that describes the result of executing the sell BloodBerry method
     */
    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        actor.addBalance(sellingAmount);
        actor.removeItemFromInventory(this);
        return "sells the Broadsword for " + sellingAmount + " runes";
    }

    /**
     * The specific actions that occur when Broadsword is bought
     * @param actor The actor which can buy the item
     * @param buyingAmount The amount the item will be bought for
     * @return A string that describes the result of executing the buy Broadsword method
     */
    @Override
    public String buyItem(Actor actor, int buyingAmount) {
        Random random = new Random();
        int chance = random.nextInt(BOUND_BUY_ITEM);
        if (actor.getBalance() < buyingAmount) {
            return "cannot afford " + this;
        }
        if (chance > CHANCE_BUY_ITEM) {
            actor.addItemToInventory(this);
            actor.deductBalance(buyingAmount);
            return "buys the Broadsword for " + buyingAmount + " runes";
        } else{
            actor.deductBalance(buyingAmount);
            return "Your runes were stolen!";
        }

    }
}

