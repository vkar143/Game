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
 * healing vial gives the replinish action for HP
 */
public class HealingVial extends Item implements Consumable, SellableItem, BuyableItem {
    private Random random;
    private final int SELLING_AMOUNT = 35;
    private final int BOUND_SELL_ITEM = 10;
    private final int BOUND_BUY_ITEM = 4;
    private final int CHANCE_SELL_ITEM = 1;
    private final int CHANCE_BUY_ITEM = 1;
    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial", 'a', true);
        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
        this.random = new Random();
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
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = super.allowableActions(target, location);
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actionList.add(new SellAction("sells the Healing Vial ", this, SELLING_AMOUNT));
        }
        return actionList;
    }

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
