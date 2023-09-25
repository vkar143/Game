package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.FollowBehavior;

public abstract class FollowingEnemies extends EnemyActor {
    protected Actor target;

    public FollowingEnemies(String name, char displayChar, int hitPoints, Actor target, int runeAmount) {
        super(name, displayChar, hitPoints, runeAmount);
        this.target = target;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location myLocate = map.locationOf(this);
        for(Exit exit: myLocate.getExits()){
            if(exit.getDestination().getActor() == target){
                addBehavior(new FollowBehavior(target),998);
            }
        }
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
