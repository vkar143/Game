package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.spawner.Spawner;

/**
 * abstract class that other graveyards inherit from
 */
public class Graveyard extends SpawningGround {

    public Graveyard(Spawner _spawner) {
        super('n', _spawner);
    }

    @Override
    public void tick(Location location) {
        ;
    }
}