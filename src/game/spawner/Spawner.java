package game.spawner;

import java.util.Random;

import edu.monash.fit2099.engine.positions.Location;
/**
 * A class that helps spawn actors
 *
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @version 1.0.0
 */

public abstract class Spawner {
    /**
     * spawning rate multiplier
     */
    protected float spawnRateMultiplier = 1.0f;
    /**
     * variable that holds the odds of spawning
     */
    protected  int odds;
    /**
     * variable that holds the bound of spawning
     */
    protected  int bound;
    /**
     * variable that holds the Random class object
     */
    protected Random random;
    
    public abstract void spawnActor(Location location);

    public void updateSpawnRateMultiplier(float newMultiplier){
        this.spawnRateMultiplier = newMultiplier;
    }

    public void resetSpawnRateMultiplier(){
        this.spawnRateMultiplier = 1.0f;
    }

}
