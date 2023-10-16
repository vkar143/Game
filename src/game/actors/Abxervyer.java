package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weather.AncientWoodWeatherController;
import game.weather.WeatherController;

public class Abxervyer extends EnemyActor {
    /***
     * Weather controller that allows the enemy to controll weather of the gamemap
     */
    AncientWoodWeatherController ancientWoodWeatherController = new AncientWoodWeatherController();
    /**
     * Constant for the Hit points
     */
    private static final int HIT_POINTS = 125;
    /**
     * Constant for the number of runes dropped
     */
    private static final int COST = 50;
    /**
     * Constant for the amount of damage dealt
     */
    private final int INTRINSIC_DAMAGE = 25;
    /**
     * Constant for the likelihood of attack landing
     */
    private final int INTRINSIC_HIT_RATE = 75;
    /**
     * Constant for the position in the hash Table for follow behaviour
     */
    private final int FOLLOW_BEHAVIOUR_PRIORITY = 998;
    public Abxervyer(){
        super("Abxervyer", 'Y', HIT_POINTS, COST);
    }
    @Override 
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){

    }
}
