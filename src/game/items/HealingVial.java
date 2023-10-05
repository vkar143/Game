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
 * A class that represents the Healing Vial
 */
public class HealingVial extends Item implements Consumable, SellableItem, BuyableItem {
    private Random random;
    private final int SELLING_AMOUNT = 35;
    private final int BOUND_SELL_ITEM = 10;
    private final int BOUND_BUY_ITEM = 4;
    private final int CHANCE_SELL_ITEM = 1;
    private final int CHANCE_BUY_ITEM = 1;

    /**
     * A constructor that creates an instance for Healing Vial
     */
    public HealingVial() {
        super("Healing Vial", 'a', true);
//        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
        this.random = new Random();
    }

    /**
     * Returns a replenish action object
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

    /**
     * By consuming the Healing Vial the actor's health will increase by a specific amount
     * @param actor The actor that consumes the item
     */
    @Override
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, actor.getAttributeMaximum(BaseActorAttributes.HEALTH)/5);
        actor.removeItemFromInventory(this);
    }

    /**
     * List of allowable actions that the Healing Vial allows its owner do to another actor.
     * @param target the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of actions for Healing Vial
     */
    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = super.allowableActions(target, location);
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actionList.add(new SellAction("sells the Healing Vial ", this, SELLING_AMOUNT));
        }
        return actionList;
    }

    /**
     * The specific actions that occur when Healing Vial is sold
     * @param actor The actor which can sell the item
     * @param sellingAmount The amount the item will be sold for
     * @return A string that describes the result of executing the sell Healing Vial method
     */
    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        Random random = new Random();
        int chance = random.nextInt(BOUND_SELL_ITEM);
        if (chance < CHANCE_SELL_ITEM) {
            sellingAmount = sellingAmount * 2;
        }
        actor.addBalance(sellingAmount);
        actor.removeItemFromInventory(this);
        return "sells the Healing Vial for " + sellingAmount + " runes";
    }

    /**
     * The specific actions that occur when Healing Vial is bought
     * @param actor The actor which can buy the item
     * @param buyingAmount The amount the item will be bought for
     * @return A string that describes the result of executing the buy Healing Vial method
     */
    @Override
    public String buyItem(Actor actor, int buyingAmount) {
        int chance = random.nextInt(BOUND_BUY_ITEM);
        double increasedAmount = 1.50;
        if (chance < CHANCE_BUY_ITEM) {
            buyingAmount = (int) (buyingAmount * increasedAmount);
        }
        if(actor.getBalance() > buyingAmount){
            actor.deductBalance(buyingAmount);
            actor.addItemToInventory(this);
            return "buys the Refreshing Flask for " + buyingAmount + " runes";
        } else {
            return  "cannot afford the " + this;
        }
    }
}
