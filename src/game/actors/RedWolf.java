package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehavior;
import game.general.Status;
import game.items.HealingVial;
import game.items.Runes;
import java.util.Random;

public class RedWolf extends EnemyActor {
    private static final int HIT_POINTS = 25;
    private static final int COST = 25;
    private final int INTRINSIC_DAMAGE = 15;
    private final int INTRINSIC_HIT_RATE = 80;
    private final int FOLLOW_BEHAVIOUR_PRIORITY = 998;
    private final int BOUND = 10;
    private final int CHANCE = 1;
    private final int OFFSET = 1;
    public RedWolf() {
        super("Red Wolf", 'r',HIT_POINTS, COST);
    }
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(INTRINSIC_DAMAGE,"bites" , INTRINSIC_HIT_RATE);
    }
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int number = random.nextInt(BOUND);
        if(number < CHANCE){
            map.locationOf(this).addItem(new HealingVial());
            builder.append("\n").append(name).append(" dropped a healing Vial").append("\n");
        }
        map.locationOf(this).addItem(new Runes(this.getRuneAmount()));
        builder.insert(OFFSET,super.unconscious(actor, map));
        return builder.toString();
    }
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            addBehavior(FOLLOW_BEHAVIOUR_PRIORITY,new FollowBehavior(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);
    }
}
