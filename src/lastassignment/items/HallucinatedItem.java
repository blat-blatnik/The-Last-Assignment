package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

import java.util.Random;

public class HallucinatedItem extends Item {

    public HallucinatedItem() {
        super("");

        switch (new Random().nextInt(20)) {
            case 0:
                setDescription("suspiciously placed bottle opener");
                break;
            case 1:
                setDescription("strange looking machine");
                break;
            case 2:
                setDescription("powered-on laptop");
                break;
            case 3:
                setDescription("closed laptop with a plugged in charger");
                break;
            case 4:
                setDescription("impossible shape");
                break;
            case 5:
                setDescription("completely transparent yet still visible box");
                break;
            case 6:
                setDescription("strange looking potion");
                break;
            case 7:
                setDescription("vaccine");
                break;
            case 8:
                setDescription("fat wallet");
                break;
            case 9:
                setDescription("map to the underworld");
                break;
            case 10:
                setDescription("tome of infinite knowledge");
                break;
            case 11:
                setDescription("out of place desk");
                break;
            case 12:
                setDescription("shiny mirror");
                break;
            case 13:
                setDescription("moving lamp");
                break;
            case 14:
                setDescription("glowing mushroom");
                break;
            case 15:
                setDescription("humming plant");
                break;
            case 16:
                setDescription("shimmering rope");
                break;
            case 17:
                setDescription("solid gold watch");
                break;
            case 18:
                setDescription("bear trap");
                break;
            case 19:
                setDescription("strange doll");
                break;
        }
    }

    @Override
    public void interact(Player player) {
        Console.printWithPause("You walk up to %s and try to touch it", getDescription());
        Console.printWithPause("But then you realize that it was never there to begin with");
        Console.printWithPause("You have come to an important realization");
        Console.printWithPause("The world makes more sense to you now");
    }

}
