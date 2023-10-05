package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.HollowSoldier;

import java.util.Random;

public class HollowSoldierSpawner implements Spawner {
    private final int odds;
    private final int bound;
    private final Random random;
    public HollowSoldierSpawner(int odds, int bound) {
        this.odds = odds;
        this.bound = bound;
        this.random = new Random();
    }

    @Override
    public void spawnEnemy(Location _location) {
        if(random.nextInt(bound) < odds){
            _location.map().addActor(new HollowSoldier(), _location);
    }}
}
