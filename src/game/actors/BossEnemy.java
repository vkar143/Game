package game.actors;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;


public abstract class BossEnemy extends EnemyActor{
    public BossEnemy(String name, char displayChar, int hitPoints, int runeAmount){
        super(name, displayChar, hitPoints, runeAmount);
    }
    @Override
    public void resetEnemy(){
        this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, getAttributeMaximum(BaseActorAttributes.HEALTH));
        Display display = new Display();
        display.println(name + "'s HP is resetted to " + getAttributeMaximum(BaseActorAttributes.HEALTH));
}
}
