package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.general.Status;

/**
 * attack behaviour that implements behaviour and is used by the enemy to attack any other enemies
 */
public class AttackBehavior implements Behaviour {
    /**
     * checks to see if there is a enemy within its surroundings if there is it returns an attack action towards that enemy
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination) && map.getActorAt(destination).hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return (new AttackAction(map.getActorAt(destination), destination.toString()));
            }

        }
        return null;
    }
}
