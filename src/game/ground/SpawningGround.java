package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawner.Spawner;

/**
 * A class that represents the Spawning Ground.
 * Created by:
 * @author Vasi
 * @version 1.0.0
 * @see Ground
 */
public abstract class SpawningGround extends Ground {
    /**
     * Variable which holds the Spawner used the Spawning Ground
     */
    protected Spawner spawner;

    /**
     * Constructor which takes a displayChar and spawner as a parameter
     * @param displayChar the Display Character of the Spawning Ground
     * @param _spawner the Spawner used by the spawning ground
     */
    public SpawningGround(char displayChar, Spawner _spawner) {
        super(displayChar);
        this.spawner = _spawner;
    }

    /**
     * The Spawn method takes a location as a parameter and attempts to spawn an enemy.
     * @param location takes a location on which to spawn
     */
    public void Spawn(Location location) {
        for(Exit exit: location.getExits()) {
            Location _location = exit.getDestination();
            if (!(_location.containsAnActor())) {
                try{
                    spawner.spawnActor(_location);
                    return;
                } catch (Exception e){
                    return;
                }
            }
        }
    }

    /**
     * Tick method of this class calls the spawn method with the location in the parameter
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Spawn(location);
    }
    public void updateSpawnRateMultiplier(float newMultiplier){
        this.spawner.updateSpawnRateMultiplier(newMultiplier);
    }
    public void resetSpawnRateMultiplier(){
        this.spawner.resetSpawnRateMultiplier();
    }
}
