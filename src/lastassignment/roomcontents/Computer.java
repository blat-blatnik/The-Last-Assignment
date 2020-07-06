package lastassignment.roomcontents;

import lastassignment.Game;
import lastassignment.doors.SchoolEntrance;
import lastassignment.utils.Inspectable;
import lastassignment.Player;
import lastassignment.Room;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.io.Serializable;

/**
 * @author Boris
 * @version 1.1
 *
 * A Computer that the Player can use to complete the assignment and
 * win the Game. The computer draws power from an ElectricalBox, if
 * the Computer's box is broken, the computer will have no power and
 * cannot be used to complete the assignment - the player has to first
 * Fix the ElectricalBox.
 *
 * @see Player
 * @see ElectricalBox
 * @see Game
 * @see SchoolEntrance
 */
public class Computer implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private final String description;
    private final ElectricalBox powerSource;

    /**
     * Constructs a Computer from a description, an ElectricalBox that
     * it is paired to, and a Room which it will inhabit.
     * Calling this constructor will automatically add the Computer to
     * the Rooms list of contents.
     *
     * @param description The description shown when inspecting the computer.
     * @param powerSource The ElectricalBox that the computer draws power from.
     * @param room The Room which the computer will inhabit.
     * @see ElectricalBox
     * @see Room
     */
    public Computer(String description, ElectricalBox powerSource, Room room) {
        this.description = description;
        this.powerSource = powerSource;
        room.addContents(this);
    }

    /**
     * Prints the Computer's description.
     *
     * @param player The player inspecting the computer.
     * @see Inspectable
     */
    @Override
    public void inspect(Player player) {
        Console.printLine(description);
    }

    /**
     * If the computers ElectricalBox is working, then the computer will boot up and the
     * player will complete the assignment if they haven't already done so. If they already
     * completed the assignment then a short interaction occurs.
     *
     * @param player The Player interacting with the computer.
     * @see Player
     * @see ElectricalBox
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        Console.printWithPause("You sit at the %s and press the power button", description);

        if (powerSource.isWorking()) {
            if (!player.hasCompletedAssignment()) {
                Console.printConversationWithPauses(
                        "The computer turns on!",
                        "You log on, and frantically start searching for a backup of your work",
                        "It's right there", "\"Phewww..\" You let out a large sigh", "The computer clock says it's 3am",
                        "Time to get to work", "You still need to actually finish your assignment",
                        "Your fingers start clicking against the cheap keyboard", "", "\"Oh, what the hell?!\"", "",
                        "\"Wait, so if this happens first, then..\"", "", "\"Maybe it would be better if I did it like this..\"",
                        "", "After a couple of hours at the computer, the assignment is finished!",
                        "The computer clock says it's 7am", "You'd better hand it in right away",
                        "You press the submit button, and power off the computer",
                        "Your exhaustion finally catches up to you after all this time",
                        "You contemplate taking a nap in the surprisingly comfy computer chair", "But that would be a bad idea",
                        "You live pretty close anyway", "Time to go home", "You get up from the computer chair");
                player.setCompletedAssignment();
            } else {
                Console.printConversationWithPauses(
                        "You already finished the assignment",
                        "But I guess there's no hurry to get back home yet",
                        "You turn on the computer",
                        "It takes a minute or two to boot",
                        "You check your email, and your inbox is empty",
                        "You check social media, but none of your friends posted anything",
                        "Fair enough its so late in the night.. what sane person would be awake to post something",
                        "Hehe"
                );
            }
        } else {
            Console.printConversationWithPauses("But nothing happens",
                    "You wait for a couple more seconds but the screen is still black",
                    "The computer gives no sign of life, it probably doesn't have any power",
                    "Of course it doesn't.. you think to yourself as you get up from the chair");
        }
    }
}