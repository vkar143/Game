package game.ground;

import game.spawner.Spawner;

/**
 * The hut class is used to spawn Forest Keepers
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
