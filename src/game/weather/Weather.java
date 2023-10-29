package game.weather;

import edu.monash.fit2099.engine.positions.GameMap;

/**
* interface for Execute effects related to this weather
 * Created by:
 * @author Zhuojun Zhao
*/
public interface Weather {
    /**
     * Implements unique weather effects
     * @param gameMap the specific map used
     */
    public void weatherEffect(GameMap gameMap);
}
