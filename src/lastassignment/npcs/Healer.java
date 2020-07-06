package lastassignment.npcs;

import lastassignment.Player;
import lastassignment.Room;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

/**
 * @author Boris
 * @version 1.0
 *
 * Healer is an NPC and can heal the player.
 *
 * @see NPC
 * @see Interactable
 */
public class Healer extends NPC implements Interactable {

    private final int healAmount;
    private int healCharges;

    public Healer(String description, int health, int damage, boolean infected, Room room) {
        super(description, health, damage, infected, room);
        this.healAmount  = 10;
        this.healCharges = 2;
    }

    /**
     * Lets the player interact with the healer and lets the healer heal the player.
     * @param player player that might get healed
     * @see Player
     */
    @Override
    public void interact(Player player) {

        super.interact(player);

        if (player.getHealth() < player.getMaxHealth()) {
            Console.print("She notices the many wounds on your body ");
            if (healCharges > 0) {

                Console.printWithPause("and she offers to heal you");

                boolean conversationInProgress = true;
                while (conversationInProgress) {
                    Console.printLine("What do you say?");
                    Console.printLine("  (1) Accept her service");
                    Console.printLine("  (2) Whats the price?");
                    Console.printLine("  (3) Politely refuse");

                    String input = Console.readString();
                    switch (input) {
                        case "1":
                            Console.printWithPause("The %s swiftly prepares a murky-looking potion using what appear " +
                                    "to be glowing bugs", getDescription());
                            Console.printWithPause("She tells you to drink it quickly, and you do");
                            Console.printWithPause("You quickly start feeling better! You healed " + healAmount +
                                    " hit points");
                            Console.printWithPause("You thank the %s for their service", getDescription());
                            player.heal(healAmount);
                            healCharges -= 1;
                            conversationInProgress = false;
                            break;
                        case "2":
                            Console.printWithPause("\"Why, completely free of charge for you my friend\" she says");
                            Console.printWithPause("\"Totally out of goodness of my heart\"");
                            break;
                        case "3":
                            Console.printWithPause("\"Suit yourself..\" the %s mumbles as you leave", getDescription());
                            conversationInProgress = false;
                            break;
                    }
                }

            } else {
                Console.printWithPause("but she is too exhausted to help you");
            }
        }
    }
}