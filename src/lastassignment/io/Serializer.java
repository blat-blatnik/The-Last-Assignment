package lastassignment.io;

import lastassignment.Player;
import lastassignment.utils.Console;

import java.io.*;

/**
 * @author Jana
 * @version 1.0
 *
 * A class containing static methods to call in order to save and load a current state of Player.
 */
public class Serializer {

    /**
     * Saves the state of the Player to a given filename.
     * The given filename is prepended with "savedgames/", so
     * all save files are saved in the same directory. The user
     * is notified if a problem occurs during saving.
     *
     * @param player The state of Player that needs to be saved.
     * @param filename The filename to save the Player state to.
     */
    public static void savePlayer(Player player, String filename) {
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir();

        Console.print("Saving %s.. ", filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + File.separator + filename + ".ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(player);
            Console.printWithPause("Save successful!");
        } catch (FileNotFoundException e) {
            Console.printWithPause("File could not be found");
            Console.printWithPause("Game not saved!");
        } catch (IOException e) {
            Console.printWithPause("Couldn't write to file");
            Console.printWithPause("Game not saved!");
        }
    }

    /**
     * Loads the state of the Game from a given filename. The filename
     * is prepended with "savedgames/". The user is notified if a
     * problem occurs during loading.
     *
     * @param filename The filename from which to load the Game state.
     */
    public static Player loadPlayer(String filename) throws IOException, ClassNotFoundException{

        Console.printLine("Loading %s.. ", filename);
        File saveDirectory = new File("savedgames");

        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + filename + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Player)objectInputStream.readObject();
        }
    }
}
