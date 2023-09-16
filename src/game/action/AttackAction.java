package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.items.HealingVial;
import game.items.OldKey;
import game.items.RefreshingFlask;

import java.util.Random;
/**
 * A class that represents the action taken by the actor to attack an enemy
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Vasi Karabourniotis
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Inflicts damage on the enemy which can lead to death
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A String summarising the effect of the attack by the actor to the enemy
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);

        handleUnconsciousTarget(actor, map);

        return result;
    }

    /**
     * Displays a description of AttackAction to the user through the actor's menu
     * @param actor The actor performing the action.
     * @return A String summarising the action of AttackAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }

    /**
     * Helper method for the execute method, displays what the actor can drop what item
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    private void handleUnconsciousTarget(Actor actor, GameMap map) {
        if (!target.isConscious()) {

            if (target.getDisplayChar() == 't') {
                canItemDrops(target, actor, map, 0.20, HealingVial.class);
                canItemDrops(target, actor, map,0.25, OldKey.class);
            } else if (target.getDisplayChar() == '&') {
                canItemDrops(target, actor, map,0.20, HealingVial.class);
                canItemDrops(target, actor, map,0.30, RefreshingFlask.class);
            }
        }
    }

    /**
     * Helper method to calculate and display a single item that an actor can drop
     * @param target the Actor to attack
     * @param actor The Actor to attack the target
     * @param map The map the actor is on
     * @param dropChance The chance an item has to be dropped
     * @param itemClass The class the item is in
     */
    private void canItemDrops(Actor target, Actor actor, GameMap map, double dropChance, Class<? extends Item> itemClass) {
        if (rand.nextDouble() <= dropChance) {
            for (Item item : target.getItemInventory()) {
                if (item.getClass() == itemClass) {
                    item.getDropAction(target).execute(actor, map);
                }
            }
        }
    }
}
