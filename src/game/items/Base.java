package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * the player base for the player it ticks each turn and makes any necessary adjustments to their stats
 */
public class Base extends Item {
    /***
     * Constructor.
     */
    public Base() {
        super("Player Base", '#', false);
    }

    /**
     * with each turn it adjusts the actors stats
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, actor.getAttributeMaximum(BaseActorAttributes.STAMINA)/100);
        }
    }

