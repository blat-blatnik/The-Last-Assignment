package lastassignment.utils;

import lastassignment.Player;

/**
 * @author Boris
 * @author Jana
 * @version 2.0
 *
 * Interactable objects can be interacted with by the Player.
 * All Interactable objects are also Inspectable, since it doesn't
 * really make sense to interact with something that you can't inspect.
 *
 * @see Inspectable
 */
public interface Interactable extends Inspectable {

    /**
     * The Player interacts with the object.
     *
     * @param player The player that interacts with the object.
     */
    void interact(Player player);
}