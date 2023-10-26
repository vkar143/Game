package game.spawner;

import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;


/**
 * An interface class that helps spawn actors
 * Created by:
 * @author Ewan Lumsden Smith
 * Modified by:
 * @author Phoebe Jiang
 * @author Zhuojun Zhao
 * @version 1.0.0
 */
public interface Spawner {

    void spawnNewActor(Location location);

    void updateSpawnRateMultiplier(float spawnRateMultiplier);

}
