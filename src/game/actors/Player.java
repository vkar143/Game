package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.FancyMessage;
import game.Status;


/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Vasi Karabourniotis
 */
public class Player extends Actor {
    /**
     * Constructor.
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hit points
     * @param staminaPoints Play's starting number of stamina points
     */
    public Player(String name, char displayChar, int hitPoints, int staminaPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(staminaPoints));
        this.addCapability(Status.NOT_DEAD);
    }

    /**
     * Selects an action to be performed for the current turn and also displays the player's name, their hit points and stamina points
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action chosen by the user for the player to execute
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        int hitPoints = this.getAttribute(BaseActorAttributes.HEALTH);
        int totalHitPoints = this.getAttributeMaximum(BaseActorAttributes.HEALTH);

        int staminaPoints = this.getAttribute(BaseActorAttributes.STAMINA);
        int totalStaminaPoints = this.getAttributeMaximum(BaseActorAttributes.STAMINA);
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        // prints the name of the player, the hit points and the stamina
        display.println(name);
        display.println("HP: " + hitPoints + "/" + totalHitPoints);
        display.println("SP: " + staminaPoints + "/" + totalStaminaPoints);

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
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }


    /**
     * Creates an intrinsic weapon for the player to use in this case their limbs
     * @return Returns an instantiated intrinsic weapon's damage and its verb
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        int intrinsicWeaponDamage = 15;
        String intrinsicWeaponVerb = "strikes";
        float damageMultiplier = 0.80f;

        int damage = Math.round(intrinsicWeaponDamage * damageMultiplier);
        return new IntrinsicWeapon(damage, intrinsicWeaponVerb);
    }

}
