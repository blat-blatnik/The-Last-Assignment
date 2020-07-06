package lastassignment;

import lastassignment.utils.Console;
import lastassignment.utils.GameStateLoader;
import lastassignment.io.Serializer;

import java.io.File;
import java.io.IOException;

/**
 * @author Boris
 * @author Jana
 * @version 1.2
 *
 * The Player uses his Journal to save and load a specific state of Player from a file.
 *
 * @see Game
 * @see Player
 */
public class Journal {

    /**
     * Opening the Journal starts a dialogue with the Player asking him if he
     * wants to save or load the Game.
     *
     * @param player The state of player as is (for potential saving).
     * @param game The game that the new state of player could be loaded into.
     * @see Game
     */
    public static void open(Game game, Player player) {
        Console.printWithPause("You take your leather bound journal out of your backpack");
        boolean playerResponded = false;
        while (!playerResponded) {
            Console.printLine("What would you like to do? (0: nothing)");
            Console.printLine("  (1) Write a new entry (Save game-state)");
            Console.printLine("  (2) Read an old entry (Load game-state)");
            Console.print("");
            String input = Console.readInput();
            switch (input) {
                case "1":
                    if (addNewEntryDialogue(player)) {
                        playerResponded = true;
                    }
                    break;
                case "2":
                    if (readOldEntryDialogue(game, player)) {
                        playerResponded = true;
                    }
                    break;
                case "0":
                    playerResponded = true;
                    break;
            }
        }
        Console.printWithPause("You put the journal back into your backpack for now");
    }

    /**
     * Opens a dialogue with the Player asking them to name their save file.
     * If the filename the player enters in response is valid, the given Game is then
     * saved to the given filename. The Player is notified of any problems if they
     * occur during saving, or if they would be overwriting another file.
     *
     * @param player the state of Player to save.
     * @return true if the state of Player is successfully saved.
     * @see Player
     */
    private static boolean addNewEntryDialogue(Player player) {

        Console.print("What will be the title of your new entry?");
        String saveFilename = Console.readString();
        File saveDirectory = new File("savedgames");
        String saveFilepath = saveDirectory + File.separator + saveFilename + ".ser";

        if (!isValidFilename(saveFilename)) {
            Console.printWithPause("%s is not a valid title", saveFilename);
            Console.printWithPause("You decide not to write it in the end");
            return false;
        }

        File saveFile = new File(saveFilepath);
        if (saveFile.exists()) {
            Console.printWithPause("You already have an entry called \"%s\"", saveFilename);
            boolean playerResponded = false;
            while (!playerResponded) {
                Console.printLine("Would you like to overwrite it?");
                Console.printLine("  (1) Yes - write over it!");
                Console.printLine("  (2) No! Don't");
                String input = Console.readInput();
                switch (input) {
                    case "1":
                        playerResponded = true;
                        Console.printLine("You start writing over your old journal entry");
                        break;
                    case "2":
                        Console.printWithPause("You decide not to write over your old journal entry");
                        Console.printWithPause("if feel like reading it later");
                        return false;
                }
            }
        }

        Serializer.savePlayer(player, saveFilename);
        return true;
    }

    /**
     * Launches a dialogue with the Player asking them whether what save file they
     * would like to load, from a list. If the player goes through with loading, the
     * save file is loaded and used to overwrite the state of the Player that was passed
     * in. The player is given an option to save the game before loading so they don't
     * accidentally lose their progress.
     *
     * @param game The game the new state will be loaded into.
     * @param player the current player
     * @return true if a state of Player is successfully loaded.
     * @see Game
     * @see Player
     */
    private static boolean readOldEntryDialogue(Game game, Player player) {
        String[] oldParagraphs = getListOfFilesIn("savedgames");
        if (oldParagraphs.length == 0) {
            Console.printWithPause("You realize that you haven't written any paragraphs yet");
            return false;
        }

        Console.printWithPause("You start flipping through your old paragraphs");

        boolean playerMadeSelection = false;
        while (!playerMadeSelection) {
            Console.printLine("Which journal entry would you like to read?");
            for (int i = 0; i < oldParagraphs.length; ++i) {
                Console.printLine("  (%d) %s", i + 1, oldParagraphs[i]);
            }
            Console.printLine("  (0) None of them - I changed my mind");

            int selectedParagraph = Console.readInt();
            if (selectedParagraph == 0) {
                return false;
            } else if (selectedParagraph > 0 && selectedParagraph <= oldParagraphs.length) {
                playerMadeSelection = true;
                String paragraphTitle = oldParagraphs[selectedParagraph - 1].split("\\.")[0];

                Console.printWithPause("You prepare to read the entry titled '%s'", paragraphTitle);
                boolean playerResponded = false;
                while (!playerResponded) {
                    Console.printLine("Would you like to write a new entry before reading this one?");
                    Console.printLine("  (1) Yes");
                    Console.printLine("  (2) No - start reading");
                    String input = Console.readInput();
                    switch (input) {
                        case "1":
                            addNewEntryDialogue(player);
                            playerResponded = true;
                            break;
                        case "2":
                            playerResponded = true;
                            break;
                    }
                }
                Console.printWithPause("You start reading your old journal entry, '%s'", paragraphTitle);
                GameStateLoader.loadFrom(game, paragraphTitle);
                return true;
            }
        }

        return false;
    }


    /**
     * @param filename The filename to check, for example "savedgames/save1.bin" or "::save1::".
     * @return true if the given String would make for a valid filename.
     */
    private static boolean isValidFilename(String filename) {

        if (filename.equals("")) return false;

        final char[] ILLEGAL_CHARACTERS = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };
        for (char fc : filename.toCharArray())
            for (char illegal : ILLEGAL_CHARACTERS)
                if (fc == illegal)
                    return false;

        File file = new File(filename);
        try {
            file.getCanonicalFile();
            return true;
        } catch (IOException io) {
            return false;
        }
    }

    /**
     * @param directory A directory whose files to list out.
     * @return An array of filenames of the files located in the given directory.
     */
    private static String[] getListOfFilesIn(String directory) {
        String dir = directory + File.separator;
        return new File(dir).list();
    }
}