package game.ground;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.spawner.Spawner;

public class Bushes extends Graveyard {

    public Bushes(Spawner _spawner) {
        super(_spawner);
        setDisplayChar('m');
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
