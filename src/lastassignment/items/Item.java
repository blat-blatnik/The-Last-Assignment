package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.utils.Interactable;
import lastassignment.Player;

import java.io.Serializable;

/**
 * @author Jana
 * @version 1.0
 *
 * Abstract class of items that can be found in rooms or in Containers.
 *
 * @see Interactable
 * @see Serializable
 */
public abstract class Item implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private String description;

    /**
     * Initializes an item with description.
     * @param description description of the item
     */
    public Item(String description) {
        this.description = description;
    }

    /**
     * Lets the player inspect the item
     * @param player player inspecting item
     * @see Player
     */
    @Override
    public void inspect(Player player){
        Console.printLine(description);
    }

    /**
     * Lets the player interact with an item, by default putting it into the players inventory.
     * @param player player that interacts with the item
     * @see Player
     */
    @Override
    public void interact(Player player) {
        player.addItemToBackpack(this);
        Console.printWithPause("You quickly stuff the %s into your pocket", description);
    }

    /**
     * Gets the description of the item
     * @return description
     */
    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
