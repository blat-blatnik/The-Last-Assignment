package lastassignment.npcs;

import lastassignment.Player;
import lastassignment.Room;
import lastassignment.items.Item;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Boris
 * @version 1.0
 *
 * Shopkeeper is a Friendly NPC that can sell items to the player.
 *
 * @see FriendlyNPC
 * @see Interactable
 */
public class Shopkeeper extends FriendlyNPC {

    private final Map<Item, Double> inventory;

    /**
     * Constructs a Shopkeeper with description, health, damage, infection status and a room.
     * @param description their description
     * @param health their health
     * @param damage their damage
     * @param infected infection status
     * @param room room the shopkeeper will be in
     */
    public Shopkeeper(String description, int health, int damage, boolean infected, Room room) {
        super(description, health, damage, infected, room);
        inventory = new HashMap<>();
    }

    /**
     * Lets the player interact with Shopkeeper, i.e. potentially buy some items.
     * @param player player that interacts with Shopkeeper
     * @see Player
     */
    @Override
    public void interact(Player player) {
        super.interact(player);
        switch (inventory.size()) {
            case 0:
                Console.printWithPause("\"Sorry but I'm all out of stock\"");
                break;
            case 1:
                singleItemDialog(player);
                break;
            default:
                multipleItemsDialog(player);
                break;
        }
    }

    /**
     * Simple dialogue for buying one item.
     * @param player player that might buy an item
     * @see Player
     */
    private void singleItemDialog(Player player) {
        assert inventory.size() == 1;
        Item item = getItems()[0];
        double price = getPrices()[0];
        Console.printWithPause("\"Would you like to buy my " + item.getDescription() + "? It's only $%.2f\"", price);
        Console.printWithPause("You have $%.2f in your wallet", player.getMoney());
        boolean playerReplied = false;
        while (!playerReplied) {
            Console.printLine("\"Whadaya say?\"");
            Console.printLine("  (1) \"Sure, here you go\" (-$%.2f)", price);
            Console.printLine("  (2) Politely refuse");
            String input = Console.readInput();
            switch (input) {
                case "1":
                    if (player.getMoney() >= price) {
                        player.purchase(item, price);
                        removeItemFromInventory(item);
                        Console.printWithPause("\"Here ya go sweetie!\" %s while handing you the %s",
                                getDescription(), item.getDescription());
                        Console.printWithPause("You stuff %s in your pockets", item.getDescription());
                        Console.printWithPause("You now have $%.2f in your wallet", player.getMoney());
                        Console.printWithPause("\"Pleasure doing business with ya!\" %s says", getDescription());
                    } else {
                        Console.printWithPause("");
                        Console.printWithPause("\"Wait a minute..\" %s says", getDescription());
                        Console.printWithPause("\"This is only $%.2f! My %s costs $%.2f!\"", player.getMoney(),
                                item.getDescription(), price);
                        Console.printWithPause("\"NO DEAL!\" they scream at you");
                    }
                    playerReplied = true;
                    break;
                case "2":
                    Console.printWithPause("\"Pffft.. Your loss sweetie\"");
                    playerReplied = true;
                    break;
            }
        }
    }

    /**
     * Dialog for potentially buying multiple items from the Shopkeeper.
     * @param player player that might buy items
     * @see Player
     */
    private void multipleItemsDialog(Player player) {
        assert inventory.size() > 1;
        Console.printWithPause("\"Are you looking to buy something?\" %s asks", getDescription());
        boolean playerReplied = false;
        while (!playerReplied) {
            Console.printLine("\"What do you say?\"");
            Console.printLine("  (1) \"I might be\"");
            Console.printLine("  (2) \"No, thanks\"");
            String input = Console.readInput();
            switch (input) {
                case "1":
                    Console.printWithPause("\"Take a look at what I've got then\"");
                    Console.printWithPause("The shopkeeper takes out all of their stock");
                    Console.printWithPause("You have $%.2f in your wallet", player.getMoney());
                    boolean playerBoughtSomething = false;
                    while (!playerReplied) {
                        Item[] items = getItems();
                        Double[] prices = getPrices();
                        Console.printLine("\"What'll ya have?\" (0 - nothing)");
                        for (int i = 0; i < items.length; ++i) {
                            Console.printLine("  (%d) %s : $%.2f", i + 1, items[i].getDescription(), prices[i]);
                        }
                        int response = Console.readInt();
                        if (response == 0) {
                            if (playerBoughtSomething) {
                                Console.printWithPause("\"Okidoki.. thanks for the business\"");
                                Console.printWithPause("\"Come again!\" %s says while putting back the stock",
                                        getDescription());
                                Console.printWithPause("You walk away");
                            } else {
                                Console.printWithPause("\"Well ya could've told me earlier\"");
                                Console.printWithPause("\"Why're ya pulling my leg here?\" %s says while putting back " +
                                        "the stock", getDescription());
                                Console.printWithPause("\"Beat it then!\"");
                                Console.printWithPause("You leave in silence, with your wallet unscathed");
                            }
                            playerReplied = true;
                        } else if (response > 0 && response <= items.length) {
                            int i = response - 1;
                            Console.printWithPause("\"Ahh yes.. %s is one of my favorites\"", items[i].getDescription());
                            Console.printWithPause("\"That'll be $%.2f\"", prices[i]);

                            while (!playerReplied) {
                                Console.printLine("Pay?");
                                Console.printLine("  (1) \"Here you go\" (-$%.2f)", prices[i]);
                                Console.printLine("  (2) \"Actually, I changed my mind\"");
                                Console.printLine("  (3) \"No! These prices are ludicrous!\"");
                                input = Console.readInput();
                                switch (input) {
                                    case "1":
                                        if (player.getMoney() >= prices[i]) {
                                            player.purchase(items[i], prices[i]);
                                            removeItemFromInventory(items[i]);
                                            Console.printWithPause("\"Here ya go sweetie!\" %s says while handing you " +
                                                    "the %s", getDescription(), items[i].getDescription());
                                            Console.printWithPause("You stuff the %s in your pockets",
                                                    items[i].getDescription());
                                            Console.printWithPause("You now have $%.2f in your wallet",
                                                    player.getMoney());
                                            playerReplied = true;
                                            playerBoughtSomething = true;
                                        } else {
                                            Console.printWithPause("");
                                            Console.printWithPause("\"Wait a minute..\" %s says", getDescription());
                                            Console.printWithPause("\"You only gave me $%.2f! My %s costs $%.2f!\"",
                                                    player.getMoney(), items[i].getDescription(), prices[i]);
                                            Console.printWithPause("\"NO DEAL! You little cheapskate\" they scream at you");
                                            playerReplied = true;
                                        }
                                        break;
                                    case "2":
                                        Console.printWithPause("\"Oh, ok then\" %s sighs", getDescription());
                                        playerReplied = true;
                                        break;
                                    case "3":
                                        Console.printWithPause("\"Well, those are MY prices.. DEAL WITH IT!\" %s " +
                                                "screams at you as you leave", getDescription());
                                        Console.printWithPause("Those prices are clearly overinflated");
                                        Console.printWithPause("You narrowly avoided a terrible deal");
                                        playerReplied = true;
                                        break;
                                }

                                if (input.equals("1") || input.equals("2")) {
                                    Console.printWithPause("\"Do you wanna buy something else?\"");
                                    playerReplied = false;
                                    while (!playerReplied) {
                                        Console.printLine("\"Hmmm?\"");
                                        Console.printLine("  (1) \"I do\"");
                                        Console.printLine("  (2) \"Not right now\"");
                                        input = Console.readInput();
                                        switch (input) {
                                            case "1":
                                                Console.printWithPause("\"Then take another look at the merchandise\"");
                                                playerReplied = true;
                                                break;
                                            case "2":
                                                playerReplied = true;
                                                if (playerBoughtSomething) {
                                                    Console.printWithPause("\"Then I'll be seeing you again next " +
                                                            "time\" %s says while putting away the stock", getDescription());
                                                    Console.printWithPause("You leave");
                                                } else {
                                                    Console.printWithPause("\"Then BEAT IT you little " +
                                                            "cheapskate!\" %s screams", getDescription());
                                                    Console.printWithPause("You walk away in silence, but with your " +
                                                            "wallet unscathed");
                                                }
                                                break;
                                        }
                                    }

                                    if (input.equals("1")) {
                                        playerReplied = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    playerReplied = true;
                    break;
                case "2":
                    Console.printWithPause("\"Pffft.. Your loss sweetie\"");
                    Console.printWithPause("\"Come again some other time, yea?\" %s adds as you leave", getDescription());
                    playerReplied = true;
                    break;
            }
        }
    }

    /**
     * Adds item to inventory with a price.
     * @param item item
     * @param value price
     * @see Item
     */
    public void addItemToInventory(Item item, double value) {
        inventory.put(item, value);
    }

    /**
     * Removes item from inventory.
     * @param item item
     * @see Item
     */
    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    /**
     * Gets items as an array.
     * @return array of items
     */
    private Item[] getItems() {
        Item[] items = new Item[inventory.size()];
        inventory.keySet().toArray(items);
        return items;
    }

    /**
     * Gets prices as an array.
     * @return array of prices
     */
    private Double[] getPrices() {
        Double[] prices = new Double[inventory.size()];
        inventory.values().toArray(prices);
        return prices;
    }
}
