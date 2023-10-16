package game.weather;

import java.util.ArrayList;
/***
 * this class is supposed to keep track of weather and provide main API for Actor to manipulate weather.
 */
public abstract class WeatherController {
    protected int playturnCount = 0;
    protected int weatherIndex = 0;
    protected ArrayList<ForestWeather> weatherList;
    protected int turnDivider = 3;


    public void initWeatherList(ArrayList<ForestWeather> weatherList) {
        this.weatherList = weatherList;
    }

    /***
     * 
     */
    public abstract void processWeather();
}
