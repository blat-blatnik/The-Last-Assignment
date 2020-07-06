package lastassignment.roomcontents;

import lastassignment.utils.Inspectable;
import lastassignment.Player;
import lastassignment.Room;
import lastassignment.items.BunnyRabbit;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.io.Serializable;

/**
 * @author Boris
 * @version 1.0
 *
 * This RabbitCage initially houses a BunnyRabbit that the player can rescue and take with them.
 */
public class RabbitCage implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private BunnyRabbit captive;

    /**
     * Constructs a RabbitCage with a rabbit that is captive within it.
     *
     * @param rabbit The rabbit trapped in this cage.
     * @param room The Room that the RabbitCage inhabits.
     * @see Room
     */
    public RabbitCage(BunnyRabbit rabbit, Room room) {
        captive = rabbit;
        room.addContents(this);
    }

    /**
     * Prints the description of the RabbitCage.
     *
     * @param player The player inspecting the RabbitCage.
     * @see Inspectable
     */
    @Override
    public void inspect(Player player) {
        if (captive == null)
            Console.printLine("An empty cage");
        else
            Console.printLine("A small cage labelled \"%s\"", captive.getName());
    }

    /**
     * If the cage isn't empty the player can choose to take the bunny held in the cage.
     *
     * @param player The player interacting with the RabbitCage.
     * @see Player
     */
    @Override
    public void interact(Player player) {
        Console.printWithPause("You walk up to the cage");
        if (captive == null)
            Console.printWithPause("It's completely empty. Just a half empty bowl of water and an empty bowl of food");
        else {

            Console.printWithPause("The cage is small but sturdy");
            Console.printWithPause("Upon further inspection you notice a rabbit tucked away in the far back corner of the cage");
            Console.printWithPause("The rabbit seems healthy and relatively happy");

            Console.printLine("What do you want to do?");
            Console.printLine("  (1) Loot the rabbit");
            Console.printLine("  (2) Leave the rabbit be");
            int input = Console.readInt();

            if (input == 1) {
                Console.printWithPause("You just can't resist the temptation to play with the little bunny rabbit");
                Console.printWithPause("You pry the doors of the cage open");
                Console.printWithPause("The rabbit silently stares as the cage door opens");
                Console.printWithPause("You expect him to come bursting out now that the door is open");
                Console.printWithPause("But the rabbit just sits there as if nothing happened");
                Console.printWithPause("You take hold of the rabbit and gently take it out of the cage");
                Console.printWithPause("He's a very sweet little guy");
                Console.printWithPause("\"This is no place for a cute little bunny like you\"");
                Console.printWithPause("\"I'm taking you with me\"");
                Console.printWithPause("The rabbit blinks in response");
                Console.printWithPause("You take the fluffy rabbit and stuff him into your backpack for now");
                Console.printWithPause("You leave the backpack partially opened so the rabbit can breathe");
                Console.printWithPause("You've gained a new fluffy companion");
                player.addItemToBackpack(captive);
                captive = null;
            } else {
                Console.printWithPause("You leave the rabbit in the cage");
                Console.printWithPause("For all you know it has rabies");
                Console.printWithPause("You'd best not touch it");
            }

        }
    }

}