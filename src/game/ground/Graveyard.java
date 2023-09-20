package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawner.Spawner;

/**
 * abstract class that other graveyards inherit from
 */
public class Graveyard extends Ground {
    private Spawner spawner;

    public Graveyard(Spawner _spawner) {
        super('n');
        this.spawner = _spawner;

    }
    public void Spawn(Location location) {
        for(Exit exit: location.getExits()) {
            Location _location = exit.getDestination();
            if (!(_location.containsAnActor())) {
                try{
                    spawner.spawnEnemy(location);
                    return;
                } catch (Exception e){
                    return;
                }
            }

        }
    }

    @Override
    public void tick(Location location) {
        Spawn(location);
    }
}