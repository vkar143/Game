package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.ForestKeeper;
import game.actors.LivingTreeBranch;

import java.util.Random;

public class TwigSpawner implements Spawner{
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

    public TwigSpawner(int bound, int odds) {
        this.bound = bound;
        this.odds = odds;
        this.random = new Random();
    }
    @Override
    public void spawnEnemy(Location location) {
        if(random.nextInt(bound) < odds) {
            EnemyActor newEnemy = new LivingTreeBranch();
            if (location.canActorEnter(newEnemy)) {
                location.map().addActor(newEnemy, location);
            } else {
                throw new IllegalArgumentException("Can't spawn there");
            }
        }
    }
}
