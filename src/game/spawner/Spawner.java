package game.spawner;

import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;


/**
 * An abstract class that helps spawn actors
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @author Zhuojun Zhao
 * @version 1.0.0
 */
public abstract class Spawner {
    /**
     * The spawn rate multiplier for the spawner
     */
    protected float spawnRateMultiplier = 1.0f;
    /**
     * stores the odds of spawning a new enemy
     */
    protected final int odds;
    /**
     * Stores the bound for checking the odds
     */
    protected final int bound;
    /**
     * Stores the random generator for this instance
     */
    protected final Random random;

    /**
     * Constructor for the Spawner class
     * @param odds the odds of spawning
     * @param bound the bound for checking the odds
     * @param random the random generator
     */
    public Spawner(int odds, int bound, Random random){
        this.odds = odds;
        this.bound = bound;
        this.random = random;
    }

    /**
     * Abstract method that creates the actor unique to each spawner
     * @return The Actor to spawn.
     */
    public abstract Actor getNewActor();

    /**
     * the method called by the SpawningGround to begin logic to spawn new actor
     * @param location the location of the SpawningGround
     */
    public void spawnNewActor(Location location){
        if(shouldSpawn()){
            placeActorInLocation(location);
        }
    }

    /**
     * Checks to see if the spawner should spawn an actor this turn
     * @return boolean
     */
    protected boolean shouldSpawn() {
        return random.nextInt(bound) < odds * spawnRateMultiplier;
    }

    /**
     * checks the surrounding exits to find a place to put the actor then places them there
     * @param location the location to spawn the actor
     */
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

    /**
     * Updates the spawn rate for the spawner
     * @param newMultiplier the desired multiplier
     */
    public void updateSpawnRateMultiplier(float newMultiplier){
        this.spawnRateMultiplier = newMultiplier;
    }
}
