package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawner.Spawner;

public abstract class SpawningGround extends Ground {

    protected Spawner spawner;

    public SpawningGround(char displayChar, Spawner _spawner) {
        super(displayChar);
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
