package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.ForestKeeper;
import game.actors.RedWolf;
import game.actors.WanderingUndead;

import java.util.Random;

/**
 * Spawner for the Forest Keeper Enemies
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see Spawner
 */
public class ForestKeeperSpawner implements Spawner{
    private float spawnRate;
    private final Random random;
    /**
     * Construct for the ForestKeeper Spawner
     * @param spawnRate for the chance of spawning
     * @param random random generator
     */
    public ForestKeeperSpawner(float spawnRate, Random random) {
        this.spawnRate = spawnRate;
        this.random = random;
    }

    @Override
    public void spawnNewActor(Location location) {
        if(random.nextFloat() < spawnRate){
            Actor newEnemy = new ForestKeeper();
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
