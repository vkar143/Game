package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.RedWolf;

import java.util.Random;

/**
 * Spawner for the Red Wolf enemy
 *
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see Spawner
 */
public class RedWolfSpawner extends Spawner{


    /**
     * Constructor for the red wolf spawner
     * @param odds numerator for the odds
     * @param bound denominator for the odds
     */
    public RedWolfSpawner(int odds, int bound) {
        this.odds = odds;
        this.bound = bound;
        this.random = new Random();
    }

    /**
     * Spawns the Red Wolf at the param location
     * @param location location to spawn
     */
    @Override
    public void spawnActor(Location location) {
        if(random.nextInt(bound) < Math.round(odds * spawnRateMultiplier)){
            EnemyActor newEnemy = new RedWolf();
            location.map().addActor(newEnemy,location);
        }
    }

}
