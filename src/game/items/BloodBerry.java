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

public class BloodBerry extends Item implements Consumable, SellableItem {

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
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actionList =  super.allowableActions(otherActor, location);
        actionList.add(new SellAction("Sell BloodBerry", this));
        return actionList;
    }

    @Override
    public void sellItem(Actor actor) {
        int sellingAmount = 10;
        actor.addBalance(sellingAmount);
        actor.removeItemFromInventory(this);
    }
}
