package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.WanderingUndead;

import java.util.Random;
/**
 * Spawner for the Hollow Soldier Enemies
 *
 * Created by:
 * @author Ewan Lumsden Smith
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see Spawner
 */
public class WanderingUndeadSpawner implements Spawner {
    /**
     * variable that holds the bound of spawning
     */
    private final int bound;
    /**
     * variable that holds the odds of spawning
     */
    private final int odds;
    /**
     * variable that holds the Random class object
     */
    private final Random random;

    /**
     * Constructor for the Wandering Undead spawner
     * @param odds numerator for the odds
     * @param bound denominator for the odds
     */
    public WanderingUndeadSpawner(int bound, int odds) {
        this.bound = bound;
        this.odds = odds;
        this.random = new Random();
    }

    /**
     * Spawns the Wandering Undead at the param location
     * @param location location to spawn
     */
    @Override
    public void spawnEnemy(Location location) {
        if(random.nextInt(bound) < odds){
            location.addActor(new WanderingUndead());
        }
    }
}
