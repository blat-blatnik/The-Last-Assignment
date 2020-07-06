package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

public class StrengthPotion extends Item {

    private final String ingestionMethod;
    private int damageModifier;

    public StrengthPotion(String description, String ingestionMethod, int damageModifier) {
        super(description);
        this.ingestionMethod = ingestionMethod;
        this.damageModifier = damageModifier;
    }

    @Override
    public void interact(Player player) {
        Console.printWithPause("You %s the %s", ingestionMethod, getDescription());
        Console.printWithPause("It leaves you with a terrible aftertaste that you just can't seem to shake off");
        if (damageModifier > 0) {
            Console.printWithPause("Your muscles feel tense");
            Console.printWithPause("Like they are ready for anything you can throw at them");
        } else if (damageModifier < 0) {
            Console.printWithPause("Your muscles start feeling limp");
            Console.printWithPause("You can barely support the weight of your own body");
        }
        player.setDamageModifier(damageModifier);
    }

}
