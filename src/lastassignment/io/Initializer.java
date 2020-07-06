package lastassignment.io;

import lastassignment.Player;
import lastassignment.utils.Console;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Initializer {

    /**
     * Creates a default Java properties config file containing some default properties of the
     * player. These default properties are then written to the config file. If the file
     * does not exist, the function will create one. Otherwise, the old config file is
     * overwritten.
     *
     * @param fileName the filename of the file that should be set
     */
    public static void setPropertiesWithDefault(String fileName) {
        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("playerName", "DefaultPlayer");
        defaultProperties.setProperty("playerFistDamage", "5");
        defaultProperties.setProperty("playerMaxHealth", "15");
        defaultProperties.setProperty("playerStartingHealth", "10");
        defaultProperties.setProperty("playerStartingMoney", "10.0");

        try(FileWriter fileWriter = new FileWriter(configDirectory + File.separator + fileName + ".properties")) {
            defaultProperties.store(fileWriter, "These are the default properties of a Player");
        } catch (IOException e) {
            System.out.println("Could not write to file");
        }
    }

    /**
     * The player itself can create new default properties that are then written to a file. Works exactly like
     * setPropertiesWithDefault but with manual values.
     * @param fileName filename of the configFile
     */
    public static void createNewDefaultProperties(String fileName) {
        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties defaultProperties = new Properties();

        Console.printWithPause("Create the default values for Player:");
        Console.print("Name: ");
        String name = Console.readString();
        Console.print("Damage: ");
        String damage = Console.readString();
        Console.print("Maximum Health: ");
        String maxHealth = Console.readString();
        Console.print("Health: ");
        String health = Console.readString();
        Console.print("Money: ");
        String money = Console.readString();

        try {
            Integer.parseInt(damage);
            Integer.parseInt(maxHealth);
            Integer.parseInt(health);
            Double.parseDouble(money);
        } catch (NumberFormatException e) {
            Console.printWithPause("You typed something wrong, the config failed.");
            return;
        }

        defaultProperties.setProperty("playerName", name);
        defaultProperties.setProperty("playerFistDamage", damage);
        defaultProperties.setProperty("playerMaxHealth", maxHealth);
        defaultProperties.setProperty("playerStartingHealth", health);
        defaultProperties.setProperty("playerStartingMoney", money);

        try(FileWriter fileWriter = new FileWriter(configDirectory + File.separator + fileName + ".properties")) {
            defaultProperties.store(fileWriter, "These are the default properties of a Player");
        } catch (IOException e) {
            System.out.println("Could not write to file");
        }
    }

    /**
     * Initializes the Player from a given config/Properties file.
     *
     * @param player The Player whose properties to initialize.
     * @return The initialized Player.
     * @see Player
     */
    public static Player initPlayerFromProperties(Player player, String fileName) throws IOException {
        File configDirectory = new File("config");

        try(FileReader fileReader = new FileReader(configDirectory + File.separator + fileName + ".properties")) {
            Properties zooProperties = new Properties();
            zooProperties.load(fileReader);

            String playerName = zooProperties.getProperty("playerName");
            double playerStartingMoney = Double.parseDouble(zooProperties.getProperty("playerStartingMoney"));
            int playerFistDamage = Integer.parseInt(zooProperties.getProperty("playerFistDamage"));
            int playerMaxHealth = Integer.parseInt(zooProperties.getProperty("playerMaxHealth"));
            int playerStartingHealth = Integer.parseInt(zooProperties.getProperty("playerStartingHealth"));

            player.setName(playerName);
            player.setMoney(playerStartingMoney);
            player.setFistDamage(playerFistDamage);
            player.setMaxHealth(playerMaxHealth);
            player.setHealth(playerStartingHealth);

            return player;
        }
    }
}
