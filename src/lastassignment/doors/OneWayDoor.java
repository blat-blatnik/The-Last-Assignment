package lastassignment.doors;

import lastassignment.utils.Console;
import lastassignment.Player;
import lastassignment.Room;

public class OneWayDoor extends Door {

    private boolean isOpenFromRoom2;
    private boolean playerTriedToOpenDoorFromRoom2;

    public OneWayDoor(String descriptionFromRoom1, String descriptionFromRoom2, Room room1, Room room2) {
        super(descriptionFromRoom1, descriptionFromRoom2, room1, room2);
        isOpenFromRoom2 = false;
        playerTriedToOpenDoorFromRoom2 = false;
    }

    public OneWayDoor(String description, Room room1, Room room2) {
        this(description, description, room1, room2);
    }

    @Override
    public void interact(Player player) {
        if (isOpenFromRoom2)
            super.interact(player);
        else {

            Room playerRoom = player.getCurrentRoom();
            if (playerRoom == getRoom1()) {

                super.interact(player);
                Console.printWithPause("As you go through, something clicks");
                isOpenFromRoom2 = true;
                if (playerTriedToOpenDoorFromRoom2) {
                    Console.printWithPause("You are now in %s", getRoom1().getDescription());
                    Console.printWithPause("Aha! So that's where this damned %s leads to", getDescription(player));
                    Console.printWithPause("I guess now that it opened once, it will probably open again");
                }

            } else { // playerRoom == room2 && !isOpenFromRoom2

                Console.printWithPause("You try to go through %s", getDescription(player));
                Console.printWithPause("But it doesn't budge!");
                Console.printWithPause("You try harder and harder, bashing and kicking");
                Console.printWithPause("But it doesn't give in");
                Console.printWithPause("Damn it! It looks like it's stuck");
                Console.printWithPause("Maybe you can open it from the other side, wherever that is");
                Console.printWithPause("For now, you go back to the center of the room\n");
                playerTriedToOpenDoorFromRoom2 = true;

            }

        }
    }

}