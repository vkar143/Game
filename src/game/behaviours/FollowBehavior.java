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
            Location targetlocation = map.locationOf(target);
            Location enemyLocation = map.locationOf(actor);
            int x = targetlocation.x() - enemyLocation.x();
            int y = targetlocation.y() - enemyLocation.y();
            int xDir = Integer.compare(x, 0);
            int yDir = Integer.compare(y, 0);
            Location movelocation = new Location(map, enemyLocation.x() + xDir, enemyLocation.y() + yDir);
            for(Exit exit: enemyLocation.getExits()){
                Location dest = exit.getDestination();
                if(dest.x() == movelocation.x() && dest.y() == movelocation.y()){
                    if(dest.canActorEnter(actor)){
                        return new MoveActorAction(dest, "towards player");
                    }
                }
            }

        }
        else{
            return null;
        }
        return null;
    }
}
