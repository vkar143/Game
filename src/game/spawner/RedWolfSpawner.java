package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
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
     * Construct for the Red Wolf Spawner
     * @param odds odds of spawning
     * @param bound bound for checking the odds
     * @param random random generator
     */
    public RedWolfSpawner(int odds, int bound, Random random) {
        super(odds, bound, random);
    }

    /**
     * Creates a RedWolf Actor
     * @return RedWolf instance
     */
    @Override
    public Actor getNewActor() {return new RedWolf();}
}
