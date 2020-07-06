package lastassignment.doors;

import lastassignment.npcs.Enemy;
import lastassignment.npcs.NPC;
import lastassignment.utils.Console;
import lastassignment.Player;
import lastassignment.Room;
import lastassignment.utils.Inspectable;
import lastassignment.utils.Interactable;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * @author Boris
 * @version 1.0
 *
 * Each door "belongs" to two rooms, and connects them together.
 * They are the only means by which the player can cross from one room to another.
 *
 * @see Room
 * @see Interactable
 * @see Serializable
 */
public class Door implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private final Room room1;
    private final Room room2;
    protected String descriptionFromRoom1;
    protected String descriptionFromRoom2;

    /**
     * Constructs a Room from 2 descriptions that will be shown when the player is in the corresponding Rooms.
     * Calling this constructor will automatically add the door to both Rooms.
     *
     * @param descriptionFromRoom1 The description the player will see from room1.
     * @param descriptionFromRoom2 The description the player will see from room2.
     * @param room1 The first room that the door belongs to.
     * @param room2 The second room that the door belongs to.
     * @see Room
     */
    public Door(String descriptionFromRoom1, String descriptionFromRoom2, Room room1, Room room2) {
        this.descriptionFromRoom1 = descriptionFromRoom1;
        this.descriptionFromRoom2 = descriptionFromRoom2;
        this.room1 = room1;
        this.room2 = room2;
        addDoorToRooms(room1, room2);
    }

    /**
     * Constructs a Room with a single description that will be shown from both sides of the Door.
     * Calling this constructor will automatically add the door to both Rooms.
     *
     * @param description The description the player will see from room1 AND room2.
     * @param room1 The first room that the door belongs to.
     * @param room2 The second room that the door belongs to.
     */
    public Door(String description, Room room1, Room room2) {
        this(description, description, room1, room2);
    }

    /**
     * Add this door to the door list of both rooms.
     * Used because some subclasses (HiddenDoor) might not want
     * to register themselves as Doors in the room (because they are hidden).
     *
     * @param room1 The first room to add this door to.
     * @param room2 The second room to add this door to.
     * @see HiddenDoor
     */
    protected void addDoorToRooms(Room room1, Room room2) {
        room1.addDoor(this);
        room2.addDoor(this);
    }

    /**
     * @param player The player.
     * @return The description of the door from the player's current Room.
     * @see Player
     */
    public String getDescription(Player player) {
        Room currentRoom = player.getCurrentRoom();
        assert currentRoom == room1 || currentRoom == room2;

        if (currentRoom == room1) {
            return descriptionFromRoom1;
        } else {
            return descriptionFromRoom2;
        }
    }

    public void setDescription(String description) {
        descriptionFromRoom1 = description;
        descriptionFromRoom2 = description;
    }

    /**
     * Prints the appropriate description of this room based on the current Room of the player.
     *
     * @param player The player.
     * @see Player
     * @see Inspectable
     */
    @Override
    public void inspect(Player player) {
        Room currentRoom = player.getCurrentRoom();

        if (currentRoom == room1) {
            Console.printLine(descriptionFromRoom1);
        } else if (currentRoom == room2) {
            Console.printLine(descriptionFromRoom2);
        }
    }

    /**
     * Transports the player to the opposite Room. If the next Room has
     * Enemies in it, they could randomly initiate combate with the player immediately.
     *
     * @param player The player.
     * @see Player
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        Room currentRoom = player.getCurrentRoom();
        assert currentRoom == room1 || currentRoom == room2;

        if (currentRoom == room1) {
            Console.printWithPause("You go through %s and into another room", descriptionFromRoom1.toLowerCase());
            player.setCurrentRoom(room2);
        } else {
            Console.printWithPause("You go through %s and into another room", descriptionFromRoom2.toLowerCase());
            player.setCurrentRoom(room1);
        }

        if (player.getCompanion() != null)
            Console.printWithPause("Your companion robot follows");

        Room nextRoom = player.getCurrentRoom();
        List<NPC> npcsInNextRoom = nextRoom.getNPCs();
        Random rand = new Random();

        for (NPC npc : npcsInNextRoom) {
            if (npc instanceof Enemy) {
                Enemy enemy = (Enemy)npc;
                if (rand.nextInt(10) < 7) {
                    Console.printWithPause("As soon as you step foot into the next room, you are jumped by %s!", enemy.getDescription());
                    enemy.startFighting(player);
                    if (!enemy.isAlive())
                        Console.printWithPause("Phew.. You finally get a chance to take in your surroundings");
                    break;
                }
            }
        }
    }

    /**
     * Gets the first room the door is connected to.
     * @return room
     * @see Room
     */
    public Room getRoom1() {
        return room1;
    }

    /**
     * Gets the second room the door is connected to.
     * @return room
     * @see Room
     */
    public Room getRoom2() {
        return room2;
    }
}
