package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.general.Ability;

/**
 * kills any player who steps on it
 */
public class Void extends Ground {

    /**
     * Constructor. sets char to '+'
     */
    public Void() {
        super('+');
    }

    /**
     * returns true
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return super.canActorEnter(actor);
    }

    /**
     * checks each turn if the actor standing on it can walk on voids if not it sets them to unconscious
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (location.containsAnActor() && (!location.getActor().hasCapability(Ability.WALK_ON_VOID))) {
            location.getActor().modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, Integer.MAX_VALUE);
        }
    }

}