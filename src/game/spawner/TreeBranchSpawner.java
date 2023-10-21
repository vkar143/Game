package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.LivingTreeBranch;

import java.util.Random;

public class TreeBranchSpawner extends Spawner{
    /**
     * Construct for the Tree Branch Spawner
     * @param odds odds of spawning
     * @param bound bound for checking the odds
     * @param random random generator
     */
    public TreeBranchSpawner(int odds, int bound, Random random) {
        super(odds, bound, random);
    }

    /**
     * creates a new LivingTreeBranch
     * @return an instance of the LivingTreeBranch class
     */
    @Override
    public Actor getNewActor() {
        return new LivingTreeBranch();
    }
}
