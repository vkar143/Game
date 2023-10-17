package game.weather;

import edu.monash.fit2099.engine.positions.GameMap;
import game.extended.ForestGameMap;
import game.ground.Bush;
import game.ground.Hut;
import game.spawner.ForestKeeperSpawner;
import game.spawner.RedWolfSpawner;

public class RainyForestWeather extends ForestWeather implements Weather{
    Hut hut = new Hut(new ForestKeeperSpawner(15,100));
    Bush bush = new Bush(new RedWolfSpawner(3,10));

    public void weatherEffect(GameMap gameMap){
        ForestGameMap _gameMap = (ForestGameMap) gameMap;
        modifySpawnRate(_gameMap, hut, 1f);
        modifySpawnRate(_gameMap, bush, 1.5f);
    }
    
}
