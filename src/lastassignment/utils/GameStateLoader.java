package lastassignment.utils;

import lastassignment.Game;
import lastassignment.Player;
import lastassignment.io.Serializer;

import java.io.IOException;

/**
 * @author Jana
 * @version 1.0
 *
 * A class providing a static method to load a specific state of Player to load.
 */
public class GameStateLoader {

    /**
     * Loads a specific state of Player that is saved. To do that it calls the important methods of Serializer.
     * @param game The game it will be loaded into.
     * @param filename The filename of the state that should be loaded.
     */
    public static void loadFrom(Game game, String filename){
        Player player;
        try {
            player = Serializer.loadPlayer(filename);
            game.setPlayer(player);
        } catch (IOException e) {
            System.out.println("Could not load from the file");
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load the state of Player");
        }
    }
}
