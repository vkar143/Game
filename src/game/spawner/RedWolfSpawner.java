package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.RedWolf;

import java.util.Random;

/**
 * Spawner for the Red Wolf enemy
 */
public class RedWolfSpawner implements Spawner{
    /**
     * variable that holds the odds of spawning
     */
    private final int odds;
    /**
     * variable that holds the odds of spawning
     */
    private final int bound;
    /**
     * variable that holds the Random class object
     */
    private final Random random;

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
    public void spawnEnemy(Location location) {
        if(random.nextInt(bound) < odds){
            EnemyActor newEnemy = new RedWolf();
            location.map().addActor(newEnemy,location);
        }
    }
}
