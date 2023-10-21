package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Runes;

import java.util.Random;

public class EldentreeGuardian extends FollowingEnemy {
    /**
     * construct for the enemyActor abstract class.
     */
    public EldentreeGuardian() {
        super("Eldentree Guardian", 'e', 250, 250);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "attacks player", 80);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        if(random.nextInt(10) < 2.5){
            map.locationOf(this).addItem(new HealingVial());
            builder.append("\n").append(name).append(" dropped a healing Vial").append("\n");
        }
        if(random.nextInt(10) < 1.5){
            map.locationOf(this).addItem(new RefreshingFlask());
            builder.append("\n").append(name).append(" dropped a refreshing flask").append("\n");
        }
        map.locationOf(this).addItem(new Runes(this.getRuneAmount()));
        return builder.toString();
    }
}
