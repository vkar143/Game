package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.ground.Bush;
import game.ground.Hut;
import game.ground.SpawningGround;
import game.spawner.RedWolfSpawner;

public class ForestGameMap extends GameMap{
    protected Bush bush = new Bush(new RedWolfSpawner(3,10));
    
    public ForestGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    /**
     * return list of Locations that have a Ground matches the Ground we wanna look for
     */
    
    public ArrayList<Location> getCertainSpawningGrounds(Class<? extends SpawningGround> ground) {
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int x : widths) {
			for (int y : heights) {
                Ground currentGround = at(x, y).getGround();
				if(ground.isAssignableFrom(currentGround.getClass())) {
                    locations.add(at(x, y));
                }
            }
        }
        return locations;
    }

    /**
     * modify spawn rate multiplier for all instances of a certain type of SpawingGround on a map
     * @param multiplier
     * @param spawningGround
     */
     public void modifySpawnRate(float multiplier, SpawningGround spawningGround) {
        ArrayList<Location> spawnerLocations = getCertainSpawningGrounds(spawningGround.getClass());
        for(Location location : spawnerLocations) { 
            SpawningGround spawnGround = (SpawningGround)location.getGround();
            spawnGround.updateSpawnRateMultiplier(multiplier);
     }
    }

}

   

