package game.weather;

import java.util.ArrayList;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EnemyActor;
import game.actors.RedWolf;
import game.extended.ForestGameMap;
import game.ground.Bush;
import game.ground.Hut;
import game.ground.SpawningGround;
import game.spawner.ForestKeeperSpawner;
import game.spawner.RedWolfSpawner;

public class SunnyForestWeather implements Weather{
    float HUT_SPAWN_RATE_MULTIPLIER = 2f;
    float BUSH_SPAWN_RATE_MULTIPLIER = 1f;
    float REDWOLF_DAMAGE_MULTIPLIER = 3f;

    Hut hut = new Hut(new ForestKeeperSpawner(15,100));
    Bush bush = new Bush(new RedWolfSpawner(3,10));
    RedWolf redWolf = new RedWolf();

    public void weatherEffect(GameMap gameMap){
        ForestGameMap _gameMap = (ForestGameMap) gameMap;
        _gameMap.modifySpawnRate(HUT_SPAWN_RATE_MULTIPLIER, hut);
        _gameMap.modifySpawnRate(BUSH_SPAWN_RATE_MULTIPLIER, bush);
        _gameMap.modifyEnemyDamagaMultiplier(REDWOLF_DAMAGE_MULTIPLIER, redWolf);
    }



}
