package lastassignment.roomcontents;

import lastassignment.Player;
import lastassignment.Room;
import lastassignment.items.*;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.io.Serializable;

public class FumeHood implements Interactable, Serializable {

    private static final long serialVersionUID = 42L;

    private String description;
    private boolean brewHasAcid;
    private boolean brewHasBase;
    private boolean brewHasMetal;
    private boolean brewHasOrganic;

    public FumeHood(String description, Room room) {
        this.description = description;
        brewHasAcid = false;
        brewHasBase = false;
        brewHasMetal = false;
        brewHasOrganic = true;
        room.addContents(this);
    }

    @Override
    public void interact(Player player) {

        Console.printWithPause("You walk up to the %s", description);
        Console.printWithPause("This is where those chemistry wizards mix their chemicals");
        Console.printWithPause("It looks like its still completely functional");
        Console.printWithPause("And it also looks like someone had already set up a bunch of lab equipment in here");
        Console.printWithPause("Most of this equipment is beyond you, but maybe you could try to mix something up");

        boolean playerResponded = false;
        while (!playerResponded) {
            Console.printLine("What do you do?");
            Console.printLine("  (1) Mix away!");
            Console.printLine("  (2) I value my life..");
            int selection = Console.readInt();
            switch (selection) {
                case 1:
                    playerResponded = true;
                    doPotionMixingDialog(player);
                    break;
                case 2:
                    Console.printWithPause("Best stay away from stuff like that");
                    Console.printWithPause("Even if you were a chemist in your past life");
                    playerResponded = true;
                    break;
            }
        }

    }

    private void doPotionMixingDialog(Player player) {

        Console.printWithPause("You try to piece together how this lab equipment is supposed to work");
        Console.printWithPause("But you quickly realize that it's futile");
        Console.printWithPause("You'd better just mix first and ask questions later");

        boolean playerBrewingPotion = true;
        while (playerBrewingPotion) {

            Console.print("Your mixture currently consists of ");
            if (!brewHasAcid && !brewHasBase && !brewHasMetal && !brewHasOrganic)
                Console.print("absolutely nothing");
            else {
                if (brewHasAcid)
                    Console.print("acid ");
                if (brewHasBase)
                    Console.print("base ");
                if (brewHasOrganic)
                    Console.print("organic compound ");
                if (brewHasMetal)
                    Console.print("metallic compound ");
            }
            Console.printLine("");

            boolean playerResponded = false;
            while (!playerResponded) {
                Console.printLine("What would you like to do?");
                if (!brewHasAcid)
                    Console.printLine("  (1) Add some acid");
                if (!brewHasBase)
                    Console.printLine("  (2) Add some base");
                if (!brewHasMetal)
                    Console.printLine("  (3) Add a piece of a soft metal");
                if (!brewHasOrganic)
                    Console.printLine("  (4) Add a pinch of some organic compound");
                Console.printLine("  (5) Finish your brew");
                Console.printLine("  (6) Stop playing with dangerous chemicals");

                int input = Console.readInt();

                switch (input) {
                    case 1:
                        if (!brewHasAcid) {
                            playerResponded = true;
                            Console.printWithPause("You pour a little bit of acid into your mixture");
                            Console.printWithPause("Its nice how they package the acid in cute little bottles with a lot of skulls and warnings");
                            Console.printWithPause("The mixture starts to bubble intensely as you add it in, and then settles down quickly");
                            brewHasAcid = true;
                        }
                        break;
                    case 2:
                        if (!brewHasBase) {
                            playerResponded = true;
                            Console.printWithPause("You pour a little bit of base into your mixture");
                            Console.printWithPause("The bottle holding the base is all corroded, you have to watch out");
                            Console.printWithPause("The mixture fizzes and hisses as you add the base");
                            brewHasBase = true;
                        }
                        break;
                    case 3:
                        if (!brewHasMetal) {
                            playerResponded = true;
                            Console.printWithPause("You add a piece of some metal into your mixture");
                            Console.printWithPause("The metal dissolves quickly and the mixture changes color");
                            brewHasMetal = true;
                        }
                        break;
                    case 4:
                        if (!brewHasOrganic) {
                            playerResponded = true;
                            Console.printWithPause("You add a bit of some organic compound into your mixture");
                            Console.printWithPause("It has a very, very long name. Like, stupidly long");
                            Console.printWithPause("The compound dissolves easily into the mixture");
                            brewHasOrganic = true;
                        }
                        break;
                    case 5:
                        Console.printWithPause("Well that's enough ingredients for this brew");
                        Console.printWithPause("Time to let the reactions occur");
                        Console.printWithPause("You're not really sure how to get that to happen in the optimal way");
                        Console.printWithPause("So instead you just swirl the mixture around");
                        Console.printWithPause("And then you heat it up on a hot plate for a minute or two");
                        Item brewedPotion = finishBrew();
                        player.addItemToBackpack(brewedPotion);
                        Console.printWithPause("Well, your mixture seems to have settled into a nice %s", brewedPotion.getDescription());
                        Console.printWithPause("You pour the mixture into a large test tube, and stopper it");
                        Console.printWithPause("You then put the test tube with the liquid into your backpack for later");
                        Console.printWithPause("It's probably time to stop playing with chemicals for now");
                        playerResponded = true;
                        playerBrewingPotion = false;
                        break;
                    case 6:
                        Console.printWithPause("Enough is enough. Time to get going");
                        Console.printWithPause("This is starting to get boring");
                        Console.printWithPause("You leave the fume hood for now");
                        Console.printWithPause("Your unfinished brew patiently awaits your return");
                        playerResponded = true;
                        playerBrewingPotion = false;
                        break;
                }
            }
        }

    }

    @Override
    public void inspect(Player player) {
        Console.printLine(description);
    }

    private Item finishBrew() {

        Item potion;

        if (brewHasAcid && brewHasBase && brewHasMetal && brewHasOrganic) {
            potion = new Hallucinogen("a black potion", "drink");
        } else if (brewHasAcid && brewHasBase && brewHasMetal && !brewHasOrganic) {
            potion = new HealingItem("a colorful potion", "drink", 4);
        } else if (brewHasAcid && brewHasBase && !brewHasMetal && brewHasOrganic) {
            potion = new HealingItem("a foul smelling potion", "drink", 5);
        } else if (brewHasAcid && brewHasBase && !brewHasMetal && !brewHasOrganic) {
            potion = new HealingItem("a white flaky substance", "eat", 1);
        } else if (brewHasAcid && !brewHasBase && brewHasMetal && brewHasOrganic) {
            potion = new Vaccine("a DIY vaccine");
        } else if (brewHasAcid && !brewHasBase && brewHasMetal && !brewHasOrganic) {
            potion = new StrengthPotion("a thick white potion", "drink", -2);
        } else if (brewHasAcid && !brewHasBase && !brewHasMetal && brewHasOrganic) {
            potion = new DamagingItem("a yellow tinted potion", "drink", 8);
        } else if (brewHasAcid && !brewHasBase && !brewHasMetal && !brewHasOrganic) {
            potion = new DamagingItem("a yellow tinted potion", "drink", 10);
        } else if (!brewHasAcid && brewHasBase && brewHasMetal && !brewHasOrganic) {
            potion = new Hallucinogen("a rainbow colored potion", "drink");
        } else if (!brewHasAcid && brewHasBase && !brewHasMetal && brewHasOrganic) {
            potion = new StrengthPotion("a shiny purple potion", "drink", 2);
        } else if (!brewHasAcid && brewHasBase && !brewHasMetal && !brewHasOrganic) {
            potion = new DamagingItem("a blue tinted potion", "drink", 10);
        } else if (!brewHasAcid && !brewHasBase && brewHasMetal && brewHasOrganic) {
            potion = new VirusMedicine("a blood red potion", 2);
        } else if (!brewHasAcid && !brewHasBase && brewHasMetal && !brewHasOrganic) {
            potion = new StrengthPotion("a green potion", "drink", -5);
        } else if (!brewHasAcid && !brewHasBase && !brewHasMetal && brewHasOrganic) {
            potion = new Hallucinogen("a stinky potion", "drink");
        } else if (!brewHasAcid && !brewHasBase && !brewHasMetal && !brewHasOrganic) {
            potion = new HealingItem("distilled water in a test tube", "drink", 0);
        } else {
            potion = new HealingItem("distilled water in a test tube", "drink", 0);
        }

        brewHasAcid = false;
        brewHasBase = false;
        brewHasOrganic = false;
        brewHasMetal = false;

        return potion;
    }

}