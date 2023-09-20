package game.items;

import edu.monash.fit2099.engine.items.Item;

public class Wallet extends Item {
    private int count;

    /***
     * Constructor.

     */
    public Wallet() {
        super("Wallet", '$', false);
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
