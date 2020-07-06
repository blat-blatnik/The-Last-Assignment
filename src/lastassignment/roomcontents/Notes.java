package lastassignment.roomcontents;

import lastassignment.utils.Inspectable;
import lastassignment.Player;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.io.Serializable;

/**
 * @author Boris
 * @version 1.0
 *
 * Represents notes that the player can read which contain tidbits of story, recipes, etc.
 */
public class Notes implements Interactable, Serializable {

    private static final long serialVersionUID = 42;

    private final String description;
    private final String[] lines;

    /**
     * Constructs Notes from a given description and list of story lines.
     *
     * @param description The description of the notes.
     * @param lines The story lines that the player reads when interacting with the notes.
     */
    public Notes(String description, String... lines) {
        this.description = description;
        this.lines = lines;
    }

    /**
     * Prints the description of the Notes.
     *
     * @param player The Player inspecting the Notes.
     * @see Player
     * @see Inspectable
     */
    @Override
    public void inspect(Player player) {
        Console.printLine(description);
    }

    /**
     * Prints out all of the story lines contained in these Notes to the player.
     *
     * @param player The Player interacting with the Notes.
     * @see Player
     * @see Inspectable
     */
    @Override
    public void interact(Player player) {
        Console.printConversationWithPauses(lines);
    }

}