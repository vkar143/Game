package game.weather;

import game.actors.EnemyActor;
import game.extended.ForestGameMap;
import game.ground.SpawningGround;

public abstract class ForestWeather{

    public void modifySpawnRate(ForestGameMap gamemap, SpawningGround spawningGround, float spawnRateMultiplier){
        gamemap.modifySpawnRate(spawnRateMultiplier, spawningGround);
    }

    public void modifyEnemyDamagaMultiplier(ForestGameMap gamemap, EnemyActor actor, float enemyDamageMultiplier){
        
    }
}
