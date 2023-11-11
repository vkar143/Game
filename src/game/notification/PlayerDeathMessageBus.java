package game.notification;

import java.util.ArrayList;
/**
 * manages subscribers and deliver player death message
 */
public class PlayerDeathMessageBus implements DeathPublisher{
    /**
     * subscriber list
     */
private static ArrayList<DeathSubcriber> deathSubcribers = new ArrayList<DeathSubcriber>();
/**
 * publish player death to subscribers
 */
@Override
public void publishDeath(){
    for(DeathSubcriber _deathSubcribers : deathSubcribers){
        _deathSubcribers.notifyDeath();
    }
}
/**
 * register subscriber to the list it manages
 * @param subscriber objects that will be affected by player death
 */
public static void addSubscriber(DeathSubcriber subscriber){
    deathSubcribers.add(subscriber);
}
}
