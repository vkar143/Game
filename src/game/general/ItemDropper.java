package game.general;

import edu.monash.fit2099.engine.positions.Location;
import game.items.RefreshingFlask;

import java.util.Random;

/**
 * idea for an item dropper static class probably not as good an idea because i think they don't like static classes
 * even though it would definitely make the code more efficient
 */
public class ItemDropper {
    private static Random random = new Random();
    public static String dropRefreshingFlask(Location location, int bound, int odds){
        if(random.nextInt(bound) < odds){
            location.addItem(new RefreshingFlask());
            return " dropped a refreshing flask \n";
        }
        return null;
    }
}
