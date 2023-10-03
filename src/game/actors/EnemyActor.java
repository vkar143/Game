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
import game.general.Ability;
import game.general.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehavior;
import game.behaviours.WanderBehaviour;
import game.items.Runes;

import java.util.HashMap;
import java.util.Map;

/**
 * abstract class that enemy actors inherit from
 */
public abstract class EnemyActor extends Actor {
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final int runeAmount;

    /**
     * construct for the enemyActor abstract class.
     * @param name sets name
     * @param displayChar sets display char
     * @param hitPoints sets hitPoints
     * @param _runeAmount sets runeAmount
     */
    public EnemyActor(String name, char displayChar, int hitPoints, int _runeAmount) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(997, new AttackBehavior());
        this.capabilitySet.addCapability(Ability.WALK_ON_VOID);
        this.capabilitySet.addCapability(Status.ENEMY);
        this.runeAmount = _runeAmount;
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
    public int getRuneAmount() {
        return runeAmount;
    }
    public void addBehavior(int priority,Behaviour behaviour){
        this.behaviours.put(priority,behaviour);
    }
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction, otherActor.getIntrinsicWeapon()));
        }
        return actions;
    }
    @Override
    public String unconscious(Actor actor, GameMap map) {
        StringBuilder builder = new StringBuilder();
        map.locationOf(this).addItem(new Runes(runeAmount));
        builder.insert(0,super.unconscious(actor, map));
        return builder.toString();
    }

}
