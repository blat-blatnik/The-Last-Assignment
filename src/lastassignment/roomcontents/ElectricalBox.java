package lastassignment.roomcontents;

import lastassignment.Game;
import lastassignment.utils.Inspectable;
import lastassignment.Player;
import lastassignment.Room;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.io.Serializable;

/**
 * @author Boris
 * @version 1.0
 *
 * The ElectricalBox is a power source for Computers so that the Player can
 * use them to finish the assignment and win the Game. They can start either
 * working or broken, and don't provide any power if they are broken, the
 * player can fix an ElectricalBox by interacting with it, but only if it
 * was constructed as fixable.
 *
 * @see Computer
 * @see Game
 * @see Player
 */
public class ElectricalBox implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private boolean isWorking;
    private String description;
    private final String workingDescription;
    private final boolean fixable;

    /**
     * Constructs a working/non-working fixable/non-fixable ElectricalBox with two descriptions
     * and a Room which it inhabits. Just like other room contents, the ElectricalBox will add
     * itself to the Rooms list of contents automatically.
     *
     * @param isWorking Determines whether the ElectricalBox provides power or not by default.
     * @param fixable Determines whether the Player can fix the ElectricalBox by interacting with it.
     * @param brokenDescription The description of the ElectricalBox when it is *not* working.
     * @param workingDescription The description of the ElectricalBox when it *is* working.
     * @param room The Room that the ElectricalBox inhabits.
     * @see Room
     */
    public ElectricalBox(boolean isWorking, boolean fixable, String brokenDescription, String workingDescription, Room room) {
        this.description = isWorking ? workingDescription : brokenDescription;
        this.workingDescription = workingDescription;
        this.isWorking = isWorking;
        this.fixable = fixable;
        room.addContents(this);
    }

    /**
     * Constructs a working/non-working fixable/non-fixable ElectricalBox with one description
     * and a Room which it inhabits. Just like other room contents, the ElectricalBox will add
     * itself to the Rooms list of contents automatically.
     *
     * @param isWorking Determines whether the ElectricalBox provides power or not by default.
     * @param fixable Determines whether the Player can fix the ElectricalBox by interacting with it.
     * @param description The description of the ElectricalBox.
     * @param room The Room that the ElectricalBox inhabits.
     * @see Room
     */
    public ElectricalBox(boolean isWorking, boolean fixable, String description, Room room) {
        this(isWorking, fixable, description, description, room);
    }

    /**
     * Prints the description of the ElectricalBox.
     *
     * @param player The player inspecting the ElectricalBox.
     * @see Inspectable
     */
    @Override
    public void inspect(Player player) {
        Console.printLine(description);
    }

    /**
     * If the ElectricalBox is broken and fixable the player fixes the ElectricalBox
     * to make it give power to its respective Computer again. If the ElectricalBox
     * is not broken, or is broken but not fixable, then a short narrative is printed.
     *
     * @param player The player interacting with the ElectricalBox.
     * @see Player
     * @see Computer
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        Console.printWithPause("You walk up to the %s", description);
        if (isWorking()) {
            Console.printWithPause("The wires all seem to be in place, and the gauge is moving");
            Console.printWithPause("There's nothing more to do with this box");
        } else {
            Console.printWithPause("Oh it's all messed up");
            if (!fixable) {
                Console.printWithPause("The wires are all cut and some of the electronics is completely destroyed beyond repair");
                Console.printWithPause("There's nothing you can do for this poor box");
            } else {
                Console.printWithPause("Some wires are out of place");
                Console.printWithPause("But other than that, it seems like it could be fixed");
                Console.printWithPause("You've done this before already");
                Console.printWithPause("But there's always a risk in fixing cables like this");

                boolean playerResponded = false;
                while (!playerResponded) {
                    Console.printLine("Do you try to fix the cables?");
                    Console.printLine("  (1) What's the worst that could happen");
                    Console.printLine("  (2) No way!");
                    String input = Console.readInput();
                    switch (input) {
                        case "1":
                            Console.printWithPause("Ok then, you get to work");
                            Console.printWithPause("");
                            Console.printWithPause("After a bit of contemplation, you think you have a pretty good idea of which wires should be moved");
                            Console.printWithPause("You go for it");
                            Console.printWithPause("You ground the red wire");
                            Console.printWithPause("You connect the green wire to some rectangular thingamajig");
                            Console.printWithPause("You connect the blue wire to the blue pin");
                            Console.printWithPause("And, you're done!");
                            Console.printWithPause("The gauge springs back to life, signaling to you that your work had been fruitful");
                            Console.printWithPause("And best of all, you get to keep your life!");
                            Console.printWithPause("The step away from the now functional electrical box");
                            isWorking = true;
                            description = workingDescription;
                            playerResponded = true;
                            break;
                        case "2":
                            Console.printWithPause("Fair enough, you do value your life");
                            Console.printWithPause("Touching loose circuits is a surefire way to end it");
                            Console.printWithPause("You step away from the %s", description);
                            playerResponded = true;
                            break;
                    }
                }
            }
        }
    }

    /**
     * @return true iff the ElectricalBox is currently giving power.
     * @see Computer
     */
    public boolean isWorking() {
        return isWorking;
    }

}