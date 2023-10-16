package game.weather;

import java.util.ArrayList;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ForestWeatherInterface;
import game.ground.SpawningGround;

public class SunnyForestWeather extends ForestWeather{

    public void weatherEffect(ForestWeatherInterface gameMap){
        
    }
    public void modifySpawnRate(ForestWeatherInterface gamemap, SpawningGround spawningGround, float spawnRateMultiplier){
        ArrayList<Location> modifiedLocations = gamemap.getCertainSpawningGrounds(spawningGround.getClass());
        gamemap.modifySpawnRate(spawnRateMultiplier, spawningGround);
    }
}
