package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
/**
 * A class that represents an enemies ability to attack an actor.
 * Created by:
 * @author Vasi Karabourniotis
 */
public class AttackBehaviour implements Behaviour{
    /**
     * Constructor for AttackBehaviour class
     */
    public AttackBehaviour() {}

    /**
     * This allows the actor to see if there are other actors in its exits
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return It returns an AttackAction on the other actor in its vicinity or a do nothing action if not applicable
     */
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for(Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor() && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return new AttackAction(exit.getDestination().getActor(), exit.getDestination().getActor().toString());
            }
        }
        return null;
    }

}

