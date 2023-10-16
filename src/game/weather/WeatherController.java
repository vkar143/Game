package game.weather;

import java.util.ArrayList;

public abstract class WeatherController {
    protected int playturnCount = 0;
    protected int weatherIndex = 0;
    protected ArrayList<Weather> weatherList;
    protected int turnDivider = 3;


    public void initWeatherList(ArrayList<Weather> weatherList) {
        this.weatherList = weatherList;
    }
    /***
     * return current weather, increase weather index, and switch weather after certain playturns.
     */
    public Weather trackWeather(){

    };
    public void setWeather(Enum weather);
    public void turnCounter(){

    }
    /***
     * 
     */
    public void processWeather(){
        weatherList.get(weatherIndex)
        playturnCount++;
        if(playturnCount % 3 == 0){
            weatherIndex++;
        }
        if(weatherIndex >= weatherList.size()){
            weatherIndex = 0;
        }
    }
}
