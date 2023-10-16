package game.weather;

import java.util.ArrayList;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ForestGameMap;
import game.actors.EnemyActor;
import game.ground.Bush;
import game.ground.Hut;
import game.ground.SpawningGround;
import game.spawner.ForestKeeperSpawner;
import game.spawner.RedWolfSpawner;

public class SunnyForestWeather extends ForestWeather{
    Hut hut = new Hut(new ForestKeeperSpawner(15,100));
    Bush bush = new Bush(new RedWolfSpawner(3,10));

    public void weatherEffect(ForestGameMap gameMap){
        modifySpawnRate(gameMap, hut, 2);
        modifySpawnRate(gameMap, bush, 0);
    }
    
    public void modifySpawnRate(ForestGameMap gamemap, SpawningGround spawningGround, float spawnRateMultiplier){
        gamemap.modifySpawnRate(spawnRateMultiplier, spawningGround);
    }
    public void modifyEnemyDamagaMultiplier(ForestGameMap gamemap, EnemyActor actor, float enemyDamageMultiplier){
        
    }
}
