package lastassignment.doors;

import lastassignment.roomcontents.Computer;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;
import lastassignment.Player;
import lastassignment.Room;

/**
 * @author Boris
 * @version 1.0
 *
 * The SchoolEntrance is a special Door that allows the Player to enter the school map,
 * but it does not allow them to leave until they have completed the assignment. Upon
 * completion of the assignment, the player can pass through the SchoolEntrance to exit
 * the school and win the game.
 * In short, this is a special one-way door, until the player finishes the assignment.
 *
 * @see Door
 * @see Player
 * @see Computer
 */

public class SchoolEntrance extends Door {

    /**
     * This constructor initializes the SchoolEntrance with the two rooms that it connects.
     * Just like a regular Door, this will add the SchoolEntrance to the list of doors of both
     * rooms.
     *
     * Since this class is a singleton, there is no need to pass in a description as an argument
     * like with other Doors.
     *
     * @param room1 The first room that this door connects to.
     * @param room2 The second room that this door connects to.
     * @see Door
     */
    public SchoolEntrance(Room room1, Room room2) {
        super("annoyingly slow revolving door", room1, room2);
    }

    /**
     * The Player tries to pass through the SchoolEntrance. If the Player
     * completed the assignment, then they can pass through both ways. However,
     * if the player did not complete the assignment yet, they can only pass
     * from room1 to room2, and not the other way around.
     *
     * @param player The player.
     * @see Player
     * @see Door
     * @see Computer
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        Room room1 = getRoom1();
        Room room2 = getRoom2();

        Room currentRoom = player.getCurrentRoom();
        assert currentRoom == room1 || currentRoom == room2;

        if (currentRoom == room1) {
            Console.printWithPause("You go through the painfully slow revolving door and into the school building");
            player.setCurrentRoom(room2);
        } else {
            if (player.hasCompletedAssignment()) {
                Console.printWithPause("You go back through the painfully slow revolving door, and out of the school");
                Console.printWithPause("Finally!");
                player.setCurrentRoom(room1);
                player.setWonGame();
            } else {
                Console.printWithPause("This door leads out of the school");
                Console.printWithPause("But you haven't finished your assignment yet!");
                Console.printWithPause("You should find a working computer first");
                Console.printWithPause("You turn back");
            }
        }

    }

}