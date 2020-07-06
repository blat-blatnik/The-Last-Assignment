package lastassignment;

import lastassignment.doors.Door;
import lastassignment.doors.HiddenDoor;
import lastassignment.utils.Console;
import lastassignment.utils.Inspectable;
import lastassignment.utils.Interactable;
import lastassignment.npcs.NPC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A room in the game.
 */
public class Room implements Inspectable, Serializable {

    private static final long serialVersionUID = 42L;

    private final String description;
    private final List<NPC> NPCs;
    private final List<Interactable> contents;
    private final ArrayList<Door> doors; //NOTE(Boris): Assignment requirements state this needs to be an ArrayList specifically and not a List.

    /**
     * Initialises a Room with a description and empty arrays for NPCs, contents and doors.
     * @param description description of the room
     */
    public Room(String description) {
        this.description = description;
        doors = new ArrayList<>();
        NPCs = new ArrayList<>();
        contents = new ArrayList<>();
    }

    /**
     * Lets the player inspect the room, i.e. see the description.
     * @param player current player
     */
    @Override
    public void inspect(Player player){
        Console.printWithPause("You are in %s", description);
    }

    /**
     * Gets contents of the room.
     * @return contents
     */
    public List<Interactable> getContents() {
        return contents;
    }

    /**
     * Gets description of the room.
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Adds new item to contents
     * @param item interactable item
     */
    public void addContents(Interactable item) {
        contents.add(item);
    }

    /**
     * Gets all visible doors (not the hidden ones)
     * @return doors that are visible
     */
    public List<Door> getDoors() {
        List<Door> nonHiddenDoors = new ArrayList<>();
        for (Door d : doors) {
            if (!(d instanceof HiddenDoor)) nonHiddenDoors.add(d);
        }
        return nonHiddenDoors;
    }

    /**
     * Adds new door to the room.
     * @param door new door
     */
    public void addDoor(Door door) {
        if (door != null)
            doors.add(door);
    }

    /**
     * Gets all NPCs that are in the room
     * @return list of NPS
     */
    public List<NPC> getNPCs() {
        return NPCs;
    }

    /**
     * Adds a new NPC to the room.
     * @param npc new NPC
     */
    public void addNPC(NPC npc) {
        if (npc != null)
            NPCs.add(npc);
    }

    /**
     * Removes NPC from the room.
     * @param npc NPC that is removed
     */
    public void removeNPC(NPC npc) {
        if (npc != null)
            NPCs.remove(npc);
    }

    /**
     * removes hidden doors from contents
     * @param hiddenDoor hidden door that is removed
     */
    public void removeHiddenDoorFromContents(HiddenDoor hiddenDoor) {
        if (hiddenDoor != null) {
            contents.remove(hiddenDoor);
        }
    }

}