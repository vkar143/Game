package game.grounds;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class that represents a void on the floor
 * Created by:
 * @author Vasi Karabourniotis
 */
public class Void extends Ground {
    public Void() {
        super('+');
    }

    /**
     * If an actor enters void they will die
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.NOT_DEAD)) {
            location.getActor().modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, Integer.MAX_VALUE);
            location.getActor().modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, Integer.MAX_VALUE);
        } else if (location.containsAnActor()){
            location.getActor().modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, Integer.MAX_VALUE);
        }
    }


}
