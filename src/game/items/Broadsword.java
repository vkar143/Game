package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.general.Ability;
import game.actions.FocusAction;

/**
 * weapon item
 */
public class Broadsword extends WeaponItem {

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    private int focusDuration;
    public Broadsword(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
        this.focusDuration = 0;
        this.capabilitySet.addCapability(Ability.ATTACK);
    }

    /**
     * returns focus duration
     * @return
     */
    public int getFocusDuration() {
        return focusDuration;
    }

    /**
     * decreases focus duration each turn and when focus duration runs out it sets the weapon stats back to normal
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        this.setFocusDuration(getFocusDuration()-1);
        if(getFocusDuration() == 0){
            updateDamageMultiplier(1);
            updateHitRate(80);
        }
    }

    /**
     * sets focus duration
     * @param focusDuration
     */
    public void setFocusDuration(int focusDuration) {
        this.focusDuration = focusDuration;
    }

    /**
     * Gives the actions that can be performed using the broadsword.
     *
     * @param target The target actor to attack.
     * @param location The location where the attack action occurs.
     * @return An ActionList containing allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(new AttackAction(target, location.toString(), this));
        return actions;
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        return super.allowableActions(owner);
    }
}

