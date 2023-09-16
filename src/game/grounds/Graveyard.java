package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.WanderingUndead;
/**
 * A class that represents a graveyard that can spawn enemies.
 * Created by:
 * @author Vasi Karabourniotis
 */
public class Graveyard extends Ground {
    public Graveyard() {
        super('n');
    }

    /**
     * Every turn there is a 25% chance that the WanderingUndead will spawn if there is no other actor in the area and
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        if (Math.random() <= 0.25 && !location.containsAnActor()) {
            location.addActor(new WanderingUndead());
        }
    }

}
