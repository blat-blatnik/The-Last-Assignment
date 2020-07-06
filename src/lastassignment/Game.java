package lastassignment;

import lastassignment.doors.Door;
import lastassignment.doors.HallucinatedDoor;
import lastassignment.items.BunnyRabbit;
import lastassignment.items.HallucinatedItem;
import lastassignment.items.Item;
import lastassignment.items.Map;
import lastassignment.utils.Console;
import lastassignment.utils.GameInitializer;
import lastassignment.utils.GameStateLoader;
import lastassignment.utils.Interactable;
import lastassignment.io.Serializer;
import lastassignment.npcs.HallucinatedNPC;
import lastassignment.npcs.NPC;

import java.io.*;
import java.util.*;

/**
 * @author Boris
 * @author Jana
 * @version 42.0
 *
 * The RPG game: Quarantine: The Last Assignment
 * This class stores all of the state for the Game.
 * The game itself is intended to be more like a
 * text version of a light-horror mystery game,
 * like a lighter version Silent Hill - with a theme
 * appropriate for our times.
 *
 * @see GameInitializer
 * @see Player
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 3L;

    private Player player;

    /**
     * Initializes the Game from one of several methods. The player
     * can choose to initialize the game from a configuration file,
     * or to set the configuration file to the default one and play the
     * game with the default configuration, or to play the game normally
     * without using a config file.
     * This constructor makes use of the GameInitializer helper class,
     * and it will initialize the entire state of the game so that
     * Game.play() can be called.
     *
     * @see GameInitializer
     */
    public Game() {

        Console.printLine("Welcome to The Last Assignment");
        Console.printLine("  (1) Play");
        Console.printLine("  (2) Load settings from config file");
        Console.printLine("  (3) Load default settings");
        String input = Console.readInput();
        GameInitializer.InitMethod initMethod;
        switch (input) {
            default: //NOTE(Boris): If the player mistypes assume they just want to play normally
            case "1":
                initMethod = GameInitializer.InitMethod.NORMAL;
                break;
            case "2":
                initMethod = GameInitializer.InitMethod.FROM_CONFIG;
                break;
            case "3":
                initMethod = GameInitializer.InitMethod.RESET_CONFIG;
                break;
        }

        GameInitializer initializer = new GameInitializer(initMethod);
        player = initializer.initGameAndGetPlayer();
    }

    /**
     * Starts up the game loop. The game loop ends when the Player
     * either dies, wins, or gives up.
     *
     * @see Player
     */
    public void play() {

        dumpExposition();

        while (player.isAlive() && !player.hasWon()) {

            Room currentRoom = player.getCurrentRoom();

            currentRoom.inspect(player);
            Console.printLine("What do you want to do?");
            Console.printLine("  (1) Look around the room");
            Console.printLine("  (2) Look for a way out");
            Console.printLine("  (3) Look for company");
            Console.printLine("  (4) Check your backpack");
            Console.printLine("  (5) Examine yourself more closely");
            if (player.hasMap())
                Console.printLine("  (6) Look at your map");
            if (player.getCompanion() != null)
                Console.printLine("  (7) approach your companion");
            Console.printLine("  (J) Open up your journal");
            Console.printLine("  (S) QuickSave");
            Console.printLine("  (L) QuickLoad");
            Console.printLine("  (X) Give up");

            String input = Console.readInput();
            switch (input) {
                case "1":
                    lookAroundRoom();
                    break;
                case "2":
                    lookForDoors();
                    break;
                case "3":
                    lookForNPCs();
                    break;
                case "4":
                    checkoutBackpack();
                    break;
                case "5":
                    player.inspect(player);
                    break;
                case "6":
                    if (player.hasMap()) {
                        Map map = player.getMap();
                        map.interact(player);
                    }
                    break;
                case "7":
                    if (player.getCompanion() != null) {
                        player.getCompanion().interact(player);
                    }
                case "S":
                    Serializer.savePlayer(player, "quicksave");
                    break;
                case "L":
                    GameStateLoader.loadFrom(this, "quicksave");
                    break;
                case "J":
                    Journal.open(this, player);
                    break;
                case "X":
                    Console.printLine("Are you sure you just want to give up now?");
                    Console.printLine("  (Y)es, this is too much to handle!");
                    Console.printLine("  (N)o, I wanna keep going for now");
                    String selection = Console.readInput();
                    if (selection.equals("Y")) {
                        player.die();
                    }
            }
        }
    }

    /**
     * Prints out a congratulations banner saying.
     */
    public static void winGame(){
        Console.printConversationWithPauses(
                "You get home and fall face first into bed",
                "You did it!",
                "Now you can finally get some sleep!",
                "Thank god for the quarantine, you don't have any classes today\n",
                "\n=================================" +
                "\n        YOU WON - THE END       " +
                "\n================================="
        );
    }

    /**
     * Prints out a game over message.
     */
    public static void loseGame(){
        Console.printWithPause(
                "\n=================================" +
                "\n      GAME OVER... YOU LOST!     " +
                "\n================================="
        );
    }

    /**
     * Prints out a dialogue where the Player looks around the current Room they
     * are in for any Doors they could use to escape. The player then selects a door
     * and goes through it, or they decide to stay in the same Room.
     *
     * @see Player
     * @see Door
     */
    private void lookForDoors() {
        Room currentRoom = player.getCurrentRoom();
        List<Door> doors = new ArrayList<>(currentRoom.getDoors());

        if (player.isHallucinating()) {
            while (new Random().nextInt(10) < 2) {
                doors.add(new HallucinatedDoor());
            }
        }

        lookThroughInteractables(doors,
                "You look around for doors",
                "But there don't seem to be any here.. How did you get here??",
                "Which door do you take? (0 : stay here)");
    }

    /**
     * Prints out a dialogue where the Player looks around the current Room they
     * are in for any NPCs they could interact with. The player then selects an NPC
     * and goes interacts with them, or they decide not do interact with any of the NPCs.
     *
     * @see Player
     * @see NPC
     */
    private void lookForNPCs() {
        Room currentRoom = player.getCurrentRoom();
        List<NPC> NPCs = new ArrayList<>(currentRoom.getNPCs());

        if (player.isHallucinating()) {
            while (new Random().nextInt(10) < 2) {
                NPCs.add(new HallucinatedNPC());
            }
        }

        lookThroughInteractables(NPCs,
                "You look to see if there's someone here",
                "But there isn't",
                "Who do you approach? (0 : keep your distance)");

        List<NPC> roomNPCs = currentRoom.getNPCs();
        roomNPCs.removeIf(npc -> !NPCs.contains(npc));
    }

    /**
     * Prints out a dialogue where the Player looks though the contents of the current room
     * they are in. The player can then select an item in the Room which they want to inspect
     * further, or they could decide not to inspect anything.
     *
     * @see Player
     * @see NPC
     */
    private void lookAroundRoom() {
        Room currentRoom = player.getCurrentRoom();
        List<Interactable> contents = new ArrayList<>(currentRoom.getContents());

        if (player.isHallucinating()) {
            while (new Random().nextInt(10) < 2) {
                contents.add(new HallucinatedItem());
            }
        }

        lookThroughInteractables(contents,
                "You examine the room more closely",
                "But there's nothing here, its just " + currentRoom.getDescription(),
                "What do you want to interact with? (0 : nothing)");

        List<Interactable> roomContents = currentRoom.getContents();
        roomContents.removeIf(item -> !contents.contains(item));
    }

    /**
     * Prints out a dialogue where the Player looks though the contents of their inventory.
     * All Items are printed, but only Usable Items can be selected by the player.
     *
     * @see Player
     * @see Item
     */
    private void checkoutBackpack() {
        List<Item> inventory = new ArrayList<>(player.getItemsInBackpack());

        if (player.isHallucinating()) {
            while (new Random().nextInt(10) < 2) {
                inventory.add(new HallucinatedItem());
            }
        }

        lookThroughInteractables(inventory,
                "You check your backpack",
                "But there's nothing useful in it.. Just some candy wrappers and broken pencils",
                "Which one do you use? (0 : none of them)");

        List<Item> playerInventory = player.getItemsInBackpack();
        playerInventory.removeIf(item -> !inventory.contains(item));
    }

    /**
     * Helper function for running a dialogue with the player about which item
     * from a list they want to interact with.
     *
     * @param interactables The list of Interactables that the Player can choose from.
     * @param actionDescription Prints what the player does when looking through the list, for example "you look through your backpack".
     * @param emptyListDescription What to print if the list is empty
     * @param promptDescription What prompt to use for the player input, for example "What do you choose?"
     */
    private void lookThroughInteractables(
            List<? extends Interactable> interactables,
            String actionDescription,
            String emptyListDescription,
            String promptDescription)
    {
        Console.printWithPause(actionDescription);
        if (interactables.size() > 0) {
            Console.printLine("You see:");
            for (int i = 0; i < interactables.size(); ++i) {
                Interactable interactable = interactables.get(i);
                Console.print("  (%d) ", i + 1);
                interactable.inspect(player);
            }

            boolean playerMadeSelection = false;
            while (!playerMadeSelection) {
                Console.printLine(promptDescription);
                int selection = Console.readInt();
                if (selection >= 0 && selection <= interactables.size()) {
                    playerMadeSelection = true;
                    if (selection != 0) {
                        Interactable selectedInteractable = interactables.get(selection - 1);
                        selectedInteractable.interact(player);
                        if (selectedInteractable instanceof Item && player.hasItem((Item)selectedInteractable) && !(selectedInteractable instanceof BunnyRabbit)) {
                            interactables.remove(selectedInteractable);
                        }
                    }
                }
            }

        } else {
            Console.printWithPause(emptyListDescription);
        }
    }

    /**
     * Print out the beginning of the Game story where the Player begins their adventure.
     */
    private void dumpExposition() {

        Console.printConversationWithPauses(
                "You wake up with a terrible throbbing headache",
                "Its the middle of the night",
                "You pull yourself up, and stand up on two feet, and then it hits you",
                "You forgot to hand in your assignment, AND THE DEADLINE IS TOMORROW MORNING!",
                "Growling thunder echoes through the air, spreading through your room",
                "\"Maybe it's a good thing that I woke up.\", you think to yourself",
                "\"At least I didn't forget.\"",
                "You turn on your computer, eager to hand in your work",
                "\"Oh no.. no no NO!\" your cries drown out the sounds of thunder outside",
                "You forgot to save your work. It's all gone");

        boolean playerSelectedOption = false;
        boolean playerTriedBlamingVirus = false;
        boolean playerLookedForBackups = false;

        while (!playerSelectedOption) {
            Console.printLine("What do you want to do?");
            Console.printLine("  (1) Go back to sleep");
            Console.printLine("  (2) Feel bad about yourself");
            Console.printLine("  (3) Try to redo the whole assignment, from scratch");
            Console.printLine("  (4) Desperately look for backups");
            Console.printLine("  (5) Take advantage of the recent outbreak, blame it all on the virus. " +
                    "The professor might let it slide");
            Console.printLine("  (6) Call your friend for help");
            Console.printLine("  (7) Try to think of another solution");

            String input = Console.readInput();
            switch (input) {
                case "1":
                    Console.printConversationWithPauses(
                            "You go back to bed and try to force yourself back to sleep",
                            "You try everything.. counting sheep, focusing on your breathing",
                            "But you just can't get the assignment out of your head!",
                            "You already did the work, and now you're just gonna fail?",
                            "NO! You have to do something!");
                    break;
                case "2":
                    Console.printConversationWithPauses(
                            "How could let this happen again?",
                            "Don't you learn from your past mistakes?",
                            "\"Hit control-s after every line of code.\".. Why don't you listen to your own advice?");
                    break;
                case "3":
                    Console.printConversationWithPauses(
                            "There's no way you could do that",
                            "It took you over a month of bloody work to finish this semester project",
                            "You're not gonna be able to redo it all in one night",
                            "Maybe you could do it you didn't have to start from literal 0");
                    break;
                case "4":
                    if (!playerLookedForBackups) {
                        Console.printConversationWithPauses(
                                "You begin to scour your computers hard drive",
                                "Maybe you made a backup somewhere and forgot about it",
                                "Alas, after a few minutes, the search results confirm your fear:",
                                "there's NOTHING left of it");
                        playerLookedForBackups = true;
                    } else {
                        Console.printConversationWithPauses(
                                "You try scouring your computer once more",
                                "You must have made a backup somewhere!",
                                "But you didn't. You never do");
                    }
                    break;
                case "5":
                    if (!playerTriedBlamingVirus) {
                        Console.printWithPause(".. Nah, that will never work, you have to try something else");
                        playerTriedBlamingVirus = true;
                    } else {
                        Console.printWithPause("No, really, that won't work");
                    }
                    break;
                case "6":
                    Console.printConversationWithPauses(
                            "You reach for your phone, but there's no reception",
                            "The storm outside must have cut it off",
                            "Besides, it's the middle of the night, your friend is probably asleep anyway");
                    break;
                case "7":
                    playerSelectedOption = true;
                    break;
            }
        }

        Console.printConversationWithPauses(
                "\n\"Hmm..\" you spend a few minutes scratching your head",
                "And then you recall: you worked though this assignment on the university computers",
                "There must be a backup on those computers!",
                "That was already over a week ago. The school has since been closed due to the virus outbreak",
                "The work may be a week old, but at least its partially complete",
                "Maybe you could redo the few bits that were missing, and be done before the deadline!",
                "The school might be closed now",
                "But you're willing to risk breaking in for a good grade",
                "Get in, finish the assignment, get the backup, get out, it should be easy",
                "You put on some clothes and a rain-coat, and start heading towards the school",
                "",
                "After a few minutes in the rain, you finally reach the entrance of one of the campus buildings",
                "Good thing you live so close to the school, otherwise you would have been soaked!",
                "You walk over to the entrance, and then you notice the sign",
                "\"Closed until further notice due to the virus outbreak, Sorry!\"",
                "Despite this sign, the inside of the building is actually lit up, how strange",
                "Applying a forceful nudge, you push through the revolving doors of the university building",
                "You're in",
                "And thus, your quest for the school computer room begins!");

        Console.printWithPause(
                "\n=================================" +
                        "\n Quarantine: The Last Assignment" +
                        "\n=================================\n"
        );
    }

    /**
     * Sets a new player for loading an old game-state.
     * @param player player with old game-state
     */
    public void setPlayer(Player player){
        this.player = player;
    }

}