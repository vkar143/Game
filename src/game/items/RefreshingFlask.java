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
 * A class that represents the item Refreshing Flask
 */
public class RefreshingFlask extends Item implements Consumable, SellableItem, BuyableItem {
    private Random random;
    private final int STAMINA_INCREASE_VALUE = 40;
    private final int SELLING_AMOUNT = 25;
    private final int BOUND_SELL_ITEM = 10;
    private final int BOUND_BUY_ITEM = 10;
    private final int CHANCE_SELL_ITEM = 5;
    private final int CHANCE_BUY_ITEM = 1;

    /**
     * A constructor that creates an instance for Refreshing Flask
     */
    public RefreshingFlask() {
        super("Refreshing Flask", 'u', true);
//        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
        this.random = new Random();
    }

    /**
     * A collection of actions that can be performed using the Refreshing Flask
     * @param owner the actor that owns the item
     * @return An unmodified collection of actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionlist = new ActionList();
        actionlist.add(super.allowableActions(owner));
        actionlist.add(new ConsumableAction("drink refreshing flask",this));
        return actionlist;
    }

    /**
     * By consuming the Refreshing Flask the actor's stamina will increase by a specific amount
     * @param actor The actor that consumes the item
     */
    @Override
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, STAMINA_INCREASE_VALUE);
        actor.removeItemFromInventory(this);
    }

    /**
     * List of allowable actions that the Refreshing Flask allows its owner do to another actor.
     * @param target the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of actions for Refreshing Flask
     */
    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = super.allowableActions(target, location);
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actionList.add(new SellAction("sells the Refreshing Flask", this, SELLING_AMOUNT));
        }
        return actionList;
    }

    /**
     * The specific actions that occur when Refreshing Flask is sold
     * @param actor The actor which can sell the item
     * @param sellingAmount The amount the item will be sold for
     * @return A string that describes the result of executing the sell Refreshing Flask method
     */
    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        int chance = random.nextInt(BOUND_SELL_ITEM);
        if (chance < CHANCE_SELL_ITEM){
            actor.removeItemFromInventory(this);
        } else {
            actor.addBalance(sellingAmount);
            actor.removeItemFromInventory(this);
        }
        return "sells the Refreshing Flask for " + sellingAmount + " runes";
    }

    /**
     * The specific actions that occur when Refreshing Flask is bought
     * @param actor The actor which can buy the item
     * @param buyingAmount The amount the item will be bought for
     * @return A string that describes the result of executing the buy Refreshing Flask method
     */
    @Override
    public String buyItem(Actor actor, int buyingAmount) {
        int chance = random.nextInt(BOUND_BUY_ITEM);
        double discountedAmount = 0.8;
        if (chance < CHANCE_BUY_ITEM){
            buyingAmount = (int) (buyingAmount * discountedAmount);
        }
        if (actor.getBalance() > buyingAmount){
            actor.deductBalance(buyingAmount);
            actor.addItemToInventory(this);
        } else {
            return  "cannot afford " + this;
        }
        return "buys the Refreshing Flask for " + buyingAmount + " runes";
    }
}

