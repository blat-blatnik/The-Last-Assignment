package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

/**
 * @author Jana
 * @version 1.0
 *
 * VirusMedicine can heal the player from the virus.
 *
 * @see Item
 */
public class VirusMedicine extends Item {

    private final int healingPower;

    /**
     * Initializes VirusMedicine with description and healing power.
     * @param description description of the vaccine
     * @param healingPower the amount that it will heal the player with
     */
    public VirusMedicine(String description, int healingPower) {
        super(description);
        this.healingPower = healingPower;
    }

    /**
     * Lets the player interact with the virus medicine.
     * @param player player inspecting the medicine
     * @see Player
     */
    @Override
    public void inspect(Player player) {
        Console.printLine("%s that can heal you for %d hit points", getDescription(), healingPower);
    }

    /**
     * Lets the player interact with the medicine, i.e. put it in inventory or use it.
     * @param player player that interacts with the item
     * @see Player
     */
    @Override
    public void interact(Player player) {

        if (player.hasItem(this)) {
            if (player.isInfected()) {
                player.heal(healingPower);
                Console.printWithPause("You take the medicine and _instantly_ feel better");
                Console.printWithPause("It works wonders on you, and you are not infected anymore");
                player.becomeImmune();
                Console.printWithPause("You feel a lot better! (%d/%d hit points)", player.getHealth(), player.getMaxHealth());
            } else {
                Console.printWithPause("You take the %s", getDescription());
                Console.printWithPause("");
                Console.printWithPause("Meh");
                Console.printWithPause("It didn't really seem to do much");
            }
            player.removeItemFromBackpack(this);
        } else {
            super.interact(player);
        }
    }
}