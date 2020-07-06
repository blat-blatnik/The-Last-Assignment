package lastassignment.npcs;


import lastassignment.Player;
import lastassignment.Room;
import lastassignment.utils.Attackable;
import lastassignment.utils.Console;

import java.util.Random;

/**
 * @author Boris
 * @author Jana
 * @version 1.0
 *
 * Enemy is an NPC in the game, is Attackable by player and attacks back by default.
 *
 * @see NPC
 * @see Attackable
 */
public class Enemy extends NPC implements Attackable {

    /**
     * Initializes the enemy without a room, as it can also "belong" to a door.
     * @param description description of the enemy
     * @param health health status of the enemy
     * @param damage damage the enemy can do
     * @param infected infection status
     */
    public Enemy(String description, int health, int damage, boolean infected){
        super(description, health, damage, infected);
    }

    /**
     * Initializes the enemy with description, health, damage, infection (status) and a room.
     * @param description description of the enemy
     * @param health health status of the enemy
     * @param damage damage the enemy can do
     * @param infected infection status
     * @param room the room the enemy is in
     */
    public Enemy(String description, int health, int damage, boolean infected, Room room) {
        super(description, health, damage, infected, room);
    }

    /**
     * Lets the enemy be attacked for damage.
     * @param damage damage that will be done to the enemy's health
     */
    @Override
    public void getAttackedFor(int damage) {
        assert damage >= 0;
        health -= damage;
        if (health < 0)
            health = 0;
        Console.printWithPause("You attack the enemy for %s hit points, leaving them with %s hit points",
                damage, health);
        if (!this.isAlive()) {
            health = 0;
            die();
        }
    }

    /**
     * Lets the player interact with the enemy, and possibly fight them.
     * @param player player that interacts with enemy
     * @see Player
     */
    @Override
    public void interact(Player player) {
        super.interact(player);
        Console.printWithPause("But then you realize that they are hostile!", getDescription());
        startFighting(player);
    }

    /**
     * The Enemy initiates combat with the player. The player is given options to attack
     * or to run away.
     *
     * @param player The Player that the Enemy is attacking.
     */
    public void startFighting(Player player) {
        if (player.getCompanion() != null) {
            Console.printWithPause("Your companion angrily beeps: \"ProtectProtocol.initiate()\"");
        }

        Console.printWithPause("You have %d hit points", player.getHealth());
        Console.printWithPause("The enemy has %d hit points", this.getHealth());
        Console.printLine("");

        boolean playerFled = false;
        while (!playerFled && player.isAlive() && this.isAlive()) {

            Console.printLine("What do you do?");
            Console.printLine("  (1) Run away!");
            Console.printLine("  (2) Attack!");

            String input = Console.readInput();
            switch (input) {
                case "1":
                    Console.printWithPause("You dash away in the most cowardly fashion");

                    if (new Random().nextInt(10) < 8) {
                        if (player.getCompanion() != null) {
                            Console.printWithPause("Your robo-companion follows in your step");
                        }

                        Console.printWithPause("The enemy taunts you as you flee to safety");
                        playerFled = true;
                    } else {
                        Console.printWithPause("But the %s isn't fooled easily.. they catch up to you!");
                        if (this.isAlive())
                            player.getAttackedFor(getDamage());
                    }

                    break;
                case "2":
                    this.getAttackedFor(player.getDamage());
                    if (this.isAlive())
                        player.getAttackedFor(getDamage());
                    break;
            }
        }

        if (!this.isAlive()) {
            Room currentRoom = player.getCurrentRoom();
            currentRoom.removeNPC(this);
            die();
        }
    }

    /**
     * Lets the enemy die.
     */
    @Override
    public void die() {
        Console.printWithPause("%s collapses on the floor, knocked out of their shoes", getDescription());
    }

}
