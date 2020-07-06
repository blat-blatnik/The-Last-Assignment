package lastassignment.doors;

import lastassignment.utils.Console;
import lastassignment.utils.Interactable;
import lastassignment.Player;
import lastassignment.Room;

/**
 * @author Boris
 * @author Jana
 * @version 1.1
 *
 * The HiddenDoor does not add itself to the list of a Rooms doors, unlike a regular Door.
 * Instead, it adds itself to the list of the rooms contents. So the Player cannot initially
 * see this door when they "look for a way out". Instead they have to first "look around the room"
 * and interact with this door. The HiddenDoor is then revealed and acts like a regular Door from
 * then on.
 *
 * @see Door
 * @see Room
 * @see Player
 * @see HiddenDoorConverter
 */
public class HiddenDoor extends Door {

    private final String revealedDescription;
    private final String revealedDescriptionFromRoom2;
    private boolean hidden;

    /**
     * Constructs a HiddenDoor from 2 sets of 2 descriptions each, one set for each side of the door respectively.
     * One of the descriptions is for when the door is hidden, for example "a bookcase", another is for when the
     * door has been revealed, for example "a secret bookcase door".
     * The HiddenDoor will be hidden by default, and will add itself to the *contents* of the Room,
     * unlike a regular Door, it *won't* add itself as a proper Door to the Room.
     *
     * @param hiddenDescriptionFromRoom1 The description of this door from the first Room while it is still hidden.
     * @param revealedDescriptionFromRoom1 The description of this door from the first Room when it is no longer hidden.
     * @param hiddenDescriptionFromRoom2 The description of this door from the second Room while it is still hidden.
     * @param revealedDescriptionFromRoom2 The description of this door from the second Room when it is no longer hidden.
     * @param room1 The first Room that this door connects to.
     * @param room2 The second Room that this door connects to.
     * @see Door
     * @see Room
     */
    public HiddenDoor(String hiddenDescriptionFromRoom1, String revealedDescriptionFromRoom1,
                      String hiddenDescriptionFromRoom2, String revealedDescriptionFromRoom2,
                      Room room1, Room room2)
    {
        super(hiddenDescriptionFromRoom1, hiddenDescriptionFromRoom2, room1, room2);
        this.revealedDescription = revealedDescriptionFromRoom1;
        this.revealedDescriptionFromRoom2 = revealedDescriptionFromRoom2;
    }

    /**
     * Constructs a HiddenDoor from a description shown while the door is still hidden, a description
     * that will be shown once the door has been revealed, and the two Rooms that the door connects to.
     *
     * @param hiddenDescription The description of this Door from both sides, when it is still hidden.
     * @param revealedDescription The description of this Door from both sides, when it has been revealed.
     * @param room1 The first Room that this door connects to.
     * @param room2 The second Room that this door connects to.
     * @see Door
     * @see Room
     */
    public HiddenDoor(String hiddenDescription, String revealedDescription, Room room1, Room room2) {
        this(hiddenDescription, revealedDescription, hiddenDescription, revealedDescription, room1, room2);
    }

    /**
     * Adds this HiddenDoor as the *contents* of both of it's respective rooms.
     * A regular Door adds itself to the *doors*.
     *
     * @param room1 The first room to add this door to.
     * @param room2 The second room to add this door to.
     * @see Door
     */
    @Override
    protected void addDoorToRooms(Room room1, Room room2) {
        room1.addContents(this);
        room2.addContents(this);
    }

    /**
     * The player interacts with this Door when it is still in the list
     * of the Rooms *contents*. The player then discovers that this is a hidden
     * door, and not random Room clutter, and the Door gets converted to a
     * regular Door.
     *
     * @param player The player.
     * @see Player
     * @see Interactable
     * @see HiddenDoorConverter
     */
    @Override
    public void interact(Player player) {
        String descriptionFromCurrentRoom = getDescription(player);
        Console.printWithPause("Its just " + descriptionFromCurrentRoom.toLowerCase() + ".. hmm");
        Console.printWithPause("Maybe you can use this as a way out of here");
        hidden = false;
        HiddenDoorConverter.convertHiddenDoor(this);
    }

    /**
     * @return true iff the door is still hidden.
     * @see HiddenDoorConverter
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @return The description as seen from the first room, when it has been revealed by the Player.
     * @see HiddenDoorConverter
     */
    public String getRevealedDescriptionFromRoom1() {
        return revealedDescription;
    }

    /**
     * @return The description as seen from the second room, when it has been revealed by the Player.
     * @see HiddenDoorConverter
     */
    public String getRevealedDescriptionFromRoom2() {
        return revealedDescriptionFromRoom2;
    }
}
