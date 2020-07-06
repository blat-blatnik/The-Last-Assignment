package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

/**
 * @author Boris
 * @author Jana
 * @version 1.1
 *
 * Clothes that a player can wear, subclass of Item. A player can either wear the clothes directly, or
 * put them in the inventory.
 *
 * @see Item
 */
public class Clothes extends Item {

    private final boolean offerVirusProtection;

    /**
     * Initializes the Clothes.
     *
     * @param description Description of the clothes.
     * @param offerVirusProtection Whether the clothes stop the player from getting infected while wearing them.
     */
    public Clothes(String description, boolean offerVirusProtection) {
        super(description);
        this.offerVirusProtection = offerVirusProtection;
    }

    /**
     * Lets the player interact with a ClothesItem:
     * the player puts the outfit in their backpack (inventory).
     * @param player current player
     * @see Player
     */
    @Override
    public void interact(Player player) {

        if (player.hasItem(this)) {

            Console.printWithPause("You change into %s", getDescription());
            Console.printWithPause("Mmm looking good");
            Console.printWithPause("You put your old outfit into your backpack");
            Clothes currentOutfit = player.getCurrentOutfit();
            if (currentOutfit != null)
                player.addItemToBackpack(currentOutfit);

            player.setCurrentOutfit(this);
            player.removeItemFromBackpack(this);
            Console.printLine("It's time to go back to what you were doing before", getDescription());

        } else {
            player.addItemToBackpack(this);
            Console.printWithPause("You put %s in your backpack", getDescription());
        }
    }

    public boolean offersVirusProtection() {
        return this.offerVirusProtection;
    }
}
