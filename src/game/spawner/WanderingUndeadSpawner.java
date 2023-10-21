package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.RedWolf;
import game.actors.WanderingUndead;

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
public class WanderingUndeadSpawner extends Spawner {
    /**
     * Construct for the Wandering Undead Spawner
     * @param odds odds of spawning
     * @param bound bound for checking the odds
     * @param random random generator
     */
    public WanderingUndeadSpawner(int odds, int bound, Random random) {
        super(odds, bound, random);
    }

    /**
     * returns an instance of a WanderingUndead
     * @return an Actor instance of a WanderingUndead
     */
    @Override
    public Actor getNewActor() {
        return new WanderingUndead();
    }
}
