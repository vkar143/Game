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

    public ForestKeeperSpawner(int odds, int bound, Random random) {
        super(odds, bound,random);
    }
    /**
     * Method to spawn enemy checks likelihood then spawns forest keeper at its location
     * @param location
     */
    @Override
    public Actor getNewActor() {
        return new ForestKeeper();
    }
}
