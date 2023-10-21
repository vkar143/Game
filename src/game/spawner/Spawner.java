package game.spawner;

import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;


/**
 * A class that helps spawn actors
 *
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @author Zhuojun Zhao
 * @version 1.0.0
 */

/**
 * changed the layout of the Spawner class to make it more extendable
 */
public abstract class Spawner {

    protected float spawnRateMultiplier = 1.0f;
    protected int odds;
    protected int bound;
    protected Random random;

    public Spawner(int odds, int bound, Random random){
        this.odds = odds;
        this.bound = bound;
        this.random = random;
    }

    public abstract Actor getNewActor();

    public void spawnNewActor(Location location){
        if(shouldSpawn()){
            placeActorInLocation(location);
        }
    }
    protected boolean shouldSpawn() {
        return random.nextInt(bound) < odds * spawnRateMultiplier;
    }
    protected void placeActorInLocation(Location location) {
        Actor newActor = getNewActor();
        for (Exit exit : location.getExits()) {
            Location _location = exit.getDestination();
            if (_location.canActorEnter(newActor)) {
                _location.addActor(newActor);
                return;
            }
        }
    }
    public void updateSpawnRateMultiplier(float newMultiplier){
        this.spawnRateMultiplier = newMultiplier;
    }
}
