package lastassignment.roomcontents;

import lastassignment.utils.Inspectable;
import lastassignment.Player;
import lastassignment.items.HealingItem;
import lastassignment.items.Item;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Boris
 * @version 1.0
 *
 * The VendingMachine is a dispenser of HealingItems. The Player can purchase
 * HealingItems for a set cost, and they can be purchased over and over again.
 * They are intended as major "healing stations" for the Player, as long as
 * they have enough money.
 *
 * @see HealingItem
 * @see Player
 */
public class VendingMachine implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private final String description;
    private final Map<HealingItem, Double> inventory; //NOTE(Boris): item:price
    private final String itemDescription;

    /**
     * Constructs a VendingMachine from a description for the machine itself,
     * and a description for the items that the machine dispenses. For example
     * a "coffee machine" would dispense "coffee".
     *
     * @param description The description of the machine.
     * @param itemDescription The description of the item the machine dispenses.
     */
    public VendingMachine(String description, String itemDescription) {
        this.description = description;
        this.inventory = new HashMap<>();
        this.itemDescription = itemDescription;
    }

    /**
     * Adds a HealingItem to the inventory of the VendingMachine.
     * It can then be purchased by the Player for the given price.
     *
     * @param item The HealingItem to add.
     * @param price The price of the item.
     * @see HealingItem
     * @see Player
     */
    public void addItem(HealingItem item, double price) {
        inventory.put(item, price);
    }

    /**
     * Prints the description of the VendingMachine.
     *
     * @param player The Player inspecting the VendingMachine.
     * @see Inspectable
     */
    @Override
    public void inspect(Player player){
        Console.printLine(description);
    }

    /**
     * The Player enter a dialogue where he can choose to purchase a HealingItem
     * from the VendingMachine for the appropriate price.
     *
     * @param player The player interacting with the VendingMachine.
     * @see HealingItem
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        Console.printWithPause("You walk up to %s", description);
        boolean playerResponded = false;
        while (!playerResponded) {
            Console.printLine("Would you like to get some %s?", itemDescription);
            Console.printLine("  (1) Yes!");
            Console.printLine("  (2) Not right now");
            String input = Console.readInput();
            switch (input) {
                case "1":
                    playerResponded = true;
                    Console.printWithPause("You have $%.2f in your wallet", player.getMoney());
                    Console.printWithPause("There's a menu printed on the side of the machine:");

                    if (inventory.size() == 0) {
                        Console.printWithPause("But.. there's nothing on it!");
                        Console.printWithPause("I guess you cant actually get anything from %s", description);
                        Console.printWithPause("You walk away confused about the purpose of this machine");
                        return;
                    }

                    HealingItem[] items = new HealingItem[inventory.size()];
                    Double[] prices = new Double[inventory.size()];
                    inventory.keySet().toArray(items);
                    inventory.values().toArray(prices);
                    boolean playerMadeSelection = false;
                    while (!playerMadeSelection) {
                        Console.printLine("What do you get? (0 - Nothing)");
                        for (int i = 0; i < items.length; ++i) {
                            Console.printLine("  (%d) %s : $%.2f", i + 1, items[i].getDescription(), prices[i]);
                        }

                        int selection = Console.readInt();
                        if (selection == 0) {
                            Console.printWithPause("On the other hand, you could also not get %s", itemDescription);
                            Console.printWithPause("You walk away from the machine, happy with your decision");
                            playerMadeSelection = true;
                        } else if (selection >= 1 && selection <= items.length) {
                            Item item = items[selection - 1];
                            double price = prices[selection - 1];
                            playerMadeSelection = true;
                            if (player.getMoney() >= price) {
                                player.setMoney(player.getMoney() - price);
                                Console.printWithPause("You slot the $%.2f into %s and press button %d", price, description, selection);
                                Console.printWithPause("You have $%.2f left in your wallet", player.getMoney());
                                Console.printWithPause("The machine buzzers and clanks for a few moments");
                                Console.printWithPause("It then dispenses a nice fresh %s", item.getDescription());
                                Console.printWithPause("You consume it in a mere flash!");
                                item.interact(player);
                                Console.printWithPause("You step away from %s, satisfied", description);
                            } else {
                                Console.printWithPause("Oh, it looks like you don't actually have $%.2f", price);
                                Console.printWithPause("Well that's too bad.. no %s for you this time", itemDescription);
                            }
                        }
                    }

                    break;
                case "2":
                    playerResponded = true;
                    Console.printWithPause("You don't need any %s right now", itemDescription);
                    Console.printWithPause("You walk away, leaving %s in the darkness behind you", description);
                    break;
            }
        }
    }

}