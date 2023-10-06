package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumableAction;
import game.items.Consumable;

import java.util.List;

/**
 * a cosmetic piece of ground
 */
public class Puddle extends Ground implements Consumable {
    /**
     * Constant for the value of the health increase
     */
    private final int HEALTH_INCREASE_VALUE = 1;

    /**
     * constructor sets the display char
     */
    public Puddle() {
        super('~');
    }


    /**
     * the consume action used to heal and refresh the player
     *
     * @param actor The actor which can consume the item
     */
    @Override
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HEALTH_INCREASE_VALUE);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, actor.getAttributeMaximum(BaseActorAttributes.STAMINA) / 100);
    }

    /**
     * returns the ConsumableAction when the player is standing exactly on the puddle
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionlist = super.allowableActions(actor, location, direction);
        if (location.getActor() == actor) {
            actionlist.add(new ConsumableAction("drinks from puddle", this));
        }
        return actionlist;
    }
}
