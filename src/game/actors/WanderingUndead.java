package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.FancyMessage;
import game.actions.AttackAction;
import game.Status;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.OldKey;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents the actor WanderingUndead
 * Created by:
 * @author Vasi Karabourniotis
 */
public class WanderingUndead extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private OldKey oldKey = new OldKey();
    private HealingVial healingVial = new HealingVial();

    public WanderingUndead() {
        super("Wandering Undead", 't', 100);
        this.behaviours.put(0, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
        this.addItemToInventory(oldKey);
        this.addItemToInventory(healingVial);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        if(!this.isConscious()){
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            System.exit(0);
        }

        return new DoNothingAction();
    }

    /**
     * The wandering undead can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * The wanderingUndead is able to use their body to attack otherActors
     * @return an intrinsic weapon to attack other actors
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        int intrinsicWeaponDamage = 30;
        String intrinsicWeaponVerb = "hits";
        float damageMultiplier = 0.50f;

        int damage = Math.round(intrinsicWeaponDamage * damageMultiplier);
        return new IntrinsicWeapon(damage, intrinsicWeaponVerb);
    }

}
