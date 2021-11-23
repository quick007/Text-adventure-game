import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public class Main extends ConsoleProgram {

    public static void main(String[] args) {
        start();
        Random rand = new Random();
        // normal variables
        boolean[] exit = { false, false }; // can you use the key, is the exit door open
        int[] stats = { 0, 1, 10, 1, 0 }; // stats: coins, rooms explored, health, direction facing, room
        // hashmap for rooms
        HashMap<String, Integer[]> dirs = new HashMap<>(); // room number (ex. rm 1), then all the exits
                                                           // (north/east/south/west) with the number room they go into.
                                                           // if they don't go into a room, put 0. (ex. {1, 0, 0, 2})
        dirs.put("rm 0", new Integer[] { 0, 0, 0, 0 });
        dirs.put("rm 1", new Integer[] { 0, 2, 3, 0 });
        dirs.put("rm 2", new Integer[] { 0, 0, 0, 1 });
        dirs.put("rm 3", new Integer[] { 1, 0, 4, 0 });
        dirs.put("rm 4", new Integer[] { 3, 5, 0, 0 });
        dirs.put("rm 5", new Integer[] { 0, 6, 0, 4 });
        dirs.put("rm 6", new Integer[] { 10, 7, 8, 5 });
        dirs.put("rm 7", new Integer[] { 9, 0, 0, 6 });
        dirs.put("rm 8", new Integer[] { 6, 0, 0, 0 });
        dirs.put("rm 9", new Integer[] { 0, 0, 7, 10 });
        dirs.put("rm 10", new Integer[] { 0, 9, 6, 0 });
        // descriptiuons for rooms whener you sue `turn <arg>`
        HashMap<String, String[]> desc = new HashMap<>();
        desc.put("rm 0", new String[] {
                "The walls are marked with what appears to be blood. They cry " + red + "Help!" + norm
                        + " written in a million different sizes.",
                "As I walk around, I can hear cameras wrrrrrrring to follow my every movement... I should get out of here somehow",
                "A mass teleportation node is fastened to a wall, that must be how I got here…",
                "A desk with some papers on it sits covered with dust." });
        desc.put("rm 1",
                new String[] { "A white, concrete slab forms the border of the dungeon",
                        "In the dungeon, an iron-wrought door stands half ajar… I wonder what’s inside.",
                        "Another hallway extends out into the abyss",
                        "A white, concrete slab forms the border of the dungeon" });
        desc.put("rm 2", new String[] {
                "Under a tapestry, you see a note on the table, written in a neat hand. It says “There’s a key in this maze somewhere, but I can’t seem to find it”.",
                "A window sits above a small shelf with a snowy landscape beyond. You try breaking the window to escape, to no avail.",
                "Another concrete wall with a window, but this one is covered with thick layers of vines. ",
                "The door back to room 1 is left open." });
        desc.put("rm 3", new String[] { "The hallway extends back to room one through the darkness",
                "A single soul lamp provides a small bit of light in this room.",
                "A granite archway with a metal door, rusted to make it look old stands before you.",
                "A ring with a symbol inside, made of some sort of reddish liquid surrounded by candles is- wait for a second, what is this place… " });
        desc.put("rm 4", new String[] {
                "A granite archway with a rusted door, now hanging ajar. On the sides of it are small air plants.",
                "A rusting iron bar door sits unlocked.",
                "This room appears to be some sort of garden. You see a fountain, statues of various creatures, and a large, quiet area with a bench and a small pond.",
                "You see a massive amount of different types of flowers. You smell their sweet, pleasant scent." });
        desc.put("rm 5", new String[] {
                "Corroded iron bars line the walls creating cells for what I can only assume to be long gone prisoners. ",
                "A path leads up into a central meeting area.",
                "More iron bars line the area, some with even some remains of bones. The same red symbol appears here.",
                "A rusting iron door leads back into the garden, which lets light into this lifeless room" });
        desc.put("rm 6", new String[] { "A path leads down to a locked door… I wonder what’s behind it.",
                "A path leads down to a forestry area.", "A path leads down to a library area with a bit of seating.",
                "A path leads down to an older prison area." });
        desc.put("rm 7", new String[] {
                "Through the trees, you notice a doorway on the left with an old, tattered banner hanging above it that reads 'Treasure Room'.",
                "You are standing in the middle of a large forest. You can see a river running along one side of the room.",
                "Thick indoor forestry continues out into the distance.",
                "A path leads up into a central meeting area." });
        desc.put("rm 8", new String[] { "A path leads up into a central meeting area.",
                "A circular window looks out to the snowy plains surrounded by reviews of books 10 years old.",
                "Books are strewn every which way, with no particular order in mind.",
                "A few armchairs and some books are lying on the ground, forming a quaint little reading area." });
        desc.put("rm 9", new String[] {
                "A treasure chest sits in the room, illuminated with hidden lighting. You attempt to pry it open, but to no avail.",
                "The sides of the treasure room have beautifully crafted robes encased in bulletproof glass.",
                "There’s a door leading back into the forest whence you came.",
                "A white, concrete slab stops you from continuing." });
        desc.put("rm 10", new String[] { "amongus", "amongus", "A path leads up into a central meeting area.", "" });
        HashMap<String, Integer> coins = new HashMap<>();
        coins.put("rm 0", 0);
        for (int i = 1; i < 10; i++) {
            coins.put("rm " + i, (rand.nextInt(10) + 1));
        }
        int a = rand.nextInt(9) + 1;
        int b = rand.nextInt(8) + 1;
        if (b == a) {
            b = b + 1;
        }
        coins.put("rm " + a, 11);
        coins.put("rm " + b, 12);
        // inventory
        ArrayList<String> inv = new ArrayList<String>();
        inv.add("TP Scroll");
        inv.add("Health Potion");
        while (!isOver) {
            String cmd = "";
            //String ans = "";
            if (!isAttacked) {
                cmd = readLine("> ");
            } else {
                //ans = readLine("> answer: ");
            }
            // help command
            if (cmd.contains("help")) {
                println("Help:\n - turn <right/left/around> (turns your charachter)\n - move (moves to the next room in the direction your facing)\n - search (searches the room your in)\n - stats (shows the stats of your charachter) \n - inventory (shows the items you can use) \n - use <item>");
                // turn command
            } else if (cmd.contains("turn")) {

                if (cmd.contains("right")) {
                    if (stats[3] == 3) {
                        stats[3] = 0;
                    } else {
                        stats[3] = stats[3] + 1;
                    }
                    println("Turned right.");
                    print(story);
                    String storyLine = (desc.get("rm " + stats[4]))[stats[3]];
                    for (int i = 0; i < storyLine.length(); i++) {
                        wait(4);
                        print(storyLine.charAt(i));
                    }
                    println("");
                } else if (cmd.contains("left")) {
                    if (stats[3] == 0) {
                        stats[3] = 3;
                    } else {
                        stats[3] = stats[3] - 1;
                    }
                    println("Turned left.");
                    print(story);
                    String storyLine = (desc.get("rm " + stats[4]))[stats[3]];
                    for (int i = 0; i < storyLine.length(); i++) {
                        wait(4);
                        print(storyLine.charAt(i));
                    }
                    println("");
                } else if (cmd.contains("around")) {
                    stats[3] = stats[3] + 2;
                    if (stats[3] == 4) {
                        stats[3] = 0;
                    } else if (stats[3] == 5) {
                        stats[3] = 1;
                    }
                    println("Turned around.");
                    print(story);
                    String storyLine = (desc.get("rm " + stats[4]))[stats[3]];
                    for (int i = 0; i < storyLine.length(); i++) {
                        wait(4);
                        print(storyLine.charAt(i));
                    }
                    println("");
                } else {
                    println("I don't know how to go in that direction! (syntax: turn <right/left/around>");
                }
                // move command
            } else if (cmd.contains("move")) {
                int enter = dirs.get("rm " + stats[4])[stats[3]];
                if (enter == 0) {
                    println("I can't go that way!");
                } else if (enter == 10) {
                    exit[0] = true;
                    if (!exit[1]) {
                        println(story + "The door is locked... maybe theres a key somewhere you can use to unlock it?");
                    } else {
                        println(story
                                + "You won! Congrats! \nThat means that my losing systems didn't take over every room randomly \n(I mean it could happen, like its not like I spent the time to actually code a failsafe aginst it)");
                        isOver = true;
                        wait(2000);
                        print(story + "More fanfare you say? Sorry that above the budget for this game.");
                        println("Score: ");
                        wait(1000);
                        println((stats[0] * 2 + 5) / 2);
                    }

                } else {
                    stats[4] = enter;
                    enterRoom(stats, "You've entered a new room");
                    stats[1] = stats[1] + 1;
                    println(story + desc.get("rm " + stats[4])[stats[3]]);
                }
                // stats command
            } else if (cmd.contains("stats")) {
                println(stats(stats));
                // search command
            } else if (cmd.contains("search")) {
                int bal = coins.get("rm " + stats[4]);
                if (stats[4] == 0 && bal != -1) {
                    // starter room
                    println(story + "You find a" + blue + " [1x TP Scroll]" + norm + " and " + blue
                            + "[1x Health Potion]" + norm + ".");
                    wait(2000);
                    println(story + "The walls are marked with what appear to be blood. They cry " + red + "Help!"
                            + norm + " written in a million different sizes.");
                    wait(2500);
                    println(story + "As I walk around, I can hear cameras wirring to follow my every movement...");
                    wait(2000);
                    println(story + red + "IS THIS SOME KIND OF EXPERIMENT?" + norm
                            + " I yell. No response, as I expected.");
                    wait(1500);
                    println(story
                            + "Thats... unerving. Well, they seem to have placed this teleportation scroll here for me to use it.");
                    println(story
                            + "It must take me somehere, but I'm not sure if that somewhere is a place I want to be...");
                    println(hint + "Type `use TP Scroll` to use the item!" + norm);
                } else if (bal == -1) {
                    println("You've already searched this room!");
                } else if (bal > 6 && bal < 11) {
                    // special occasion rooms
                    int special = rand.nextInt(3);
                    int extra = rand.nextInt(5) + 1;
                    switch (special) {
                    case 0:
                        if (!inv.contains("Single-Use Shield")) {
                            println("You were attacked. " + ((extra * 2 + 4) / 2) + " coins were lost.");
                            println(red + "[-" + extra + " ❤ ]" + norm);
                            stats[0] = stats[0] - ((extra * 2 + 4) / 2);
                            stats[2] = stats[2] - extra;
                            if (stats[2] > 1) {
                                isDead(stats);
                            }
                        } else {
                            println("You were attacked.");
                            wait(1000);
                            println("Single-Use Shield activated!");
                            inv.remove("Single-Use Shield");
                            println(blue + "- [1x Single-Use Shield]" + norm);
                        }
                        break;
                    case 1:
                        if (!inv.contains("Single-Use Shield")) {
                            println("You were attacked, and defeated the intruder.\nYou lost " + (extra + 1)
                                    + " hearts from the attack, but got a " + blue + "[1x Single-Use Gun]" + norm
                                    + ".");
                            println(red + "[-" + (extra + 2) + " ❤ ]" + norm);
                            println(blue + "+ [1x Single-Use Gun]" + norm);
                            stats[2] = stats[2] - extra;
                            inv.add("Single-Use Gun");
                            if (stats[2] > 1) {
                                isDead(stats);
                            }
                        } else {
                            println("You were attacked.");
                            wait(1000);
                            println("Single-Use Shield activated!");
                            println(blue + "- [1x Single-Use Shield]" + norm);
                            inv.remove("Single-Use Shield");
                        }
                        break;
                    case 2:
                        println(story + "You find a" + blue + " [1x Health Potion]" + norm);
                        println(blue + "+ [1x Health Potion]" + norm);
                        inv.add("Health Potion");
                        break;
                    case 3:
                        println(story + "You find a" + blue + " [1x Single-Use Shield]" + norm);
                        println(blue + "+ [1x Single-Use Shield]" + norm);
                        inv.add("Single-Use Shield");
                        break;

                    }
                } else if (bal == 11) {
                    println(story + "You find a" + blue + " [1x Key]" + norm);
                    inv.add("Key");
                } else if (bal == 12) {
                    println(story + "You find a" + blue + " [1x Key]" + norm);
                    inv.add("Key");
                    // normal occurance
                } else {
                    println("You found " + coins.get("rm " + stats[4]) + " coins!");
                    stats[0] = stats[0] + coins.get("rm " + stats[4]);
                }
                coins.put("rm " + stats[4], -1);
                // use command
            } else if (cmd.contains("use")) {
                String toUse = cmd.split(" ", 2)[1];
                if (inv.contains(toUse)) {
                    inv.remove(new String(toUse));
                    println("Used " + blue + "[1x " + toUse + "]" + norm + "!");
                    wait(1000);
                    switch (toUse) {
                    case "TP Scroll":
                        stats[4] = 1;
                        enterRoom(stats, "The scroll teleports you to room one!");
                        break;
                    case "Health Potion":
                        println(red + "[+5 ❤ ]" + norm);
                        stats[2] = stats[2] + 5;
                        if (stats[2] > 10) {
                            stats[2] = 10;
                        }
                        break;
                    case "Key":
                        if (exit[0]) {
                            exit[1] = true;
                            println(story + "Opened the door!");
                        } else {
                            println(story + "You can't use this right now...");
                        }
                        break;
                    case "Single-Use Shield":
                        println(story + "This can't be used here!");
                        break;
                    case "Single-Use Pistol":
                        println(story + "This can't be used here!");
                        break;
                    case default:
                        println(story + "You can't use this right now...");
                        break;
                    }
                } else {
                    println("I can't find " + blue + "[" + toUse + "]" + norm + "! \nCheck your capitalization!");
                }
                // inventory command
            } else if (cmd.contains("inv") || cmd.contains("inventory")) {
                println("Inventory: ");
                for (String element : inv) {
                    println(" - " + element);
                }
            } else {
                println("I can't recognize that command! Type `help` for help");
            }
        }
    }

    static void start() {
        println("Loading commands...");
        wait(500);
        println("Downloading libs...");
        wait(200);
        println("Adding background...");
        wait(400);
        print("\u001B[40m\u001B[37m");
        println("Loaded!");
        wait(100);
        println("Starting...");
        wait(500);
        println("Started!\n\n");
        wait(1000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        println(story
                + "The last thing I can remember was going deadish. Now I'm revived, but where could I be? \nMaybe there's a hint in this dark room, "
                + cyan + "I wonder if searching it would turn up any clues..." + norm + "\nType `help` for help.\n---");
    }

    static void enterRoom(int[] stats, String toPrint) { // array of the stats (coins/rooms explored), what message to
                                                         // send when you enter the room, and the number of the room
                                                         // entered
        System.out.print("\033[H\033[2J");
        System.out.flush();
        println(toPrint);
        println("Entered room: " + stats[4]);
        println(stats(stats));
        wait(1000);
    }

    static String stats(int[] stats) {
        String healthVisual = "";
        switch (Math.round((float) stats[2] / 2)) {
        case 0:
            healthVisual = "";
            break;
        case 1:
            healthVisual = "█";
            break;
        case 2:
            healthVisual = "██";
            break;
        case 3:
            healthVisual = "███";
            break;
        case 4:
            healthVisual = "████";
            break;
        case 5:
            healthVisual = "█████";
            break;
        }
        return ("---\nStats:\n - Coins: " + stats[0] + "\n - Rooms Explored: " + stats[1] + "\n - Health: "
                + healthVisual + " (" + stats[2] + "/10)" + "\n - Direction: " + direction(stats[3]) + "\n---");
    }

    static String direction(int facing) {
        String visual = "";
        switch (facing) {
        case 0:
            visual = "↑ (North)";
            break;
        case 1:
            visual = "→ (East)";
            break;
        case 2:
            visual = "↓ (South)";
            break;
        case 3:
            visual = "← (West)";
            break;
        }
        return (visual);
    }

    static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException x) {
        }
    }

    static void isDead(int[] stats) {
        wait(5000);
        println(red + "GAME OVER. YOU DIED.");
        stats(stats);
        println("Score: ");
        wait(1000);
        println(Math.round((stats[0] * 2 + 5) / 2));
        isOver = true;
    }

    // void move(room)
    public static boolean isOver = false;
    public static boolean isAttacked = false;
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String norm = "\u001B[37m";
    public static final String story = blue + "[Story]: " + norm;
    public static final String hint = yellow + "[Hint]: ";
}