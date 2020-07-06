package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

/**
 * @author Jana
 * @version 1.0
 *
 * Vaccine can make the player immune to the virus.
 *
 * @see Item
 */
public class Vaccine extends Item {

    /**
     * Initializes Vaccine with a description.
     * @param description The description of the vaccine.
     */
    public Vaccine(String description) {
        super(description);
    }

    /**
     * Lets the player interact with the Vaccine, i.e. use it.
     * @param player player that interacts with the item.
     * @see Player
     */
    @Override
    public void interact(Player player) {
        if (player.hasItem(this)) {
            Console.printWithPause("You are scared of needles, but you know it must be done");
            Console.printWithPause("You ram the needle into your arm and squeeze");
            if (player.isImmune()) {
                Console.printWithPause("");
                Console.printWithPause("You don't feel any different");
                Console.printWithPause("Maybe it takes a while to kick in");
            } else {
                player.becomeImmune();
            }
            player.removeItemFromBackpack(this);
        } else {
            super.interact(player);
        }
    }
}
