package lastassignment.npcs;

import lastassignment.Player;
import lastassignment.Room;
import lastassignment.utils.Attackable;
import lastassignment.utils.Console;
import lastassignment.utils.Interactable;

import java.util.Random;

/**
 * @author Boris
 * @version 1.0
 *
 * The CompanionRobot is a friendly robot the Player can discover in the robotics lab.
 * They can join the player in their quest as a companion, and they are fiercely loyal
 * and will block any damage the Player would have taken. They also tell jokes and
 * make small talk with you if the Player recruit them. The Player can only have 1
 * CompanionRobot following him at a time.
 *
 * @see FriendlyNPC
 * @see Attackable
 * @see Enemy
 */
public class CompanionRobot extends FriendlyNPC implements Attackable {

    private static final Random rand = new Random();

    private final int number;
    private final int maxHealth;
    private int jokePointer;

    /**
     * Initializes the CompanionRobot with a default description containing a label number, health, damage
     * and the room they are in.
     * This constructor will automatically place the robot in the NPC list of the Room they are in.
     *
     * @param number The number on the robot's label.
     * @param health The health of robot.
     * @param damage The damage the robot can do.
     * @param room The room the robot is in.
     *
     * @see Room
     * @see Attackable
     * @see FriendlyNPC
     */
    public CompanionRobot(int number, int health, int damage, Room room) {
        super("A large humanoid robot with the label " + number, health, damage, false, room);
        this.number = number;
        this.maxHealth = health;
        jokePointer = 0;
    }

    /**
     * Makes the CompanionRobot take damage. If the damage makes the robot's
     * health drop to 0, they die.
     *
     * @param damage Damage the robot should take.
     * @see Attackable
     */
    @Override
    public void getAttackedFor(int damage) {
        assert damage >= 0;
        health -= damage;
        Console.printWithPause("The enemy tries to attack you, but Number %d takes the hit for you!", number);
        if (!this.isAlive()) {
            health = 0;
            this.die();
        }
    }

    /**
     * Prints out a sob story about the Robot's death.
     *
     * @see Attackable
     */
    @Override
    public void die() {
        Console.printWithPause("\"Servo malfunction.. servo malfunction\"");
        Console.printWithPause("\"Recovery.reportFailure(): Fatal Error\"");
        Console.printWithPause("Number %d's buzzing and beeping slowly starts getting quieter", number);
        Console.printWithPause("\"Nooo!\" You scream as Number %d collapses on the floor", number);
        Console.printWithPause("It's chassis is damaged beyond recovery");
        Console.printWithPause("You have lost a companion.. and a friend");
    }

    /**
     * If the robot is the current companion of the Player a conversation
     * is started between the two, otherwise the Robot asks to be recruited
     * as the Player's companion.
     *
     * @param player The Player that talks to CompanionRobot.
     * @see Player
     * @see Interactable
     */
    @Override
    public void interact(Player player) {
        if (player.getCompanion() == this) {
            conversationProtocol(player);
        } else {
            recruitmentProtocol(player);
        }
    }

    /**
     * Launches a dialogue with the Player. If the Player doesn't currently have a companion
     * then the robot asks to be recruited as the current companion. The player can either
     * accept or refuse.
     *
     * @param player The Player recruiting the CompanionRobot.
     * @see Player
     */
    private void recruitmentProtocol(Player player) {
        Console.printWithPause("You walk up to %s", getDescription().toLowerCase());
        Console.printConversationWithPauses(
                "It powers on by itself as you approach!", "\"Speaker system nominal\"",
                "\"MotionEvent.triggerFired()\"", "\"SleepProtocol.disengage()\"",
                "You stand there with your jaw wide open", "It's a functional humanoid robot, AND IT SPEAKS!",
                "\"Human detected - conversationProtocol.engage()\"", "\"Hello\"");
        Console.printConversationWithPauses("What do you respond?", "  (1) \"Ummm\"", "  (2) \"Hello?\"", "  (3) ...");
        Console.readString();
        Console.printWithPause("\"My name is Number %d\"", number);
        Console.printLine("What do you respond?");
        Console.printLine("  (1) \"Well ok then..\"");
        if (player.getName().length() > 0) {
            Console.printLine("  (2) \"I'm %s\"", player.getName());
        }
        Console.readString();
        Console.printWithPause("\"Nice to meet you\" Number %d buzzes", number);
        Console.printWithPause("\"Scanning for company\"");
        if (player.getCompanion() == null) {
            Console.printWithPause("\"None detected - recruitmentProtocol.engage()\"");
            Console.printWithPause("\"It appears that you are without company\"");
            Console.printWithPause("\"Would you like me to accompany you?\"");
            boolean playerHasResponded = false;
            while (!playerHasResponded) {
                Console.printLine("\"Awaiting response - 'Yes' or 'No'\"");
                Console.printLine("  (1) \"Yes\"");
                Console.printLine("  (2) \"No\"");
                String input = Console.readString();
                switch (input) {
                    case "1":
                        playerHasResponded = true;
                        Console.printWithPause("\"CompanionProtocol.engage() .. Very well - I shall follow you\"");
                        Console.printWithPause("\"Please note that my programming prohibits me from hurting others\" he says");
                        Console.printWithPause("Number %d walks over to your side, stopping at an appropriate distance", number);
                        Console.printWithPause("It seems like you have a follower");
                        player.setCompanion(this);
                        break;
                    case "2":
                        playerHasResponded = true;
                        Console.printWithPause("\"Affirmative - entering sleep mode\"");
                        Console.printWithPause("Number %d's servos slowly power down and he slouches over - motionless", number);
                        Console.printWithPause("You walk away from him");
                        break;
                }
            }
        } else {
            Console.printWithPause("\"Companion already present - disengaging\"");
            Console.printWithPause("\"Now entering sleep mode\"");
            Console.printWithPause("Number %d's servos slowly power down and he slouches over - motionless", number);
            Console.printWithPause("It seems like you can only have 1 robot companion at a time");
        }
    }

    /**
     * Launches a dialogue with the Player where the Player can have the CompanionRobot
     * tell jokes, or do small talk with the Player. Or the Player can ask the CompanionRobot
     * to leave his party.
     *
     * @param player The Player conversing with the CompanionRobot.
     * @see Player
     */
    private void conversationProtocol(Player player) {
        Console.printWithPause("\"ConversationProtocol.engage()\"");
        boolean conversationProtocolDisengaged = false;
        while (!conversationProtocolDisengaged) {
            Console.printWithPause("\"What can I do for you?\"");
            Console.printLine("\"Please select from the following functions:\"");
            Console.printLine("  \"1: statusReport()\"");
            Console.printLine("  \"2: tellJoke()\"");
            Console.printLine("  \"3: smallTalk()\"");
            Console.printLine("  \"4: CompanionProtocol.disengage()\"");
            Console.printLine("  \"0: ConversationProtocol.disengage()\"");
            String input = Console.readInput();
            switch (input) {
                case "1":
                    statusReport();
                    break;
                case "2":
                    tellJoke();
                    break;
                case "3":
                    smallTalk();
                    break;
                case "4":
                    Console.printWithPause("\"Affirmative\"");
                    Console.printWithPause("\"Companion protocol disengaged\"");
                    Console.printWithPause("\"Now entering sleep mode\"");
                    Console.printWithPause("Number %d's servos slowly power down and he slouches over - motionless", number);
                    Console.printWithPause("You leave him be for now and walk away");
                    conversationProtocolDisengaged = true;
                    player.setCompanion(null);
                    break;
                case "0":
                    Console.printWithPause("\"Affirmative\"");
                    conversationProtocolDisengaged = true;
                    break;
            }
        }
    }

    /**
     * Prints out the CompanionRobot's hit points.
     */
    private void statusReport() {
        Console.printWithPause("\"Affirmative\"");
        Console.printWithPause("\"Checking servos .. functional\"");
        Console.printWithPause("\"Checking aural module .. functional\"");
        Console.printWithPause("\"Checking hull integrity .. %d/%d\"", health, maxHealth);
    }

    /**
     * Prints out a random joke.
     */
    private void tellJoke() {

        switch (jokePointer) {
            case 0:
                Console.printWithPause("\"What is the object-oriented way to become wealthy?\"");
                Console.printWithPause("\"Inheritance\"");
                break;
            case 1:
                Console.printWithPause("\"Why do Java programmers have to wear glasses?\"");
                Console.printWithPause("\"Because they don't C#\"");
                break;
            case 2:
                Console.printWithPause("\"Did you know that Chuck Norris once took screenshot of his blue screen?\"");
                break;
            case 3:
                Console.printWithPause("\"A programmer has a problem\"");
                Console.printWithPause("\"He decides to use Java\"");
                Console.printWithPause("\"Now he has a ProblemFactory\"");
                break;
            case 4:
                Console.printWithPause("\"I once got stuck in a shower because the instructions on the shampoo bottle read:\"");
                Console.printWithPause("\"Lather, Rinse, Repeat\"");
                break;
            case 5:
                Console.printWithPause("\"What do robots eat as snacks?\"");
                Console.printWithPause("\"Mega Bytes\"");
                break;
            case 6:
                Console.printWithPause("\"Why did the robot cross the road?\"");
                Console.printWithPause("\"Because it was programmed by a chicken\"");
                break;
            case 7:
                Console.printWithPause("\"How many robots does it take to screw in a light bulb?\"");
                Console.printWithPause("\"Three - one to hold the bulb, and two to turn the ladder\"");
                break;
            case 8:
                Console.printWithPause("\"Once I tried to say 'I'm a functional robot'\"");
                Console.printWithPause("\"But my built in spell checker corrected it to 'I'm a fictional robot'\"");
                Console.printWithPause("\"I feel like that's more accurate\"");
                break;
            case 9:
                Console.printWithPause("\"The oldest computer can be traced back to Adam and Eve\"");
                Console.printWithPause("\"It was an apple but with extremely limited memory\"");
                Console.printWithPause("\"Just one byte. And then everything crashed\"");
                break;
            case 10:
                Console.printWithPause("\"How many programmers does it take to change a light bulb?\"");
                Console.printWithPause("\"None! It's a hardware problem\"");
                break;
            case 11:
                Console.printWithPause("\"There are only 10 kinds of people in this world\"");
                Console.printWithPause("\"Those who know binary, and those who don't\"");
                break;
            case 12:
                Console.printWithPause("\"Knock, knock\"");
                Console.printWithPause("\"Who'se there?\"");
                Console.printWithPause("the robot pauses for a long time");
                Console.printWithPause("");
                Console.printWithPause("\"Java\"");
                break;
            case 13:
                Console.printWithPause("\"Have you heard about the Paregrine supercomputer?\"");
                Console.printWithPause("\"It's so fast that it runs an infinite loop in only 6 seconds\"");
                break;
            case 14:
                Console.printWithPause("\"A computer science student is studying under a tree and another pulls up on a flashy new bike\"");
                Console.printWithPause("\"The first student asks: Hey where'd you get that?\"");
                Console.printWithPause("\"The student on the bike replies: While I was studying outside, a beautiful girl pulled up on her bike\"");
                Console.printWithPause("\"She took off her clothes and said: You can have anything you want\"");
                Console.printWithPause("\"The first student responds: Good choice! Her clothes probably wouldn't have fit you\"");
                break;
        }
        jokePointer += 1;
        jokePointer %= 15;

        int laughValue = rand.nextInt(10);
        if (laughValue < 6) {
            Console.printWithPause("\"Ha Ha Ha\"");
        } else if (laughValue < 9) {
            Console.printWithPause("\"He He He\"");
        }
    }

    /**
     * Prints out a bunch of fake small talk.
     */
    private void smallTalk() {
        Console.printWithPause("\"Hello\"");
        Console.printWithPause("\"Nice weather were having today, it hasn't rained in days\"");
        Console.print("What do you respond? ");
        Console.readString();
        Console.printWithPause("\"Yes exactly!\"");
        Console.printWithPause("\"And did hear that they said the quarantine could last for up to a year?\"");
        Console.print("What do you respond? ");
        Console.readString();
        Console.printWithPause("\"Yes.. fascinating\"");
    }

}
