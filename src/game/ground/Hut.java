package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import game.spawner.Spawner;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Ewan Lumsden Smith
 * @version 1.0.0
 * @see Ground
 */
public class Hut extends SpawningGround {

    /**
     * constructor takes a spawner as a parameter
     * @param _spawner The Spawning Object used by the spawning ground
     */
    public Hut(Spawner _spawner) {
        super('h', _spawner);
    }
}
