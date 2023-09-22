package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumableAction;

public class Runes extends Item implements Consumable{
    private int amount;
    /***
     * Constructor.
     */
    public Runes(int amount) {
        super("Runes", '$', true);
        this.amount = amount;
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = super.allowableActions(owner);
        actionList.add(new ConsumableAction("Add " + amount + " runes to wallet", this));
        return actionList;
    }

    @Override
    public void consume(Actor actor) {
        actor.addBalance(amount);
        actor.removeItemFromInventory(this);
    }
}
