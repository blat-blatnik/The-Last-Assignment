package lastassignment.npcs;

import lastassignment.Player;
import lastassignment.Room;
import lastassignment.items.Clothes;
import lastassignment.utils.Console;
import lastassignment.utils.Infectable;
import lastassignment.utils.Interactable;

import java.io.Serializable;

/**
 * @author Jana
 * @author Boris
 * @version 1.0
 *
 * NPC is an abstract superclass that implements common fields of all NPCs, such as description,
 * infection status, health and damage, as well as common features, such as defaults for inspect and interact.
 *
 * @see Infectable
 * @see Interactable
 * @see Serializable
 */
public abstract class NPC implements Infectable, Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private String description;
    private boolean infected;
    private final int damage;

    /**
     * health status of the NPC
     */
    protected int health;


    /**
     * Gives the possibility to initialize an NPC without a room, e.g. for an enemy that "belongs" to AmbushDoor.
     * @param description their description
     * @param health their health status
     * @param damage damage they can do
     * @param infected infection status
     */
    NPC(String description, int health, int damage, boolean infected) {
        this.description = description;
        this.health = health;
        this.damage = damage;
        this.infected = infected;
    }

    /**
     * Initializes the NPC with a description, health, damage, infected flag, and in a certain Room.
     * The NPC is added to the NPC list of the given Room.
     *
     * @param description Description of the NPC.
     * @param health Health points of the NPC.
     * @param damage Damage of the NPC.
     * @param infected Whether the NPC is infected or not.
     * @param room What room the NPC should be added to.
     */
    NPC(String description, int health, int damage, boolean infected, Room room) {
        this(description, health, damage, infected);
        room.addNPC(this);
    }

    /**
     * Lets the player inspect the NPC.
     * @param player player that inspects
     * @see Player
     */
    @Override
    public void inspect(Player player){
        Console.printLine(description);
    }

    /**
     * Lets the player interact with the NPC and infection is exchanged in case one is infected (and not immune).
     * @param player player that interacts with NPC
     * @see Player
     */
    @Override
    public void interact(Player player) {
        Console.printWithPause("You share a few quiet moments with " + description.toLowerCase());
        if (this.isInfected() && !player.isImmune()){
            player.getInfected();
        } else if (!this.isInfected() && player.isInfected()) {
            Clothes clothes = player.getCurrentOutfit();
            if (clothes == null || !clothes.offersVirusProtection())
                this.getInfected();
        }
    }

    /**
     * Lets the NPC get infected with the virus.
     */
    @Override
    public void getInfected() {
        infected = true;
    }

    /**
     * Determines whether the NPC is infected or not.
     * @return true if infected, otherwise false
     */
    @Override
    public boolean isInfected() {
        return infected;
    }

    /**
     * Determines whether an NPC is alive.
     * @return true if alive, otherwise false
     */
    public boolean isAlive() {
        return (health > 0);
    }

    /**
     * Gets description.
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Gets health status.
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets damage of the NPC
     * @return damage it could do
     */
    public int getDamage() {
        return damage;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
