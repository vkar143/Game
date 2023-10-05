package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumableAction;

/**
 * Runes are dropped by enemies and used to trade with
 */
public class Runes extends Item implements Consumable{
    /**
     * The Amount of runes for the object
     */
    private final int amount;
    /***
     * Constructor.
     */
    public Runes(int amount) {
        super("Runes", '$', true);
        this.amount = amount;
    }

    /**
     * returns the list of allowable actions when the Runes are being held by the player
     * @param owner the actor that owns the item
     * @return returns a ConsumableAction to add the Runes to wallet
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = super.allowableActions(owner);
        actionList.add(new ConsumableAction("Add " + amount + " runes to wallet", this));
        return actionList;
    }

    /**
     * Consume interface used to add the runes to wallet
     * @param actor The actor which can consume the item
     */
    @Override
    public void consume(Actor actor) {
        actor.addBalance(amount);
        actor.removeItemFromInventory(this);
    }
}
