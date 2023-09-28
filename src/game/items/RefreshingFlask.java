package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumableAction;
import game.actions.SellAction;
import game.general.Ability;

import java.util.Random;

/**
 * refreshing flask allows the actor to replenish stamina
 */
public class RefreshingFlask extends Item implements Consumable, SellableItem, BuyableItem {
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("Refreshing Flask", 'u', true);
        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
    }

    /**
     * returns a replenish action that replenishes the users Stamina
     * @param owner the actor that owns the item
     * @return
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionlist = new ActionList();
        actionlist.add(super.allowableActions(owner));
        actionlist.add(new ConsumableAction("drink refreshing flask",this));
        return actionlist;
    }

    @Override
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 40);
        actor.removeItemFromInventory(this);
    }

    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = super.allowableActions(target, location);
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actionList.add(new SellAction("sells the Refreshing Flask", this, 25));
        }
        return actionList;
    }

    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        Random random = new Random();
        int chance = random.nextInt(10);
        if (chance < 5){
            actor.removeItemFromInventory(this);
        } else {
            actor.addBalance(sellingAmount);
            actor.removeItemFromInventory(this);
        }
        return "sells the Refreshing Flask for " + sellingAmount + " runes";
    }

    @Override
    public String buyItem(Actor actor, int buyingAmount) {
        Random random = new Random();
        int chance = random.nextInt(10);
        int discountedAmount = 15;
        if (chance < 1 ){
            buyingAmount = buyingAmount - discountedAmount;
            actor.deductBalance(buyingAmount);
        } else if (actor.getBalance() > buyingAmount){
            actor.deductBalance(buyingAmount);
            actor.addItemToInventory(this);
        } else {
            return  "cannot afford " + this;
        }
        return "buys the Refreshing Flask for " + buyingAmount + " runes";
    }
}

