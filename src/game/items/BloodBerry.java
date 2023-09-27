package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumableAction;
import game.general.Ability;

import java.util.concurrent.CopyOnWriteArrayList;

public class BloodBerry extends Item implements Consumable, SellItem{

    /***
     * Constructor.
     */
    public BloodBerry() {
        super("BloodyBerry", '*', true);
        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = super.allowableActions(owner);
        actionList.add(new ConsumableAction("eats the bloodberry", this));
        return actionList;
    }

    @Override
    public void consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,5);
        actor.removeItemFromInventory(this);
    }

    @Override
    public void sellItem(Actor actor) {
        int sellingAmount = 10;
        actor.addItemToInventory(new Runes(sellingAmount));
        actor.removeItemFromInventory(this);
    }
}
