package game.notification;
/**
 * interface that receives death message from publisher and prompt operation for different implementations.
 */
public interface DeathSubcriber {
    void notifyDeath();
}
