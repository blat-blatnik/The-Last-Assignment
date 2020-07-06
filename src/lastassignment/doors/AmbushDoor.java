package lastassignment.doors;

import lastassignment.utils.Console;
import lastassignment.utils.Inspectable;
import lastassignment.utils.Interactable;
import lastassignment.Player;
import lastassignment.Room;
import lastassignment.npcs.Enemy;

/**
 * @author Jana
 * @author Boris
 * @version 2.0
 *
 * BlockedDoors are blocked by an enemy. When the player first tries to go through a AmbushDoor
 * they will get ambushed by the enemy and have to kill them in order to pass through.
 *
 * @see Door
 * @see Enemy
 */
public class AmbushDoor extends Door {

    private final Enemy enemy;
    private boolean blocked;

    /**
     * Constructs a AmbushDoor from two rooms that the door connects, two descriptions for both
     * sides of the door respectively, and an enemy that guards the door.
     * Just like the Door constructor, this constructor will automatically add the door to both rooms.
     *
     * @param descriptionFromRoom1 The description of this door when the player is in the first room.
     * @param descriptionFromRoom2 The description of this door when the player is in the second room.
     * @param room1 The first room that this door belongs to.
     * @param room2 The second room that this door belongs to.
     * @param enemy The enemy blocking the door.
     *
     * @see Enemy
     * @see Door
     */
    public AmbushDoor(String descriptionFromRoom1, String descriptionFromRoom2, Room room1, Room room2, Enemy enemy) {
        super(descriptionFromRoom1, descriptionFromRoom2, room1, room2);
        this.enemy = enemy;
        this.blocked = (enemy.isAlive());
    }

    /**
     * Constructs a AmbushDoor from two rooms that the door connects, an Enemy that
     * guards the door, and a single description for both sides of the door.
     * Just like the Door constructor, this constructor will automatically add the door to both rooms.
     *
     * @param description The description of the AmbushDoor from both sides.
     * @param room1 The first room that the door belongs to.
     * @param room2 The second room that the door belongs to.
     * @param enemy The Enemy blocking the door.
     *
     * @see Enemy
     * @see Door
     */
    public AmbushDoor(String description, Room room1, Room room2, Enemy enemy) {
        this(description, description, room1, room2, enemy);
    }

    /**
     * Inspects the door by the given player. If the Enemy was not defeated yet
     * the player will be informed that the door is guarded.
     *
     * @param player The player.
     * @see Player
     * @see Enemy
     * @see Inspectable
     */
    @Override
    public void inspect(Player player) {
        String currentDescription = getDescription(player);

        if (blocked){
            String newDescription = currentDescription + " guarded by " + enemy.getDescription();
            Console.printLine(newDescription);
        } else {
            Console.printLine(currentDescription);
        }
    }

    /**
     * The player tries to go through the AmbushDoor. If the Enemy guarding the door
     * is still alive, the player will be ambushed and enter combat with them. Otherwise
     * it just behaves like a normal Door.
     *
     * @param player The player.
     * @see Player
     * @see Enemy
     * @see Door
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        if (enemy.isAlive()) {
            Console.printWithPause("You try to open the door, when you see %s standing in front", enemy.getDescription());
            enemy.interact(player);
            if (!enemy.isAlive())
                blocked = false;
        }

        if (!blocked) {
            super.interact(player);
        } else {
            Console.printWithPause("Since the enemy won't go out of your way, you cannot open the the door");
            Console.printWithPause("Try later when you've regained your strength");
        }

    }
}
