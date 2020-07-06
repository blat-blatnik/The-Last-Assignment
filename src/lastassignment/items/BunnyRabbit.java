package lastassignment.items;

import lastassignment.Player;
import lastassignment.utils.Console;

/**
 * @author Boris
 * @version 1.0
 *
 * Represents a bunny rabbit that the player can rescue and carry around in their bag.
 * The player can cuddle the bunny in order to decrease their naughtiness level.
 */
public class BunnyRabbit extends Item {

    private int conversationPointer;
    private String name;

    /**
     * Initializes the BunnyRabbit.
     *
     * @param name The rabbit's name.
     */
    public BunnyRabbit(String name) {
        super(name + " the rabbit");
        this.name = name;
        conversationPointer = 0;
    }

    /**
     * @return The Rabbits name.
     */
    public String getName() {
        return name;
    }

    /**
     * Lets the player interact with a BunnyRabbit.
     * The player can pet the bunny to decrease their naughtiness or release it into the wild.
     *
     * @param player current player
     * @see Player
     */
    @Override
    public void interact(Player player) {

        if (!player.hasItem(this))
            throw new RuntimeException("Player interacted with bunny rabbit not in their inventory");

        Console.printWithPause("You take a look at the rabbit");
        Console.printWithPause("He's comfortably sitting in your backpack, seemingly contempt");

        boolean conversationFinished = false;
        while (!conversationFinished) {

            Console.printLine("What would you like to do?");
            Console.printLine("  (1) Talk to the bunny");
            Console.printLine("  (2) Pet the bunny");
            Console.printLine("  (3) Cuddle the bunny");
            Console.printLine("  (4) Hug the bunny");
            Console.printLine("  (5) Release the bunny");
            Console.printLine("  (0) Leave the bunny to sleep");

            int input = Console.readInt();
            switch (input) {
                case 0:
                    conversationFinished = true;
                    Console.printWithPause("You leave the bunny be - comfortably sleeping in your backpack");
                    break;
                case 1:
                    Console.printWithPause("You start talking to the bunny rabbit");
                    switch (conversationPointer) {
                        case 0:
                            Console.printWithPause("\"You're the cutest little thing ever\"");
                            Console.printWithPause("\"Aren't you?\"");
                            Console.printWithPause("\"Areeenttt youuuu?\"");
                            break;
                        case 1:
                            Console.printWithPause("\"You know, sometimes I wonder why I even bother with these assignment\"");
                            Console.printWithPause("\"Maybe I should have just left it and stayed at home\"");
                            Console.printWithPause("\"So what if I fail this one subject\"");
                            Console.printWithPause("The bunny stares at you intently");
                            Console.printWithPause("\"Oh you don't know the half of it bunny\"");
                            break;
                        case 2:
                            Console.printWithPause("\"Sometimes I really wish I was some kind of pet like you\"");
                            Console.printWithPause("\"You just get fed, cuddled, loved, and nobody expects anything from you\"");
                            Console.printWithPause("\"I'm sure I'd miss doing human things but still\"");
                            Console.printWithPause("\"Doesn't seem like a terrible trade\"");
                            Console.printWithPause("\"Maybe if I could do it for a year or so, and then go back\"");
                            break;
                        case 3:
                            Console.printWithPause("\"I wish my parents weren't so tough on me\"");
                            Console.printWithPause("\"Nyeee you have to be top of the class, otherwise you'll be a nobody\"");
                            Console.printWithPause("\"Nyeeeee who cares that you're wasting your youth and stressing like crazy\"");
                            Console.printWithPause("\"It will all be worth it when you're 60\"");
                            Console.printWithPause("\"Assholes\"");
                            break;
                        case 4:
                            Console.printWithPause("\"God I wish my lab partner actually helped with this assignment like he was supposed to\"");
                            Console.printWithPause("\"Then maybe I wouldn't be in this mess\"");
                            Console.printWithPause("\"How come I always get stuck with partners that don't do anything\"");
                            Console.printWithPause("\"It doesn't even make any sense - because all of my friends and acquaintances have the same problem\"");
                            Console.printWithPause("\"It can't possibly be true that 90%% of students don't do anything, right?\"");
                            break;
                    }
                    conversationPointer += 1;
                    conversationPointer %= 5;
                    Console.printWithPause("The bunny looks at you with dead eyes");
                    Console.printWithPause("It has no idea what's going on");
                    Console.printWithPause("But it's a pretty good listener");
                    break;
                case 2:
                    Console.printWithPause("You start petting the bunny rabbit");
                    Console.printWithPause("You pet it's head");
                    Console.printWithPause("You pet it's fluffy little ears");
                    Console.printWithPause("You scratch it's back a bit");
                    Console.printWithPause("The bunny seems completely indifferent to your petting though");
                    player.addNaughtiness(-1);
                    player.setHealth(player.getHealth() + 1);
                    break;
                case 3:
                    Console.printWithPause("You start cuddling the cute little bunny rabbit");
                    Console.printWithPause("You cuddle it for minutes on end");
                    Console.printWithPause("It reacts about as much as a fluffy plush toy");
                    player.addNaughtiness(-1);
                    player.setHealth(player.getHealth() + 1);
                    break;
                case 4:
                    Console.printWithPause("You lift the bunny out of your backpack and hug it as hard as you can");
                    Console.printWithPause("- as hard as you can without squeezing it to death that is");
                    Console.printWithPause("The bunny doesn't really react");
                    Console.printWithPause("If anything it seems kind of annoyed that it's peaceful sleep was disturbed");
                    player.addNaughtiness(-1);
                    player.setHealth(player.getHealth() + 1);
                    break;
                case 5:
                    Console.printWithPause("You think about releasing the rabbit back into the wild");
                    Console.printWithPause("Well - not the wild, but the school building will have to do");
                    Console.printWithPause("Then again you would probably miss the little fur ball a lot if you did that");
                    Console.printWithPause("Even if he's more of a stress toy than anything else");
                    Console.printLine("Are you sure you want to release the rabbit?");
                    Console.printLine("  (Y)es");
                    Console.printLine("  (N)o");
                    String choice = Console.readInput();
                    if (choice.equals("Y")) {
                        Console.printWithPause("You take a deep breath");
                        Console.printWithPause("Determined, you take %s the rabbit out of your bag", name);
                        Console.printWithPause("Your hand motions are slow, but deliberate");
                        Console.printWithPause("You give %s one last hug, before putting him down on the ground", name);
                        Console.printWithPause("The rabbit takes a couple of seconds to register the change in it's surroundings");
                        Console.printWithPause("\"Go on\"");
                        Console.printWithPause("It seems very confused about it's new situation");
                        Console.printWithPause("\"Come on, don't drag this out you little fluff ball\"");
                        Console.printWithPause("Finally, the rabbit realizes that it's free");
                        Console.printWithPause("It hops away from you joyfully");
                        Console.printWithPause("\"I'll miss you %s!\"", name);
                        Console.printWithPause("The rabbit doesn't even look back");
                        Console.printWithPause("He hops around a corner and out of your sight");
                        Console.printWithPause("You continue your journey as well - with a tear in your eye");
                        conversationFinished = true;
                        player.addNaughtiness(-999);
                        player.removeItemFromBackpack(this);
                    } else {
                        Console.printWithPause("You decide against it");
                        Console.printWithPause("Of course you did");
                        Console.printWithPause("You NEED this rabbit with you on your journey");
                        Console.printWithPause("He is a key item");
                    }

                    break;
            }

        }
    }

}