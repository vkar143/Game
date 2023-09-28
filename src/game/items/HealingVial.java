package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BuyAction;
import game.actions.ConsumableAction;
import game.actions.SellAction;
import game.general.Ability;

import java.util.Random;

/**
 * healing vial gives the replinish action for HP
 */
public class HealingVial extends Item implements Consumable, SellableItem, BuyableItem {

    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial", 'a', true);
        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
    }

    /**
     * returns a replenish action object
     * @param owner the actor that owns the item
     * @return
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionlist = new ActionList();
        actionlist.add(super.allowableActions(owner));
        actionlist.add(new ConsumableAction("drink healing vial" ,this));
        return actionlist;
    }

    @Override
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, actor.getAttributeMaximum(BaseActorAttributes.HEALTH)/5);
        actor.removeItemFromInventory(this);
    }
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actionList = super.allowableActions(otherActor, location);
        actionList.add(new SellAction("sells the Healing Vial ", this));
        actionList.add(new BuyAction("buys the Healing Vial", this));
        return actionList;
    }

    @Override
    public String sellItem(Actor actor) {
        Random random = new Random();
        int chance = random.nextInt(10);
        int sellingAmount = 35;
        if (chance < 1) {
            int newSellingAmount = sellingAmount * 2;
            actor.addItemToInventory(new Runes(newSellingAmount));
            actor.removeItemFromInventory(this);
        } else {
            actor.addBalance(sellingAmount);
            actor.removeItemFromInventory(this);
        }
        return actor + "sells the Healing Vial for " + sellingAmount + " runes";
    }

    @Override
    public String buyItem(Actor actor) {
        Random random = new Random();
        int chance = random.nextInt(10);
        int sellingAmount = 100;
        int increasedAmount = 50;
        if (chance < 2.5 & actor.getBalance() > sellingAmount){
            sellingAmount = sellingAmount + increasedAmount;
            actor.removeItemFromInventory(new Runes(sellingAmount));
            actor.addItemToInventory(new RefreshingFlask());
        } else if (actor.getBalance() > sellingAmount){
            actor.removeItemFromInventory(new Runes(sellingAmount));
            actor.addItemToInventory(new RefreshingFlask());
        } else {
            return actor + "cannot afford " + this;
        }
        return actor + "buys the Refreshing Flask for " + sellingAmount + " runes";
    }
}
