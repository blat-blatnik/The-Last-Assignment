package lastassignment.utils;

import lastassignment.Player;

/**
 * @author Boris
 * @author Jana
 * @version 1.0
 *
 * Every inspectable object can be inspected by the player.
 *
 * @see Player
 */
public interface Inspectable {

    /**
     * The Player inspects the object.
     *
     * @param player The Player that inspects the object.
     */
    void inspect(Player player);
}
