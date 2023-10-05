package game.ground;

import game.spawner.Spawner;

/**
 * The bushes class is used to spawn RedWolf
 */
public class Bushes extends SpawningGround {
    /**
     * Constructor which takes a spawner object as a param
     * @param _spawner The spawner object for this spawning ground
     */
    public Bushes(Spawner _spawner) {
        super('m', _spawner);
    }
}
