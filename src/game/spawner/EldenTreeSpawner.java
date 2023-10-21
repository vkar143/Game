package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EldentreeGuardian;
import game.actors.EnemyActor;
import game.actors.LivingTreeBranch;
import game.actors.RedWolf;

import java.util.Random;

public class EldenTreeSpawner extends Spawner {
    /**
     * Construct for the Eldentree Spawner
     * @param odds odds of spawning
     * @param bound bound for checking the odds
     * @param random random generator
     */
    public EldenTreeSpawner(int odds, int bound, Random random) {
        super(odds, bound, random);
    }

    /**
     * Creates an instance of EldenTreeGuardian
     * @return an instance of the EldentreeGuardian Actor class
     */
    @Override
    public Actor getNewActor() {
        return new EldentreeGuardian();
    }
}

