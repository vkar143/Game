package game.ground;

import game.spawner.Spawner;

/**
 * A type of spawning Ground used to spawn the HollowSoldier and WanderingUndead
 * Created by:
 * @author Ewan Lumsden-Smith & Vasi
 * @version 1.0.0
 * @see SpawningGround
 */
public class Graveyard extends SpawningGround {

    /**
     * Constructor for the Graveyard Class which takes a spawner as a parameter.
     * @param _spawner the Spawner used by the object
     */
    public Graveyard(Spawner _spawner) {
        super('n', _spawner);
    }
}