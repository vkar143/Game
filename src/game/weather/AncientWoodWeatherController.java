package game.weather;

import java.util.ArrayList;

import edu.monash.fit2099.engine.positions.Location;

public class AncientWoodWeatherController extends WeatherController{
    protected ArrayList<Location> spawnLocationAffectedByWeather = new ArrayList<>();

    public AncientWoodWeatherController(){
        super();
        this.weatherList.add(new SunnyWeather());
    }

    /***
     * return current weather, increase weather index, and switch weather after certain playturns.
     */
    public ForestWeather currentWeather(){
        
        if(playturnCount !=0 && playturnCount % 3 == 0){
            weatherIndex++;
        }
        if(weatherIndex > weatherList.size() - 1){
            weatherIndex = 0;
        }
        playturnCount++;
        return this.weatherList.get(weatherIndex);
    };

    public void processWeather(){
        
    }
    
}
