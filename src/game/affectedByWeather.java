package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.positions.Location;
import game.ground.SpawningGround;

public interface affectedByWeather {
    public ArrayList<Location> getWeatherAffectedSpawningGrounds(Class<? extends SpawningGround> ground);
}
