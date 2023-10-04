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
    private final int HEALTH_INCREASE_VALUE = 5;
    private final int SELLING_AMOUNT = 10;
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
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,HEALTH_INCREASE_VALUE);
        actor.removeItemFromInventory(this);
    }

    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList =  super.allowableActions(target, location);
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actionList.add(new SellAction("sells the BloodBerry", this, SELLING_AMOUNT));
        }
        return actionList;
    }

    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        actor.addBalance(sellingAmount);
        actor.removeItemFromInventory(this);
        return "sells the BloodBerry for " + sellingAmount + " runes";
    }
}
