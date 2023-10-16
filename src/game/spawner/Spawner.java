package game.spawner;

import edu.monash.fit2099.engine.positions.Location;
/**
 * A class that represents an interface for spawning actors
 *
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @version 1.0.0
 */

public abstract class Spawner {
    public abstract void spawnEnemy(Location location);
}
