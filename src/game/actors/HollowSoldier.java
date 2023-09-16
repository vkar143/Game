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
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.RefreshingFlask;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents A Hollow Soldier
 * Created by:
 * @author Vasi Karabourniotis
 */
public class HollowSoldier extends Actor {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private HealingVial healingVial = new HealingVial();
    private RefreshingFlask refreshingFlask = new RefreshingFlask();

    /**
     * Constructor used for making an instance of HollowSoldier showing its name, its character and the number of hit points it has
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        this.behaviours.put(0, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
        this.addItemToInventory(healingVial);
        this.addItemToInventory(refreshingFlask);
    }

    /**
     * Selects an action to be performed by the HollowSoldier for the current turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An action chosen by the user for Hollow Soldier to execute
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
     * Creates an intrinsic weapon for the Hollow Soldier to use in this case their limbs
     * @return Returns an instantiated intrinsic weapon's damage and its verb
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        int intrinsicWeaponDamage = 50;
        String intrinsicWeaponVerb = "strikes";
        float damageMultiplier = 0.50f;

        int damage = Math.round(intrinsicWeaponDamage * damageMultiplier);
        return new IntrinsicWeapon(damage, intrinsicWeaponVerb);
    }
}
