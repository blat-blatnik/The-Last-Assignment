package lastassignment.doors;

import lastassignment.items.Key;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;
import lastassignment.Player;
import lastassignment.Room;

/**
 * @author Boris
 * @version 2.0
 *
 * The LockedDoor connects two rooms - however, the Player needs to
 * have the appropriate Key in their inventory in order to unlock the
 * LockedDoor so they can pass through. Until this happens the Player
 * cannot pass through.
 *
 * @see Door
 * @see Key
 */
public class LockedDoor extends Door {

    private boolean locked;
    private final Key key;

    /**
     * Constructs a LockedDoor from the two Rooms that the door connects to, as well as 2
     * descriptions shown respectively from each side of the door, and a Key that is used to
     * unlock the Door.
     * Just like a regular Door, this will add this door to both of the Rooms.
     *
     * @param descriptionFromRoom1 The description of this door as seen from room1.
     * @param descriptionFromRoom2 The description of this door as seen from room2.
     * @param room1 The first Room that this door connects to.
     * @param room2 The second Room that this door connects to.
     * @param key The Key needed by the Player to unlock this door.
     * @see Room
     * @see Key
     * @see Door
     */
    public LockedDoor(String descriptionFromRoom1, String descriptionFromRoom2, Room room1, Room room2, Key key) {
        super(descriptionFromRoom1, descriptionFromRoom2, room1, room2);
        this.locked = true;
        this.key = key;
    }

    /**
     * Constructs a LockedDoor from the two Rooms that the door connects to, a Key that
     * unlocks the door, and only a single description that is shown from *both* sides of
     * the door.
     * Just like a regular Door, this will add this door to both of the Rooms.
     *
     * @param description The description of this door from *both* sides.
     * @param room1 The first Room that the door connects to.
     * @param room2 The first Room that the door connects to.
     * @param key The Key needed by the Player to unlock this door.
     * @see Room
     * @see Key
     * @see Door
     */
    public LockedDoor(String description, Room room1, Room room2, Key key) {
        this(description, description, room1, room2, key);
    }

    /**
     * The Player tries to go through the door. If the LockedDoor is unlocked,
     * the Player can pass through and this acts like a regular Door. However,
     * if the Player didn't unlock the door yet, then they cannot pass through
     * unless they have the Key. If they *do* have the key, then they can unlock
     * the door, pass through, and it will stay unlocked.
     *
     * @param player The player.
     * @see Player
     * @see Key
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        if (locked) {
            Console.printWithPause("It appears that this door is locked");

            if (player.hasItem(key)) {
                Console.printWithPause("But you think you might have the key");
                Console.printWithPause("You jam the %s into the door, and it opens!", key.getDescription());
                locked = false;
                super.interact(player);
            } else {
                Console.printWithPause("But it seems like you don't have a key that would fit the lock");
                Console.printWithPause("You turn back, frustrated");
            }
        } else {
            super.interact(player);
        }
    }

}
