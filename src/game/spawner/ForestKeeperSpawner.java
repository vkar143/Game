package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.ForestKeeper;
import game.actors.RedWolf;

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
     * Construct for the ForestKeeper Spawner
     * @param odds odds of spawning
     * @param bound bound for checking the odds
     * @param random random generator
     */
    public ForestKeeperSpawner(int odds, int bound, Random random) {
        super(odds, bound,random);
    }

    /**
     * creates a forest Keeper instance
     * @return an instance of the ForestKeeper Actor class
     */
    @Override
    public Actor getNewActor() {
        return new ForestKeeper();
    }
}
