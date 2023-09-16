package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Ability;
import game.actions.AttackAction;
import game.actions.FocusAction;

/**
 * A weapon class named broadsword that extends WeaponItem
 * Created by:
 * @author Vasi Karas
 */
public class Broadsword extends WeaponItem {
    private final int originalHitRate;
    private boolean focusActive;
    private int counter;

    /**
     * Constructor which inherits the WeaponItem's constructor attributes
     */
    public Broadsword() {
        super("Broadsword", '1', 110, "swings", 80);
        this.addCapability(Ability.HAS_BROADSWORD);
        this.originalHitRate = 80;
        focusActive = true;
    }

    /**
     * List of allowable actions that the item can perform to the current actor
     * that allows for the actor to use the special powers of the Broadsword
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = new ActionList();
        actions.add(new ActionList(new FocusAction()));
        return actions;
    }

    /**
     * List of allowable actions that the weapon allows its owner to perform on another actor (attack).
     * @param otherActor the other actor
     * @param location   the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(otherActor, location.toString(), this));
        return actions;
    }

    /**
     * Informs broadsword of the passage of time.
     * This method is called once per turn, if Broadsword is being carried
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        if (actor.hasCapability(Ability.HAS_BROADSWORD)) {
            if (focusActive && currentLocation.getActor().hasCapability(Ability.HAS_FOCUS_ACTION) && counter <= 5){
                this.increaseDamageMultiplier(0.1f);
                this.updateHitRate(90);
                focusActive = false;
                counter++;
            } else if (actor.hasCapability(Ability.HAS_BROADSWORD)){
                this.updateDamageMultiplier(1.0f);
                this.updateHitRate(originalHitRate);
        }
        } else if (counter > 5){
            this.updateDamageMultiplier(1.0f);
            this.updateHitRate(originalHitRate);
            actor.removeCapability(Ability.HAS_BROADSWORD);
            actor.removeCapability(Ability.HAS_FOCUS_ACTION);
            counter = 0;
        }
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 2);
    }
}
