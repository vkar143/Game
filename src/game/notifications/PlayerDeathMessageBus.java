package game.notifications;

import java.util.ArrayList;

public class PlayerDeathMessageBus implements DeathPublisher{
private static ArrayList<DeathSubcriber> deathSubcribers = new ArrayList<DeathSubcriber>();
@Override
public void publishDeath(){
    for(DeathSubcriber _deathSubcribers : deathSubcribers){
        _deathSubcribers.notifyDeath();
    }
}
public static void addSubscriber(DeathSubcriber subscriber){
    deathSubcribers.add(subscriber);
}
}
