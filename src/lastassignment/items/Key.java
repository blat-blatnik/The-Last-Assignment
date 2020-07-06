package lastassignment.items;

import lastassignment.doors.LockedDoor;

/**
 * @author Jana
 * @version 1.0
 *
 * A key can open LockedDoor's.
 *
 * @see LockedDoor
 */
public class Key extends Item {

    /**
     * Initializes a key with a description.
     * @param description The description of the Key.
     */
    public Key(String description) {
        super(description);
    }
}