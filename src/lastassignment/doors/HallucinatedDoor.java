package lastassignment.doors;

import lastassignment.utils.Console;
import lastassignment.Player;
import lastassignment.Room;

import java.util.Random;

public class HallucinatedDoor extends Door {

    public HallucinatedDoor() {
        super("", null, null);

        switch (new Random().nextInt(20)) {
            case 0:
                setDescription("plain wooden door");
                break;
            case 1:
                setDescription("metallic door covered in moving gears");
                break;
            case 2:
                setDescription("perfectly reflective door");
                break;
            case 3:
                setDescription("portal to another universe");
                break;
            case 4:
                setDescription("portal to exactly where you need to be");
                break;
            case 5:
                setDescription("door to exactly where you need to be");
                break;
            case 6:
                setDescription("entrance to fairyland");
                break;
            case 7:
                setDescription("door to the black pits of hell");
                break;
            case 8:
                setDescription("dungeon door");
                break;
            case 9:
                setDescription("basement");
                break;
            case 10:
                setDescription("front door of your house");
                break;
            case 11:
                setDescription("strangely placed door");
                break;
            case 12:
                setDescription("door lying on the floor");
                break;
            case 13:
                setDescription("fake looking door");
                break;
            case 14:
                setDescription("walking door");
                break;
            case 15:
                setDescription("shimmering door");
                break;
            case 16:
                setDescription("glowing door");
                break;
            case 17:
                setDescription("door so white that your eyes hurt");
                break;
            case 18:
                setDescription("tiny rat hole");
                break;
            case 19:
                setDescription("window into another universe");
                break;
        }
    }

    @Override
    protected void addDoorToRooms(Room room1, Room room2) {}

    @Override
    public void interact(Player player) {
        Console.printWithPause("You go through the %s and into another room", getDescription(player));
        Console.printWithPause("Oh wait. You walked right into the wall");
        Console.printWithPause("It turns out there was nothing there");
    }

    @Override
    public String getDescription(Player player) {
        return descriptionFromRoom1;
    }

}