package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class FollowBehavior implements Behaviour {
    private Actor target;
    public FollowBehavior(Actor actor){
        this.target = actor;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(map.contains(target) && map.contains(actor)){
            Location targetLocation = map.locationOf(target);
            Location enemyLocation = map.locationOf(actor);
            int x = targetLocation.x() - enemyLocation.x();
            int y = targetLocation.y() - enemyLocation.y();
            int xDir = Integer.compare(x, 0);
            int yDir = Integer.compare(y, 0);

            Location bestMoveLocation = null;
            int bestMoveScore = Integer.MAX_VALUE;

            for (Exit exit : enemyLocation.getExits()) {
                Location dest = exit.getDestination();
                if (dest.x() == enemyLocation.x() + xDir && dest.y() == enemyLocation.y() + yDir) {
                    int moveScore = calculateMoveScore(dest, actor);
                    if (moveScore < bestMoveScore) {
                        bestMoveLocation = dest;
                        bestMoveScore = moveScore;
                    }
                }
            }

            if (bestMoveLocation != null) {
                return new MoveActorAction(bestMoveLocation, "towards player");
            }
        }

        return null;
    }

    private int calculateMoveScore(Location location, Actor actor) {
        if (location.canActorEnter(actor)) {
            return 0;
        } else {
            return Integer.MAX_VALUE;
        }
    }

}
