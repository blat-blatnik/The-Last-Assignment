package lastassignment.utils;

/**
 * @author Boris
 * @version 2.0
 *
 * Attackable objects can be attacked and killed.
 */
public interface Attackable {

    /**
     * The object gets attacked for a specified amount of damage.
     *
     * @param damage Damage to be done to the Attackable object.
     */
    void getAttackedFor(int damage);

    /**
     * @return Current number of health points.
     */
    int getHealth();

    /**
     * @return true iff the Attackable object can be considered "alive"
     */
    boolean isAlive();

    /**
     * The Attackable object dies.
     */
    void die();
}