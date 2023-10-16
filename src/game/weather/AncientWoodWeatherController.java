package game.weather;

import java.util.ArrayList;

import edu.monash.fit2099.engine.positions.Location;

public class AncientWoodWeatherController extends WeatherController{
    protected ArrayList<Location> spawnLocationAffectedByWeather = new ArrayList<>();

    public AncientWoodWeatherController(){
        super();
        this.weatherList.add(new SunnyWeather());
    }
    
}
