package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ForestGameMap;
import game.behaviours.FollowBehavior;
import game.general.Status;
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
        ancientWoodWeatherController.processWeather();
        return super.playTurn(actions, lastAction,(ForestGameMap)map, display);
    }

    /**
     * Sets follow behaviour if player is within range of the enemy and then returns actionList
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList of allowableActions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            addBehavior(FOLLOW_BEHAVIOUR_PRIORITY,new FollowBehavior(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);
    }
}
