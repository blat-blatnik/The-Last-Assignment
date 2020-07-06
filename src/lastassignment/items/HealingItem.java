package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

/**
 * @author Boris
 * @version 1.0
 *
 * Healing item can give the player some health back (healing power), when it is interacted with.
 * Unlike Medicine it's never actually given to the Player, the Player simply uses it straight away.
 *
 * @see Item
 */
public class HealingItem extends Item {

    private final String ingestionMethod;
    private final int healingPower;

    /**
     * Initializes healing item.
     * @param description description of the item
     * @param ingestionMethod the method it will be taken (e.g. eat, drink)
     * @param healingPower the healing power the item has on the player
     */
    public HealingItem(String description, String ingestionMethod, int healingPower) {
        super(description);
        this.ingestionMethod = ingestionMethod;
        this.healingPower = healingPower;
    }

    /**
     * Lets the player interact with the Healing Item, i.e. consume it.
     * @param player player that interacts
     * @see Player
     */
    @Override
    public void interact(Player player) {
        Console.printWithPause("You %s the %s", ingestionMethod, getDescription());

        if (healingPower > 0) {
            if (player.getHealth() < player.getMaxHealth()) {
                int oldHealth = player.getHealth();
                player.heal(healingPower);
                int newHealth = player.getHealth();
                Console.printWithPause("Mmmm.. it's delicious");
                Console.printWithPause("It brings back some of your energy! (+%d health points)", newHealth - oldHealth);
            } else {
                Console.printWithPause("It tastes fine, and you sort of regret %sing it now", ingestionMethod);
                Console.printWithPause("Oh well, what's done is done");
            }
        } else {
            Console.printWithPause("It tastes plain, as to be expected");
            Console.printWithPause("It also didn't seem to give you any superpowers");
        }

        if (player.hasItem(this)) {
            player.removeItemFromBackpack(this);
        }
    }

}