package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class FollowBehavior implements Behaviour {
    private final Actor target;

    public FollowBehavior(Actor actor) {
        this.target = actor;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor)) {
            return null;
        }
        Location targetLocation = map.locationOf(target);
        Location enemyLocation = map.locationOf(actor);
        int storedScore = Integer.MAX_VALUE;
        Location storedLocation = null;
        for (Exit exit : enemyLocation.getExits()) {
            Location dest = exit.getDestination();
            int moveScore = moveScore(targetLocation, dest);
            if (moveScore < storedScore && dest.canActorEnter(actor)) {
                storedScore = moveScore;
                storedLocation = dest;
            }
        }
        if(storedLocation != null){
            return new MoveActorAction(storedLocation, "towards player");
        }else {
            return null;
        }

    }

    private int moveScore(Location dest1, Location dest2) {
        return Math.abs(dest1.x() - dest2.x()) + Math.abs(dest1.y() - dest2.y());
    }
}
