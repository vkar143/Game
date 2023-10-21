package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.LivingTreeBranch;

import java.util.Random;

public class TreeBranchSpawner extends Spawner{
    public TreeBranchSpawner(int odds, int bound, Random random) {
        super(odds, bound, random);
    }
    /**
     * Spawns the Red Wolf at the param location
     * @param location location to spawn
     */
    @Override
    public Actor getNewActor() {
        return new LivingTreeBranch();
    }
}
