package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.LivingTreeBranch;
import game.actors.WanderingUndead;

import java.util.Random;

public class LivingTreeBranchSpawner implements Spawner{
    private float spawnRate;
    private final Random random;
    /**
     * Construct for the Tree Branch Spawner
     * @param spawnRate odds of spawning
     * @param random random generator
     */
    public LivingTreeBranchSpawner(float spawnRate, Random random) {
        this.spawnRate = spawnRate;
        this.random = random;
    }

    @Override
    public void spawnNewActor(Location location) {
        if(random.nextFloat() < spawnRate){
            Actor newEnemy = new LivingTreeBranch();
            for(Exit exit: location.getExits()){
                if(exit.getDestination().canActorEnter(newEnemy)){
                    exit.getDestination().addActor(newEnemy);
                }
            }
        }
    }

    @Override
    public void updateSpawnRateMultiplier(float spawnRateMultiplier) {
        this.spawnRate *= spawnRateMultiplier;
    }
}
