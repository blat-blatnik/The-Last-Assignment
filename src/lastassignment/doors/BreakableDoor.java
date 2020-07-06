package lastassignment.doors;

import lastassignment.Player;
import lastassignment.utils.Attackable;
import lastassignment.utils.Console;
import lastassignment.Room;

/**
 * @author Jana
 * @version 1.0
 *
 * A BreakableDoor needs to be broken down before the Player can walk through it.
 * The player will take damage in the process. This is useful for things like a large
 * glass window, or a rotten wooden door that the player needs to break to pass through.
 *
 * @see Door
 * @see Player
 * @see Attackable
 */
public class BreakableDoor extends Door {

    private final int damage;
    private boolean crackedOpen;

    /**
     * Constructs the BreakableDoor with 2 Rooms that the door belongs to, 2 descriptions
     * one for each Room respectively, as well as the amount of damage the Player will take
     * when breaking down the door.
     *
     * @param descriptionFromRoom1 The description of this door as seen from the first room.
     * @param descriptionFromRoom2 The description of this door as seen from the second room.
     * @param room1 The first Room that this door connects to.
     * @param room2 The second Room that this door connects to.
     * @param damage The amount of damage the player will take when breaking down this door.
     * @see Room
     * @see Player
     * @see Attackable
     */
    public BreakableDoor(String descriptionFromRoom1, String descriptionFromRoom2, Room room1, Room room2, int damage) {
        super(descriptionFromRoom1, descriptionFromRoom2, room1, room2);
        this.damage = damage;
        crackedOpen = false;
    }

    /**
     * Constructs the BreakableDoor with 2 Rooms that it connects to, the amount of damage the Player
     * will take when breaking through the door, as well as a single description as seen from both
     * sides of the door.
     *
     * @param description The description of this door from both sides.
     * @param room1 The first Room that this door connects to.
     * @param room2 The second Room that this door connects to.
     * @param damage The amount of damage that the Player receives when breaking down this door.
     * @see Room
     * @see Player
     * @see Attackable
     */
    public BreakableDoor(String description, Room room1, Room room2, int damage) {
        this(description, description, room1, room2, damage);
    }

    /**
     * If the door hasn't been crackedOpen yet, the Player will be prompted to break it
     * down. If he does so he gets damaged appropriately. Once this BreakableDoor has been
     * crackedOpen, it simply acts as a normal Door.
     *
     * @param player The player.
     * @see Player
     * @see Door
     * @see Attackable
     */
    public void interact(Player player) {
        if (!crackedOpen) {
            Console.printWithPause("You notice the door seems stuck, as you try to open it");
            Console.printWithPause("You try to open it with all of your power!");
            Console.printWithPause("But it doesn't budge");
            Console.printWithPause("");

            Console.printLine("What do you do?");
            Console.printLine("  (1) Turn around and leave!");
            Console.printLine("  (2) Crack the door open!");

            String input = Console.readInput();
            switch (input) {
                case "1":
                    Console.printWithPause("You turn around frustrated.");
                    break;
                case "2":
                    Console.printWithPause("You kick against the door and it cracks open!");
                    player.getAttackedFor(damage);
                    crackedOpen = true;
                    Console.printWithPause("Ouch, cracking the door open damaged you for " + damage + "hit points");
                    Console.printWithPause("You now have " + player.getHealth() + " hit points");
                    break;
            }
        }
        if (crackedOpen) {
            super.interact(player);
            Console.printWithPause("The door stays open behind you, because you broke it.");
        }
    }

}
