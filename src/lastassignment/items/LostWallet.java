package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

public class LostWallet extends Item {

    private double initialMoney;
    private double money;

    public LostWallet(String description, double money) {
        super(description);
        this.money = money;
        this.initialMoney = money;
    }

    @Override
    public void interact(Player player) {
        Console.printWithPause("You walk over to the %s and pick it up", getDescription());
        Console.printWithPause("Someone must have dropped it before the school closed");
        if (money == 0) {
            if (initialMoney != 0) {
                Console.printWithPause("You already took everything that was in there");
            } else {
                Console.printWithPause("There's no money in it. Just some useless cards and coupons");
            }
            Console.printWithPause("You put the wallet back on the ground");
        } else {
            Console.printWithPause("Oh, there's something in here. $%.2f to be exact!", money);

            boolean playerResponded = false;
            while (!playerResponded) {
                Console.printLine("What do you do?");
                Console.printLine("  (1) Take all the money");
                Console.printLine("  (2) Leave it - you're not a thief");
                int response = Console.readInt();
                if (response == 1) {
                    playerResponded = true;

                    Console.printWithPause("You take all of the money out of the %s and add it to your own wallet", getDescription());
                    Console.printWithPause("A sudden chill runs down your spine");
                    Console.printWithPause("");

                    player.addNaughtiness(1);
                    int naughtiness = player.getNaughtiness();

                    if (naughtiness >= 3) {
                        Console.printWithPause("A shadowy figure emerges from the darkness");
                        Console.printWithPause("You feel a nauseating pain from merely laying your eyes on it");
                        Console.printWithPause("You drop on the floor, paralyzed with fear");
                        Console.printWithPause("The figure slowly approaches");
                        Console.printWithPause("All you can do, is to let it happen");
                        Console.printWithPause("You close your eyes");
                        Console.printWithPause("");
                        Console.printWithPause("");
                        Console.printWithPause("");
                        Console.printWithPause("You feel it's presence leave the room");
                        Console.printWithPause("Surprisingly you're still in one piece");
                        Console.printWithPause("You take a few minutes to collect yourself");
                        Console.printWithPause("That was terrifying");
                        Console.printWithPause("What was that?");
                        Console.printWithPause("Were you just imagining things?");
                        Console.printWithPause("I guess so");
                        Console.printWithPause("You stand up and make like everything is fine");
                        Console.printWithPause("Oddly enough you feel slightly lighter than before");
                        player.setMoney(0);
                        player.addNaughtiness(-999);
                    } else {
                        player.setMoney(player.getMoney() + money);
                        Console.printWithPause("You feel as if you were being watched");
                        Console.printWithPause("");
                        Console.printWithPause("You now have $%.2f in your wallet", player.getMoney());
                        Console.printWithPause("You quickly leave the emptied wallet on the ground where you found it");
                    }

                    money = 0;

                } else if (response == 2) {
                    playerResponded = true;
                    Console.printWithPause("Yeah, that's probably for the best");
                    Console.printWithPause("You never know who could be watching");
                }
            }
        }
    }

}