package game.weather;

import java.util.ArrayList;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.extended.ForestGameMap;

public class AncientWoodWeatherController{
    protected int playturnCount = 0;
    protected int weatherIndex = 0;
    protected ArrayList<Weather> weatherList;
    protected ArrayList<GameMap> gameMapList;
    protected final int TURN_DIVIDER = 3;

    public AncientWoodWeatherController(){
        ArrayList<Weather> weathersToAdd = new ArrayList<>();
        weathersToAdd.add(new SunnyForestWeather());
        weathersToAdd.add(new RainyForestWeather());
        this.weatherList = weathersToAdd;
    }
    public void weatherTracker(){
        if(playturnCount !=0 && playturnCount % TURN_DIVIDER == 0){
            weatherIndex++;
        }
        if(weatherIndex > weatherList.size() - 1){
            weatherIndex = 0;
        }
        playturnCount++;
        System.out.println("Current Turn: "+playturnCount + "\nCurrent weather: " + weatherList.get(weatherIndex).getClass());
    }

    /***
     * 
     */
    public Weather currentWeather(){
        return this.weatherList.get(weatherIndex);
    };
    
    public void processWeather(ArrayList<ForestGameMap> gameMapList){
        weatherTracker();
        for(GameMap gameMap : gameMapList){
            currentWeather().weatherEffect(gameMap);
    }
    
}
}