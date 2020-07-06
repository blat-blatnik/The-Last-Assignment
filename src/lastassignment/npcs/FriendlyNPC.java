package lastassignment.npcs;

import lastassignment.Player;
import lastassignment.Room;
import lastassignment.utils.Attackable;
import lastassignment.utils.Console;


/**
 * @author Boris
 * @version 1.0
 *
 * FriendlyNPCs are just NPCs that don't do anything special.
 * Normally they don't do anything at all, they're more like
 * a decoration that adds atmosphere to the Game. The first time
 * a player talks to FriendlyNPC, the NPC will ask them for their
 * name.
 *
 * @see NPC
 * @see Attackable
 */
public class FriendlyNPC extends NPC {

    /**
     * Initializes the FriendlyNPC with description, health, damage, infection status and room.
     * The NPC is added to the Room's list of NPCs.
     *
     * @param description Their description.
     * @param health Their health.
     * @param damage The damage they can do.
     * @param infected Infection status
     * @param room The room they are in.
     * @see Room
     */
    public FriendlyNPC(String description, int health, int damage, boolean infected, Room room) {
        super(description, health, damage, infected, room);
    }

    /**
     * Lets the player interact with the FriendlyNPC and have a conversation with them.
     *
     * @param player player that interacts with friendly NPC
     * @see Player
     */
    @Override
    public void interact(Player player) {
        if (!player.wasAskedForName()) {
            giveSomeBackStoryAndAskForName(player);
        } else {
            super.interact(player);
            Console.printWithPause("But then you leave again, because %s don't seem too interesting", getDescription());
        }
    }

    /**
     * Lets the player have a conversation with the Friendly NPC
     * @param player player that talks to friendly NPC
     * @see Player
     */
    private void giveSomeBackStoryAndAskForName(Player player) {
        assert !player.wasAskedForName();

        Console.printWithPause("\"Hey!\" %s shouts", getDescription());
        Console.printWithPause("\"What are you doing here? The school is closed!\"");
        boolean playerHasResponded = false;
        while (!playerHasResponded) {
            Console.printLine("What do you say?");
            Console.printLine("  (1) \"Well why are you here then?\"");
            Console.printLine("  (2) \"Nothing, I was just leaving\"");
            Console.printLine("  (3) \"I need to use the school computer\"");
            String input = Console.readInput();
            switch (input) {
                case "1":
                    playerHasResponded = true;
                    Console.printWithPause("\"Hey don't flip this around on me! I asked YOU first\"");
                    break;
                case "2":
                    playerHasResponded = true;
                    Console.printWithPause("\"That's what I thought\"");
                    break;
                case "3":
                    playerHasResponded = true;
                    Console.printWithPause("\"Well I suggest you find some other computer to use\"");
                    break;
            }
        }

        Console.printWithPause("\"You really shouldn't be here, especially at this time of night\"");
        playerHasResponded = false;
        while (!playerHasResponded) {
            Console.printLine("What do you say?");
            Console.printLine("  (1) \"And why is that?\"");
            Console.printLine("  (2) \"I get it, I get it\"");
            Console.printLine("  (3) \"I know this place isn't safe - I was really just leaving\"");
            String input = Console.readInput();
            switch (input) {
                case "2":
                    Console.printWithPause("\"I hope you do\"");
                case "1":
                    playerHasResponded = true;
                    Console.printWithPause("\"There's nothing for you to do here anyway\"");
                    Console.printWithPause("\"You know, since the power's out and everything is broken and abandoned\"");
                    Console.printWithPause("\"And you definitely don't want one of those researchers getting their hands on you\"");
                    Console.printWithPause("");
                    Console.printWithPause("\"What? Why are you looking at me like that? Don't you know?\"");
                    Console.printWithPause("\"When the school was closed because of the epidemic, some researchers chose to stay\"");
                    Console.printWithPause("\"They were trying to synthesize a vaccine for the virus\"");
                    Console.printWithPause("\"But they've been stuck in those labs for so long\"");
                    Console.printWithPause("\"One of them must've had the virus, and it spread quickly amongst them\"");
                    Console.printWithPause("\"I think by now pretty much all of them have the virus\"");
                    Console.printWithPause("\"They all went insane, overworking themselves over that vaccine\"");
                    Console.printWithPause("\"And it was all for nothing\"");
                    Console.printWithPause("\"So there you go - you'd better be on your way out now\"");
                    break;
                case "3":
                    playerHasResponded = true;
                    Console.printWithPause("%s smiles", getDescription());
                    Console.printWithPause("\"Smart kid\"");
                    Console.printWithPause("\"Be on your way then\"");
                    break;
            }
        }

        Console.printWithPause("\"Oh by the way\"");
        Console.printWithPause("\"I never asked for your name\"");
        Console.printWithPause("\"What should I call you?\"");
        String name = Console.readString().trim();
        player.setName(name);
        if (name.length() == 0) {
            Console.printWithPause("\"Wow, you really don't wanna tell me? Im offended\"");
            Console.printWithPause("\"Fine enjoy your privacy\"");
            Console.printWithPause("%s seems pretty annoyed", getDescription());
        } else {
            Console.printWithPause("\"Ah, nice to meet you %s\"", name);
        }
    }
}
