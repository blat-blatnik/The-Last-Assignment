package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

public class DamagingItem extends Item {

    private final String ingestionMethod;
    private final int damageAmount;

    public DamagingItem(String description, String ingestionMethod, int damageAmount) {
        super(description);
        this.ingestionMethod = ingestionMethod;
        this.damageAmount = damageAmount;
    }

    @Override
    public void interact(Player player) {
        Console.printWithPause("You %s the %s", ingestionMethod, getDescription());

        Console.printWithPause("Oh god.. IT BURNS!");
        Console.printWithPause("Your whole body starts to melt from the inside out");
        player.getDamagedFor(damageAmount);
        if (player.isAlive()) {
            Console.printWithPause("After a few long and agonizing moments, the pain begins to subside");
            Console.printWithPause("That was a close call");
            Console.printWithPause("You should really be more careful with what you %s!", ingestionMethod);
        }

        if (player.hasItem(this)) {
            player.removeItemFromBackpack(this);
        }
    }

}