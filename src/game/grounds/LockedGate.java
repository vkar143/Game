package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.UnlockAction;

/**
 * A class that represents a lockedGate that acts as a portal between different maps.
 * Created by:
 * @author Vasi Karabourniotis
 */
public class LockedGate extends Ground {
    private Action travelAction;
    /**
     * Constructor.
     */
    public LockedGate() {
        super('=');
    }

    /**
     * Is an action that enables the actor to teleport between maps
     * @param newAction Can be given an action to be implemented by the lockedGate
     */
    public void addSampleAction(Action newAction){
        this.travelAction = newAction;
    }

    /**
     * The actions that the actor can execute through the lockedGate
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return An action to be executed
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (actor.hasCapability(Ability.CAN_UNLOCK_DOOR)){
        actions.add(travelAction);
        }
        actions.add(new UnlockAction());
        return actions;
    }

    /**
     * It allows lockedGate to experience the passage of time and will only
     * allow actor to enter is they have the ability to unlock the door
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Ability.CAN_UNLOCK_DOOR)){
            canActorEnter(location.getActor());
        }
    }
}
