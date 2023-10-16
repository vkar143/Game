package game.ground;

import game.spawner.Spawner;

/**
 * The Spawning Ground for the Red Wolf enemy.
 * Created by:
 * @author Ewan Lumsden Smith
 * @version 1.0.0
 * @see SpawningGround
 */
public class Bush extends SpawningGround {

    /**
     * Constructor which takes a spawner object as a parameter.
     * @param _spawner The spawner object for this spawning ground
     */
    public Bush(Spawner _spawner) {
        super('m', _spawner);
    }
}
