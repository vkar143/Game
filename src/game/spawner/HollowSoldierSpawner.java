package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.HollowSoldier;

import java.util.Random;

/**
 * Spawner for the Hollow Soldier Enemies
 *
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see Spawner
 */
public class HollowSoldierSpawner implements Spawner {
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
     * Constructor for the hollow soldier spawner
     * @param odds numerator for the odds
     * @param bound denominator for the odds
     */
    public HollowSoldierSpawner(int odds, int bound) {
        this.odds = odds;
        this.bound = bound;
        this.random = new Random();
    }
    /**
     * Method to spawn enemy checks likelihood then spawns hollow soldier at its location
     * @param _location
     */
    @Override
    public void spawnEnemy(Location _location) {
        if(random.nextInt(bound) < odds){
            _location.map().addActor(new HollowSoldier(), _location);
    }}
}
