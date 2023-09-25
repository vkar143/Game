package game.ground;

import game.spawner.Spawner;

/**
 * abstract class that other graveyards inherit from
 */
public class Graveyard extends SpawningGround {

    public Graveyard(Spawner _spawner) {
        super('n', _spawner);

    }
}