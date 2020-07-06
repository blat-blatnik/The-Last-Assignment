package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.npcs.Enemy;
import lastassignment.Player;

/**
 * @author Jana
 * @version 1.0
 *
 * Weapon helps the player fight off enemies.
 *
 * @see Item
 * @see Enemy
 */
public class Weapon extends Item {

    private final int damage;

    /**
     * Initializes Weapon with description and damage it can do.
     * @param description description of weapon
     * @param damage damage it can do
     */
    public Weapon(String description, int damage) {
        super(description);
        this.damage = damage;
    }

    /**
     * Lets the player interact with a Weapon. The player gets a choice between:
     * - putting the Weapon in their backpack (inventory)
     * - keeping the Weapon in their hands for attacking.
     * @param player player that interacts with weapon
     * @see Player
     */
    @Override
    public void interact(Player player) {

        if (player.hasItem(this)) {
            Weapon currentWeapon = player.getCurrentWeapon();
            if (currentWeapon != null) {
                player.addItemToBackpack(currentWeapon);
                Console.printWithPause("You put away your %s, it barely fits into your backpack", currentWeapon.getDescription());
            }
            player.setCurrentWeapon(this);
            player.removeItemFromBackpack(this);
            Console.printWithPause("You take out your %s", getDescription());
            Console.printWithPause("Time to take the school by storm!");
        } else {
            player.addItemToBackpack(this);
            Console.printWithPause("After a lot of hassle, you finally manage to fit %s in your backpack", getDescription());
        }
    }

    /**
     * Gets the damage the weapon can do.
     * @return damage
     */
    public int getDamage() {
        return damage;
    }
}
