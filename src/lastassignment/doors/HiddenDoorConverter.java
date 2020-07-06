package lastassignment.doors;

import lastassignment.Room;

/**
 * @author Jana
 * @version 1.0
 *
 * The HiddenDoorConverter converts HiddenDoors into regular Doors.
 *
 * @see Door
 * @see HiddenDoor
 */
public class HiddenDoorConverter {

    /**
     * Converts the given HiddenDoor into a regular Door. The HiddenDoor
     * is removed from a Rooms list of *contents* and gets placed in the
     * Rooms list of *doors*.
     *
     * @param hiddenDoor The HiddenDoor which to convert.
     * @see Door
     * @see HiddenDoor
     * @see Room
     */
    public static void convertHiddenDoor(HiddenDoor hiddenDoor) {

        //
        //NOTE(Boris): Why the do we need this???? Answer: We don't! Remove it!
        //

        if (!hiddenDoor.isHidden()) {
            String descriptionFromRoom1 = hiddenDoor.getRevealedDescriptionFromRoom1();
            String descriptionFromRoom2 = hiddenDoor.getRevealedDescriptionFromRoom2();
            Room room1 = hiddenDoor.getRoom1();
            Room room2 = hiddenDoor.getRoom2();

            new Door(descriptionFromRoom1, descriptionFromRoom2, room1, room2);

            room1.removeHiddenDoorFromContents(hiddenDoor);
            room2.removeHiddenDoorFromContents(hiddenDoor);
        } 
    }
}
