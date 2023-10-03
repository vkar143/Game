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


public class GreatKnife extends WeaponItem implements SellableItem, BuyableItem{
    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '>', 75, "stabs", 70);
        this.capabilitySet.addCapability(Ability.ATTACK);
        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
    }
    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actions = super.allowableActions(location);
        if(target.hasCapability(Status.ENEMY)){
            actions.add(new AttackAction(target, location.toString(), this));
            actions.add(new StabAndStepAction(target, location.toString(), this));
        }
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actions.add(new SellAction("sells the Giant Hammer", this, 175));
        }
        return actions;
    }

    @Override
    public String buyItem(Actor actor, int buyingAmount) {
        Random random = new Random();
        int chance = random.nextInt(20);
        if (actor.getBalance() < buyingAmount) {
            return "cannot afford " + this;
        }
        if (chance > 1) {
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

    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        Random random = new Random();
        int chance = random.nextInt(10);

        if (chance > 1) {
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
