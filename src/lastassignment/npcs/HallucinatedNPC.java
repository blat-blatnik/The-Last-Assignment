package lastassignment.npcs;

import lastassignment.Player;
import lastassignment.utils.Console;

import java.util.Random;

public class HallucinatedNPC extends NPC {

    public HallucinatedNPC() {
        super("", 1, 1, false);

        switch (new Random().nextInt(20)) {
            case 0:
                setDescription("your mother");
                break;
            case 1:
                setDescription("your father");
                break;
            case 2:
                setDescription("your programming professor");
                break;
            case 3:
                setDescription("Sparkie the dog");
                break;
            case 4:
                setDescription("Meow the infinite");
                break;
            case 5:
                setDescription("a magnificent pony");
                break;
            case 6:
                setDescription("a lost child");
                break;
            case 7:
                setDescription("a busy looking researcher");
                break;
            case 8:
                setDescription("an uncaring world");
                break;
            case 9:
                setDescription("the sun god");
                break;
            case 10:
                setDescription("a talking mug");
                break;
            case 11:
                setDescription("the humanoid blob resting on the wall");
                break;
            case 12:
                setDescription("a moving shadow");
                break;
            case 13:
                setDescription("a smiling shadow");
                break;
            case 14:
                setDescription("a disembodied voice");
                break;
            case 15:
                setDescription("a talking fish out of water");
                break;
            case 16:
                setDescription("a stone statue");
                break;
            case 17:
                setDescription("a live sized atom");
                break;
            case 18:
                setDescription("a robot buzzing by");
                break;
            case 19:
                setDescription("Tommy the fly");
                break;
        }
    }

    @Override
    public void interact(Player player) {
        super.interact(player);
        Console.printWithPause("You turn around for a brief second");
        Console.printWithPause("When you turn back %s is gone");
    }

}
