package lastassignment.roomcontents;

import lastassignment.Room;
import lastassignment.utils.Inspectable;
import lastassignment.Player;
import lastassignment.items.Item;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jana
 * @author Boris
 * @version 2.0
 *
 * A Container represents contents of a Room that can hold other
 * Interactable items inside of them - for example a locker or office drawer.
 * The Player can pick up items from the Container.
 *
 * @see Item
 * @see Room
 */
public class Container implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private final String description;
    private final List<Item> contents;

    /**
     * Constructs a Container from a given description.
     * By default the Container is empty.
     *
     * @param description The description of the container.
     */
    public Container(String description) {
        this.description = description;
        contents = new ArrayList<>();
    }

    /**
     * Prints the description of the Container.
     *
     * @param player The Player inspecting the Container.
     * @see Player
     * @see Inspectable
     */
    @Override
    public void inspect(Player player) {
        Console.printLine(description);
    }

    /**
     * Launches a dialog with the Player where they get to choose which
     * items to pick up from the Container. If the container is empty the
     * dialogue is skipped to save the Players time.
     *
     * @param player The Player interacting with the Container.
     * @see Player
     * @see Inspectable
     */
    @Override
    public void interact(Player player) {
        Console.printWithPause("You open the %s", description);
        if (contents.size() == 0) {
            Console.printWithPause("Sadly, it's empty");
        } else {
            Console.printLine("Within it you see:");
            for (int i = 0; i < contents.size(); i++) {
                Item item = contents.get(i);
                Console.print("  (%d) ", (i + 1));
                item.inspect(player);
            }

            Console.printLine("What do you plunder? (0 - nothing, A - everything!)");
            String input = Console.readInput();
            if (input.equals("A")) {
                for (Item item : contents) {
                    item.interact(player);
                }
                contents.clear();
            } else {
                int selection;
                try {
                    selection = Integer.parseInt(input) - 1;
                } catch (NumberFormatException e) {
                    selection = -1;
                }

                if (selection >= 0 && selection < contents.size()) {
                    Item item = contents.get(selection);
                    item.interact(player);
                    contents.remove(item);
                }
            }
        }
    }

    /**
     * Adds an item to the Container. Items are removed when the
     * player interacts with the Container.
     *
     * @param item Item to add to the Container.
     * @see Item
     */
    public void addContents(Item item) {
        contents.add(item);
    }
}