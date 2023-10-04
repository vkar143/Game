package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.GreatSlamAction;
import game.actions.SellAction;
import game.general.Ability;
import game.general.Status;

public class GiantHammer extends WeaponItem implements SellableItem {
    private final int SELLING_AMOUNT = 250;
    private static final int DAMAGE = 160;
    private static final int HIT_RATE = 90;
    /**
     * Constructor.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', DAMAGE, "slams", HIT_RATE);
        this.capabilitySet.addCapability(Ability.ATTACK);
        this.capabilitySet.addCapability(Ability.CAN_BE_SOLD);
    }

    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actions = super.allowableActions(location);
        if(target.hasCapability(Status.ENEMY)){
            actions.add(new AttackAction(target, location.toString(), this));
            actions.add(new GreatSlamAction(target, location.toString(), this));
        }
        if (target.hasCapability(Ability.CAN_TRADE)) {
            actions.add(new SellAction("sells the Giant Hammer", this, SELLING_AMOUNT));
        }
        return actions;
    }


    @Override
    public String sellItem(Actor actor, int sellingAmount) {
        actor.addBalance(sellingAmount);
        actor.removeItemFromInventory(this);
        return "sells the Giant Hammer for " + sellingAmount + " runes";
    }
}
