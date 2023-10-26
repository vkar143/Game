package game.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.*;

import java.util.Random;

public class EldenTreeSpawner implements Spawner {
    private float spawnRate;
    private Random random;
    /**
     * Construct for the Eldentree Guardian Spawner
     * @param spawnRate for the chance of spawning
     * @param random random generator
     */
    public EldenTreeSpawner(float spawnRate, Random random) {
        this.spawnRate = spawnRate;
        this.random = random;
    }

    @Override
    public void spawnNewActor(Location location) {
        if(random.nextFloat() < spawnRate){
            Actor newEnemy = new EldentreeGuardian();
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

