package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

public class Hallucinogen extends Item {

    private final String ingestionMethod;

    public Hallucinogen(String description, String ingestionMethod) {
        super(description);
        this.ingestionMethod = ingestionMethod;
    }

    @Override
    public void interact(Player player) {
        Console.printWithPause("You %s the %s", ingestionMethod, getDescription());
        Console.printWithPause("It tastes very bitter, with a terrible aftertaste");
        Console.printWithPause("But it doesn't really seem to do much else");

        if (player.isHallucinating())
            player.setHallucinating(false);
        else
            player.setHallucinating(true);
    }

}
