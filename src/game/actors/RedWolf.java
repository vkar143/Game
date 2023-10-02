package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.Runes;

import java.util.Random;

public class RedWolf extends EnemyActor{
    public RedWolf() {
        super("Red Wolf", 'r',25, 25);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15,"bites" , 80);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int number = random.nextInt(10);
        if(number < 1){
            map.locationOf(this).addItem(new HealingVial());
            String tempString = "\n" + name + " dropped a healing Vial" + "\n";
            builder.append(tempString);
        }
        map.locationOf(this).addItem(new Runes(this.getRuneAmount()));
        builder.insert(0,super.unconscious(actor, map));
        return builder.toString();
    }
}
