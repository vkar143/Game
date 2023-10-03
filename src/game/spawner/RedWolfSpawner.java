package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.RedWolf;

import java.util.Random;

public class RedWolfSpawner implements Spawner{
    private final int odds;
    private final int bound;
    private final Random random;

    public RedWolfSpawner(int odds, int bound) {
        this.odds = odds;
        this.bound = bound;
        this.random = new Random();
    }

    @Override
    public void spawnEnemy(Location location) {
        if(random.nextInt(bound) < odds){
            EnemyActor newEnemy = new RedWolf();
            location.map().addActor(newEnemy,location);
        }
    }
}
