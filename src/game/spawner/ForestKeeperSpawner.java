package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.ForestKeeper;
import java.util.Random;

/**
 * Spawner for the Forest Keeper Enemies
 *
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see Spawner
 */
public class ForestKeeperSpawner extends Spawner{
    /**
     * variable that holds the odds of spawning
     */
    private final int odds;
    /**
     * variable that holds the bound of spawning
     */
    private final int bound;
    /**
     * variable that holds the Random class object
     */
    private final Random random;

    /**
     * Constructor for the ForestKeeperSpawner
     * @param odds The numerator for the odds
     * @param bound The denominator for the odds
     */
    public ForestKeeperSpawner(int odds, int bound) {
        this.odds = odds;
        this.bound = bound;
        this.random = new Random();
    }

    /**
     * Method to spawn enemy checks likelihood then spawns forest keeper at its location
     * @param location
     */
    @Override
    public void spawnActor(Location location) {
        if(random.nextInt(bound) < odds){
            EnemyActor newEnemy = new ForestKeeper();
            location.map().addActor(newEnemy,location);
        }
    }
}
