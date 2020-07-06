package lastassignment.items;

import lastassignment.utils.Console;
import lastassignment.Player;

/**
 * @author Boris
 * @version 1.0
 *
 * The Player can purchase/find a Map that can help with navigation.
 * The Map shows the player the layout of the Rooms on the first floor
 *
 * @see Item
 */
public class Map extends Item {

    public Map(String description) {
        super(description);
    }

    /**
     * The player looks at the Map and its printed out on the console.
     * This consists of a bunch of ASCII art.
     *
     * @param player The Player that uses the Map
     * @see Player
     */
    @Override
    public void interact(Player player) {
        if (player.hasItem(this)) {
            Console.printLine("+---------------+---------------+--+-----------------+................................");
            Console.printLine("|               |               |  |   Mechanical    |................................");
            Console.printLine("|  West Break   [               |  [   Engineering   |................................");
            Console.printLine("|     Room      |               ]  [   Lab           |................................");
            Console.printLine("+---==---+-+==+-+    Atrium     |  |                 |................................");
            Console.printLine("| Toilet |.|__|.|               |  +-----------------+----+--------------------------+");
            Console.printLine("+--------+.|__|.|               |  |                      |                          |");
            Console.printLine("...........|__|.|               ]  [     Lecture Room     ]      East Break Room     |");
            Console.printLine("...........|__|.|               |  |         512          |                          |");
            Console.printLine("...........|__|.+---==---+--==--|  +----------------------+==+-----------+----+==+---+");
            Console.printLine("...........|__|..........| Meet |  |......................|  |           |           |");
            Console.printLine("......+----+  |..........| Spot [  |......................|  + Classroom | Organic   |");
            Console.printLine("......|       |..........|      |  |......................|  [    200    | Chemistry |");
            Console.printLine("......|       |..........+--==--+==+-------------------+..|  +           | Lab       |");
            Console.printLine("......|  To   |.................|                      |..|  |           |           |");
            Console.printLine("......[  2nd  |.................|     Lecture Room     +--+  |-----------+           |");
            Console.printLine("......| Floor |.................|          509         ]     |...........|           |");
            Console.printLine("......|       |.................+==+-------------------+--+  |...........+-----------+");
            Console.printLine("......|       |.................|                      [     |........................");
            Console.printLine("......+----+__|.................[                      +--+==+-----+..................");
            Console.printLine("...........|__|.................[   +----==----+       |..| Toilet |..................");
            Console.printLine("...........|__|.................|   | Computer |    R  +--+==+-----+-----------------+");
            Console.printLine("...........|__+-----------------+   |   Room   |    e  [                             |");
            Console.printLine("...........|__|    Classroom    |   +----==----+    c  +-------------------+---==----+");
            Console.printLine("...........|__|       300       |                   e  |...................|         |");
            Console.printLine("...........|__+-------===-------+                   p  +-------------------+  Staff  |");
            Console.printLine("...........|                    ]                   t  |                   [  Room   |");
            Console.printLine("...........+--+-------===-------+                   i  [                   +---==----+");
            Console.printLine("..............| Nano-technology |                   o  [                   |         |");
            Console.printLine("..............|       Lab       |                   n  |     Cafeteria     [ Kitchen |");
            Console.printLine("..............+-----------------+                      |                   |         |");
            Console.printLine("................................+-----+---====---+-----+                   +---------+");
            Console.printLine("......................................| Entrance |.....|                   |..........");
            Console.printWithPause("......................................+---====---+.....+-------------------+..........");
        } else {
            Console.printWithPause("You pick up the %s", getDescription());
            Console.printWithPause("It will definitely come in handy");
            player.addItemToBackpack(this);
        }
    }
}