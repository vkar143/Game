package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.HollowSoldier;
import game.actors.RedWolf;

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
public class HollowSoldierSpawner extends Spawner {

    public HollowSoldierSpawner(int odds, int bound, Random random) {
        super(odds, bound, random);
    }

    /**
     * Method to spawn enemy checks likelihood then spawns hollow soldier at its location
     * @param _location
     */
    @Override
    public Actor getNewActor() {
        return new HollowSoldier();
    }
}
