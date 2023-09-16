package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing spawn enemy class
 * Created by:
 * @author Vasi Karabourniotis
 */
public class SpawnEnemy extends Location {

    private boolean enemySpawned = false;
    private final double spawnPercentage;
    private final Actor enemyToSpawn;

    /**
     * Constructor
     * @param map Map to spawn enemy
     * @param x exact x value to spawn enemy
     * @param y exact y value to spawn enemy
     * @param spawnPercentage what chance does the enemy have to spawn
     * @param enemyToSpawn the enemy to spawn
     */
    public SpawnEnemy(GameMap map, int x, int y, double spawnPercentage, Actor enemyToSpawn) {
        super(map, x, y);
        this.spawnPercentage = spawnPercentage;
        this.enemyToSpawn = enemyToSpawn;
    }

    /**
     * Every turn it will spawn new enemy based on this calculation
     */
    public void tick() {
        if (!enemySpawned && shouldSpawnEnemy(spawnPercentage)) {
            this.map().addActor(enemyToSpawn, this);
            enemySpawned = true;
        }

        super.tick();
    }

    /**
     * A helped function to calculate whether the spawning of the enemy will happen
     * @param spawnPercentage what chance does the enemy have to spawn
     * @return Will return true or false depending on if the calculation worked
     */
    private boolean shouldSpawnEnemy(double spawnPercentage) {
        return !containsAnActor() && Math.random() <= spawnPercentage;
    }
}
