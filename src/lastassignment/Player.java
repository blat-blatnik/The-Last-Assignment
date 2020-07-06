package lastassignment;

import lastassignment.doors.Door;
import lastassignment.items.Clothes;
import lastassignment.items.Item;
import lastassignment.items.Map;
import lastassignment.items.Weapon;
import lastassignment.npcs.Shopkeeper;
import lastassignment.roomcontents.VendingMachine;;
import lastassignment.npcs.CompanionRobot;
import lastassignment.utils.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Boris
 * @author Jana
 * @version 9001.0
 *
 * Represents the player's character.
 *
 * @see Inspectable
 * @see Attackable
 * @see Infectable
 * @see Serializable
 * @see Room
 * @see Door
 * @see CompanionRobot
 * @see Clothes
 * @see Weapon
 */
public class Player implements Inspectable, Attackable, Infectable, Serializable {
    /**
     * The name of the player before they introduces themselves in the "default" configuration.
     */
    public static final String DEFAULT_NAME = "";

    /**
     * The amount of money the player starts with in the "default" configuration.
     * @see Shopkeeper
     * @see VendingMachine
     */
    public static final double DEFAULT_STARTING_MONEY = 10.0;

    /**
     * The maximum amount of health a player can have in the "default" configuration.
     * @see Attackable
     */
    public static final int DEFAULT_MAX_HEALTH = 10;

    /**
     * The amount of damage the player does with no weapon equipped in the "default configuration".
     * @see Weapon
     */
    public static final int DEFAULT_FIST_DAMAGE = 2;

    /**
     * The amount of health the player starts the game with in the "default configuration".
     * @see Attackable
     */
    public static final int DEFAULT_STARTING_HEALTH = DEFAULT_MAX_HEALTH;

    /**
     * The amount of damage gained or lost when the player is infected.
     * Damage is capped to a minimum of 1.
     * @see Infectable
     */
    private static final int INFECTED_DAMAGE_MOD = -2;

    private static final long serialVersionUID = 42L;

    private String name;
    private Room currentRoom;
    private int health;
    private int maxHealth;
    private int fistDamage;
    private int damageModifier;
    private int naughtiness;
    private double money;
    private Clothes currentOutfit;
    private Weapon currentWeapon;
    private CompanionRobot companion;
    private final List<Item> inventory;

    private boolean hasCompletedAssignment;
    private boolean hasWonGame;
    private boolean isInfected;
    private boolean isImmune;
    private boolean wasAskedForName;
    private boolean isHallucinating;

    /**
     * Initialises a player in a given starting Room.
     *
     * @param startingRoom The Room the player starts the game in.
     * @see Room
     */
    public Player(Room startingRoom) {
        //NOTE(Boris): Initially we don't know the player's name ..
        //             we only discover it later when they introduce themselves to an NPC.
        this.currentRoom = startingRoom;
        name = DEFAULT_NAME;
        health = DEFAULT_STARTING_HEALTH;
        maxHealth = DEFAULT_MAX_HEALTH;
        fistDamage = DEFAULT_FIST_DAMAGE;
        money = DEFAULT_STARTING_MONEY;
        inventory = new ArrayList<>();
        hasCompletedAssignment = false;
        hasWonGame = false;
        isInfected = false;
        isImmune = false;
        currentOutfit = null;
        currentWeapon = null;
        wasAskedForName = false;
        isHallucinating = false;
        companion = null;
        damageModifier = 0;
        naughtiness = 0;
    }

    /**
     * The player inspects themselves. This prints out hit points
     * money in the wallet, infection status, and other Player properties.
     *
     * @param player The player to inspect.
     */
    @Override
    public void inspect(Player player) {
        if (name.length() > 0)
            Console.printWithPause("You are " + getName() + " and you have " + getHealth() + "/" + getMaxHealth() + " hit points");
        else
            Console.printWithPause("You have " + getHealth() + "/" + getMaxHealth() + " hit points");

        Console.printWithPause("You have $%.2f in your wallet", getMoney());

        if (isInfected())
            Console.printWithPause("You are infected");
        else
            Console.printWithPause("You are not infected");

        if (getCurrentOutfit() != null)
            Console.printWithPause("You are wearing %s", getCurrentOutfit().getDescription());

        if (getCurrentWeapon() != null)
            Console.printWithPause("You are carrying a %s", getCurrentWeapon().getDescription());
        else
            Console.printWithPause("You are not carrying any weapons at the moment, apart from your fists presumably");
    }

    /**
     * Get the damage of the player. The damage depends on whether the player is infected or not
     * as well as whether they have a weapon equipped.
     *
     * @return The damage that the player would do with an attack.
     */
    public int getDamage() {
        int damage = this.fistDamage;

        Weapon weapon = getCurrentWeapon();
        if (weapon != null)
            damage = weapon.getDamage();
        damage += damageModifier;

        if (isInfected) {
            damage += INFECTED_DAMAGE_MOD;
        }

        if (damage < 1)
            damage = 1; //NOTE(Boris): Minimum of 1 damage.

        return damage;
    }

    /**
     * The Player dies and the game ends shortly thereafter.
     */
    @Override
    public void die(){
        if (health > 0) {
            health = 0;
            Console.printWithPause("You gave up on your dreams");
            Console.printWithPause("You slowly waddle yourself back home knowing full well that you've fail the assignment");
        } else {
            Console.printWithPause("Everything starts fading away");
            Console.printWithPause("You have been bested");
        }
        Game.loseGame();
    }

    /**
     * Player gets attacked for a certain amount of damage. If the player has a RobotCompanion,
     * he will block the damage. If the player looses all of their health, they die.
     *
     * @param damage damage that the player is attacked for.
     */
    @Override
    public void getAttackedFor(int damage) {
        assert damage >= 0;

        if (companion != null) {
            companion.getAttackedFor(damage);
            if (!companion.isAlive()) {
                currentRoom.removeNPC(companion);
                companion = null;
            }
        } else {
            health -= damage;
            Console.printWithPause("The enemy retaliates for %d hit points, bringing you down to %d hit points!",
                    damage, health);
            if (!this.isAlive()) {
                health = 0;
                this.die();
            }
        }
    }

    public void getDamagedFor(int damage) {
        assert damage >= 0;

        health -= damage;
        Console.printWithPause("You take %d damage, bringing you down to %d hit points! ", damage, health);
        if (!this.isAlive()) {
            health = 0;
            this.die();
        }
    }

    public void setDamageModifier(int modifier) {
        damageModifier = modifier;
    }

    public boolean isHallucinating() {
        return this.isHallucinating;
    }

    public void setHallucinating(boolean value) {
        this.isHallucinating = value;
    }

    /**
     * @return true iff the Player is infected
     * @see Infectable
     */
    @Override
    public boolean isInfected() {
        return isInfected;
    }

    /**
     * Infects the Player with the virus, unless they are immune.
     * @see Infectable
     */
    @Override
    public void getInfected() {
        Clothes clothes = getCurrentOutfit();
        if (!isImmune && (clothes == null || !clothes.offersVirusProtection())) {
            isInfected = true;
            Console.printWithPause("Damn, you got too close to an infected");
            Console.printWithPause("Looks like you've contracted the virus");
            Console.printWithPause("You feel very weak");
            health -= 2;
            if (health <= 0 ) {
                health = 0;
                Console.printWithPause("Suddenly you collapse");
                this.die();
            } else {
                Console.printWithPause("Your health is lowered to " + health + " hit points");
                Console.printWithPause("You should keep in mind that you can infect others!");
                Console.printWithPause("It's time to practice social distancing");
            }
        }
    }

    /**
     * Makes the Player immune to the virus.
     * @see Infectable
     */
    public void becomeImmune() {
        isInfected = false;
        isImmune = true;
        Console.printWithPause("You are now immune to the virus!");
    }

    /**
     * @return true iff the Player is immune to the virus.
     */
    public boolean isImmune(){
        return this.isImmune;
    }

    /**
     * Heals the Player by a certain number of hit points.
     * @param amount Positive number of health points the player heals for.
     */
    public void heal(int amount) {
        assert amount >= 0;
        health += amount;
        if (health > maxHealth)
            health = maxHealth;
    }

    /**
     * Makes a purchase of an item for a price.
     *
     * @param item Item to be bought.
     * @param price Price of that item.
     * @see Item
     * @see Shopkeeper
     */
    public void purchase(Item item, double price) {
        assert getMoney() >= price;
        addItemToBackpack(item);
        money -= price;
    }

    /**
     * @return Player's health points.
     * @see Attackable
     */
    @Override
    public int getHealth() {
        return health;
    }


    /**
     * @return True iff the Player has at least 1 health point.
     * @see Attackable
     */
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * @return List of Items in the backpack/inventory of the Player.
     */
    public List<Item> getItemsInBackpack() {
        return inventory;
    }

    /**
     * Checks whether Player has a certain item in their inventory.
     *
     * @param item Item to look for.
     * @return true iff the item is present in the inventory of the Player.
     */
    public boolean hasItem(Item item) {
        return inventory.contains(item);
    }

    /**
     * Adds item to Player's .
     * @param item item that is added
     */
    public void addItemToBackpack(Item item) {
        inventory.add(item);
    }

    /**
     * Removes item from inventory.
     * @param item item that is removed
     */
    public void removeItemFromBackpack(Item item) {
        inventory.remove(item);
    }

    /**
     * Gets the map from the inventory.
     * @return map
     * @see Map
     */
    public Map getMap() {
        for (Item item : inventory) {
            if (item instanceof Map) {
                return (Map)item;
            }
        }
        return null;
    }

    /**
     * Checks whether player has map.
     * @return true if map in inventory, false if not
     * @see Map
     */
    public boolean hasMap() {
        return getMap() != null;
    }

    /**
     * Gets the room the player is currently in.
     * @return current room
     * @see Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Lets the player change rooms.
     * @param room new room
     * @see Room
     */
    public void setCurrentRoom(Room room) {

        if (companion != null) {
            currentRoom.removeNPC(companion);
            room.addNPC(companion);
        }

        currentRoom = room;
    }

    /**
     * Gets the Player's robot companion.
     *
     * @return robot companion
     * @see CompanionRobot
     */
    public CompanionRobot getCompanion() {
        return companion;
    }

    /**
     * Sets the companion of the player.
     * @param companion companion the player has from now on
     * @see CompanionRobot
     */
    public void setCompanion(CompanionRobot companion) {
        assert this.companion == null;
        this.companion = companion;
    }

    /**
     * @return The player's current naughtiness level.
     */
    public int getNaughtiness() {
        return naughtiness;
    }

    /**
     * Increases or decreases the player's naughtiness level.
     * @param amount
     */
    public void addNaughtiness(int amount) {
        naughtiness += amount;
    }

    /**
     * Checks whether the player was already asked for their name.
     * @return true if yes, false if no
     */
    public boolean wasAskedForName() {
        return wasAskedForName;
    }

    /**
     * Gets the player's name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
        wasAskedForName = true;
    }

    /**
     * Gets the player's outfit.
     * @return current outfit
     * @see Clothes
     */
    public Clothes getCurrentOutfit() {
        return currentOutfit;
    }

    /**
     * Checks whether the player has won the game
     * @return true if player won, otherwise false
     */
    public boolean hasWon() {
        return this.hasWonGame;
    }

    /**
     * Sets the field wonGame to true and lets the player win the game.
     */
    public void setWonGame() {
        hasWonGame = true;
        Game.winGame();
    }

    /**
     * Sets the completed assignment to true, so that player can win the game.
     */
    public void setCompletedAssignment() {
        hasCompletedAssignment = true;
    }

    /**
     * Determines whether the player has completed the assignment.
     * @return true if the assignment is completed, otherwise false
     */
    public boolean hasCompletedAssignment() {
        return this.hasCompletedAssignment;
    }

    /**
     * Changes the player's current outfit.
     * @param currentOutfit new outfit
     * @see Clothes
     */
    public void setCurrentOutfit(Clothes currentOutfit) {
        this.currentOutfit = currentOutfit;
    }

    /**
     * Gets the weapon the player is holding.
     * @return weapon
     * @see Weapon
     */
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    /**
     * Lets the player pick up a (new) weapon.
     * @param currentWeapon new weapon
     * @see Weapon
     */
    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    /**
     * Sets the players health to a new value.
     * @param health new health value
     */
    public void setHealth(int health) {
        if (health < 1)
            health = 1;
        if (health > maxHealth)
            health = maxHealth;
        this.health = health;
    }

    /**
     * Sets the player's maximum health
     * @param maxHealth maximum value for health
     */
    public void setMaxHealth(int maxHealth) {
        if (maxHealth < 1)
            maxHealth = 1;
        this.maxHealth = maxHealth;
        if (health > maxHealth)
            health = maxHealth;
    }

    /**
     * Gets the player's maximum health
     * @return maximum health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the player's damage.
     * @param fistDamage damage
     */
    public void setFistDamage(int fistDamage) {
        this.fistDamage = fistDamage;
    }

    /**
     * Gets the player's money.
     * @return money
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets the player's money to a new value.
     * @param money The amount of player money.
     */
    public void setMoney(double money) {
        this.money = money;
    }

}