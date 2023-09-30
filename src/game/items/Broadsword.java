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
 * weapon item
 */
public class Broadsword extends WeaponItem implements SellableItem, BuyableItem {
    /**
     * Constructor.
     */
    private int focusDuration;
    public Broadsword() {
        super("BroadSword", '1', 110, "Swings at", 80);
        this.focusDuration = 0;
        this.capabilitySet.addCapability(Ability.ATTACK);
        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
    }

    /**
     * returns focus duration
     */
    public int getFocusDuration() {
        return focusDuration;
    }

    /**
     * decreases focus duration each turn and when focus duration runs out it sets the weapon stats back to normal
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        this.setFocusDuration(getFocusDuration()-1);
        if(getFocusDuration() == 0){
            updateDamageMultiplier(1);
            updateHitRate(80);
        }
    }

    /**
     * sets focus duration
     */
    public void setFocusDuration(int focusDuration) {
        this.focusDuration = focusDuration;
    }

    /**
     * Gives the actions that can be performed using the broadsword.
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
        }
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actions.add(new SellAction("sells the Broadsword", this, 100));
        }
        return actions;
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = super.allowableActions(owner);
        actionList.add(new FocusAction(this));
        return actionList;
    }

    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        actor.addBalance(sellingAmount);
        actor.removeItemFromInventory(this);
        return "sells the Broadsword for " + sellingAmount + " runes";
    }

    @Override
    public String buyItem(Actor actor, int buyingAmount) {
        Random random = new Random();
        int chance = random.nextInt(20);
        if (actor.getBalance() < buyingAmount) {
            return "cannot afford " + this;
        }
        if (chance > 1) {
            actor.addItemToInventory(this);
            actor.deductBalance(buyingAmount);
            return "buys the Broadsword for " + buyingAmount + " runes";
        } else{
            actor.deductBalance(buyingAmount);
            return "Your runes were stolen!";
        }

    }
}

