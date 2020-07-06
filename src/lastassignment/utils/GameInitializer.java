package lastassignment.utils;

import lastassignment.Game;
import lastassignment.doors.*;
import lastassignment.items.*;
import lastassignment.npcs.*;
import lastassignment.roomcontents.*;
import lastassignment.Player;
import lastassignment.Room;
import lastassignment.io.Initializer;

import java.io.IOException;
import java.util.Random;

/**
 * @author Jana
 * @version 3.0
 *
 * This helper class is used to initialize the state of the Game world.
 *
 * @see Game
 */
public class GameInitializer {

    /**
     * Used for selecting whether to initialize the game from config,
     * reset the config to default, or just initialize the Game without
     * a config file.
     */
    public enum InitMethod {
        NORMAL,
        FROM_CONFIG,
        RESET_CONFIG
    }

    private final Random rand;
    private final InitMethod initMethod;

    /**
     * Constructs a GameInitializer with a given InitMethod.
     *
     * @param method The desired initialization method.
     * @see InitMethod
     */
    public GameInitializer(InitMethod method) {
        rand = new Random();
        initMethod = method;
    }

    /**
     * Initializes the state of the Game world.
     *
     * @return The Player of the Game, which indirectly holds the state of the whole Game.
     * @see Player
     * @see Room
     * @see Door
     * @see NPC
     * @see Game
     */
    public Player initGameAndGetPlayer() {

        Room entrance = new Room(
                "the entrance to the university building");
        Room mainHall = new Room(
                "the main hall of the university building");
        Room computerRoom = new Room(
                "a dimly lit computer room");
        Room cafeteria = new Room(
                "the school cafeteria");
        Room kitchen = new Room(
                "the pleasantly smelling cafeteria kitchen");
        Room staffRoom = new Room(
                "a very messy staff room");
        Room eastHallway = new Room(
                "a long hallway");
        Room maintenanceRoom = new Room(
                "a very out of place maintenance room");
        Room toilet = new Room(
                "a surprisingly clean toilet");
        Room northEastHallway = new Room(
                "an elongated hallway with no end in sight");
        Room echoingLectureHall = new Room(
                "a large, acoustically tuned lecture room featuring a massive blackboard spanning the entire wall");
        Room eastBreakRoom = new Room(
                "a coffee stain covered break room");
        Room crampedClassroom = new Room(
                "a cramped classroom with uncomfortable looking chairs");
        Room organicChemistryLab = new Room(
                "a lab full of tempting looking chemicals");
        Room topSecretResearchLab = new Room(
                "a lab filled with equipment far beyond your understanding");
        Room dustyLectureHall = new Room(
                "a large lecture room permeating with stale air");
        Room northWestHallway = new Room(
                "yet another long hallway");
        Room mechanicalEngineeringLab = new Room(
                "a lab filled with gadgets and machinery");
        Room utilityRoom = new Room(
                "a dank over sized closet with lots of cleaning equipment");
        Room maintenanceHallway = new Room(
                "a long dimly lit tunnel with uninspired gray walls");
        Room observatory = new Room(
                "a pleasantly constructed room with huge windows used to observe the outside world");
        Room atrium = new Room(
                "the tall and echoing atrium of the university building");
        Room westBreakRoom = new Room(
                "a break room with a very relaxing atmosphere");
        Room electronicsRoom = new Room(
                "a room full of buzzing wires and clicking gauges");
        Room contaminatedToilet = new Room(
                "a smelly toiled with contaminated air");
        Room campusPark = new Room(
                "a large grassy park right outside of the university building");
        Room westHallway = new Room(
                "a long L-shaped hallway");
        Room spaciousClassroom = new Room(
                "a pleasant classroom with comfortable chairs and a view of the park outside");
        Room nanoTechnologyLab = new Room(
                "a lab full of intimidating hazmat suits scattered on the floor as if in a hurry");
        Room raisedPlatform = new Room(
                "a 2nd floor platform overlooking the park outside");
        Room contaminatedHallway = new Room(
                "a very contaminated hallway");
        Room emergencyExitPlatform = new Room(
                "a shaky looking-and-feeling platform only meant for emergency use");
        Room planningRoom = new Room(
                "a room featuring a large scrawled-over whiteboard and a buzzing projector");
        Room electricalEngineeringLab = new Room(
                "a lab whose floor is littered with electronic circuits");
        Room roboticsLab = new Room(
                "a state of the art robotics lab.. filled with actual human sized robots");
        Room officeRoom = new Room(
                "an office fit for the bureaucratic type of scientist");

        Key organicChemistryLabKey = new Key("small silver key");
        Key engineeringLabKey = new Key("key labelled \"Engineering\"");
        Key maintenanceKey = new Key("rusted old key");
        Key secondFloorKey = new Key("key with a corroded label");

        Enemy staffRoomCafeteriaEnemy = new Enemy("an old lady staring intensely at you",
                5, 1, true);
        Enemy electronicRoomMaintenanceHallwayEnemy = new Enemy(
                "a jumpsuit wearing man with bloodshot eyes and a terrible cough",
                8, 5, true);

        new SchoolEntrance(entrance, mainHall);
        new Door("A thin glass door", mainHall, computerRoom);
        new Door("The large cafeteria door", mainHall, cafeteria);
        new Door("A small blue door labelled \"East\"",
                "A small blue door labelled \"Main Hall\"",
                mainHall, eastHallway);
        new Door("A small green door labelled \"North-East\"",
                "A small green door with a scratched off label",
                mainHall, northEastHallway);
        new Door("The large wooden door of lecture room 509",
                "A large wooden leading out of lecture room 509",
                mainHall, echoingLectureHall);
        new OneWayDoor("An emergency exit going into the university building",
                "An emergency exit",
                campusPark, mainHall);
        new Door("A small yellow door labelled \"West\"",
                "A small yellow door labelled \"Main Hall\"",
                mainHall, westHallway);
        new LockedDoor("An inconspicuous maintenance door", cafeteria, maintenanceRoom, maintenanceKey);
        new Door("The kitchen door", cafeteria, kitchen);
        new AmbushDoor("A door labelled \"Staff\"",
                "a door labelled \"Cafeteria\"",
                cafeteria, staffRoom, staffRoomCafeteriaEnemy);
        new Door("An industrial door labelled \"Staff\"",
                "Large industrial door",
                kitchen, staffRoom);
        new Door("A small door labelled \"East\"",
                "A small door labelled \"Staff\"",
                staffRoom, eastHallway);
        new LockedDoor("A blended in maintenance door", eastHallway, maintenanceRoom, maintenanceKey);
        new OneWayDoor("a wall shifting mechanism",
                "a broken down toilet mounted on an out of place wall",
                topSecretResearchLab, toilet);
        new Door("The toilet door",
                "A door leading south",
                eastHallway, toilet);
        new Door("The toilet door",
                "A door leading north",
                northEastHallway, toilet);
        new Door("The swinging door of lecture room \"509\"",
                "A swinging door leading out of lecture room 509",
                northEastHallway, echoingLectureHall);
        new Door("A door labelled 200",
                "A small door leading out of the classroom",
                northEastHallway, crampedClassroom);
        new Door("A large wooden door labelled \"512\"",
                "A large wooden door leading out of lecture room 512",
                northEastHallway, dustyLectureHall);
        new Door("A small door", northEastHallway, eastBreakRoom);
        new HiddenDoor("a bookshelf", "a revolving bookshelf",
                organicChemistryLab, topSecretResearchLab);
        new HiddenDoor("a small vent", "a vent big enough to crawl through",
                crampedClassroom, organicChemistryLab);
        new LockedDoor("A lab door", eastBreakRoom, organicChemistryLab, organicChemistryLabKey);
        new HiddenDoor("a broken vending machine",
                "a small passageway hidden behind a broken vending machine",
                "a metal plate",
                "a closed metal chute",
                eastBreakRoom, maintenanceHallway);
        new Door("A raised door labelled \"North-West\"",
                "A raised door labelled \"509\"",
                echoingLectureHall, northWestHallway);
        new Door("A raised door labelled \"North-West\"",
                "A raised door labelled \"512\"",
                dustyLectureHall, northWestHallway);
        new Door("A polished glass door", northWestHallway, observatory);
        new OneWayDoor("A large blocked off doorway", atrium, northWestHallway);
        new LockedDoor("A heavy metal door labelled \"Engineering\"",
                "A heavy metal door", northWestHallway, mechanicalEngineeringLab, engineeringLabKey);
        new HiddenDoor("a sign \"Maintenance\"", "An inconspicuous maintenance door", northWestHallway, maintenanceHallway);
        new Door("A small shiny glass door", atrium, observatory);
        new Door("A slow revolving door to the outside", atrium, campusPark);
        new Door("A door labelled \"Coffee Corner\"",
                "A door labelled \"West Wing\"",
                atrium, westBreakRoom);
        new Door("A toilet door",
                "A door leading out of the toilet",
                westBreakRoom, contaminatedToilet);
        new HiddenDoor("a broken vending machine",
                "a hidden entrance behind a broken vending machine",
                westBreakRoom, electronicsRoom);
        new HiddenDoor("a huge bookcase", "a movable bookcase",
                westBreakRoom, maintenanceHallway);
        new Door("Stairs leading up to the 2nd floor", "Stairs going down labelled \"Break\"", westBreakRoom, raisedPlatform);
        new HiddenDoor("a bookcase","a secret bookcase door", electronicsRoom, contaminatedToilet);
        new AmbushDoor("A sealed metal door", electronicsRoom, maintenanceHallway, electronicRoomMaintenanceHallwayEnemy);
        new BreakableDoor("A door made of rotting wood", electronicsRoom, contaminatedHallway, 3);
        new BreakableDoor("A large rotting door", contaminatedHallway, officeRoom, 2);
        new LockedDoor("A large metallic door labelled \"Robotics\"",
                "a large metallic door", contaminatedHallway, roboticsLab, secondFloorKey);
        new LockedDoor("A small wooden door labelled \"Electronics\"",
                "a small wooden door", contaminatedHallway, electricalEngineeringLab, secondFloorKey);
        new LockedDoor("A fancy looking wooden door", contaminatedHallway, planningRoom, secondFloorKey);
        new Door("A small door leading out of the hallway", contaminatedHallway, raisedPlatform);
        new HiddenDoor("A hole in the wall", "a large hole reaching into the next room",
                roboticsLab, electricalEngineeringLab);
        new HiddenDoor("A concealed air duct", "an air duct large enough to crawl through",
                electricalEngineeringLab, planningRoom);
        new BreakableDoor("A large glass door", planningRoom, emergencyExitPlatform, 1);
        new HiddenDoor("a ladder", "a ladder down into the park",
                "a ladder", "a ladder up to a platform",
                emergencyExitPlatform, campusPark);
        new Door("Stairs going down labelled \"West\"", "Stairs leading up to the 2nd floor", raisedPlatform, westHallway);
        new Door("A door labelled \"300\"",
                "A small door leading out of the classroom",
                westHallway, spaciousClassroom);
        new Door("A hermetically sealed door", westHallway, nanoTechnologyLab);
        new BreakableDoor("A small utility door", maintenanceHallway, utilityRoom, 3);
        new LockedDoor("A metal door", mechanicalEngineeringLab, utilityRoom, maintenanceKey);

        LostWallet crampedClassroomWallet = new LostWallet("abandoned canvas wallet", 6.25);
        LostWallet spaciousClassroomWallet = new LostWallet("abandoned leather wallet", 9.05);
        LostWallet officeRoomWallet = new LostWallet("fancy looking wallet", 26.70);
        LostWallet eastBreakRoomWallet = new LostWallet("dropped wallet", 7.25);
        LostWallet dustyLectureRoomWallet = new LostWallet("lost denim wallet", 4.60);
        LostWallet westBreakRoomWallet = new LostWallet("fat canvas wallet", 12.35);
        crampedClassroom.addContents(crampedClassroomWallet);
        spaciousClassroom.addContents(spaciousClassroomWallet);
        officeRoom.addContents(officeRoomWallet);
        eastBreakRoom.addContents(eastBreakRoomWallet);
        dustyLectureHall.addContents(dustyLectureRoomWallet);
        westBreakRoom.addContents(westBreakRoomWallet);

        Container staffLocker = new Container("an open locker");
        Container utilityLocker = new Container("an old locker covered in rust");
        Container chest = new Container("a wooden chest");

        Clothes staffUniform = new Clothes("a staff uniform", false);
        Clothes labCoat = new Clothes("a lab coat", false);
        Clothes jumpsuit = new Clothes("a utility jumpsuit", false);
        Clothes hazmatSuit = new Clothes("a hazmat suit", true);

        Weapon crowbar = new Weapon("a crowbar", 5);
        Weapon bottleWeapon = new Weapon("a bottle with sharp edges", 3);
        Weapon slingshot = new Weapon("a wooden slingshot", 4);
        Weapon wrench = new Weapon("a metallic wrench", 4);
        Weapon rock = new Weapon("a small rock", 5);

        Vaccine vaccine1 = new Vaccine("a small syringe");
        Vaccine vaccine2 = new Vaccine("a horribly big syringe");

        VirusMedicine medicine = new VirusMedicine("a red pill", 5);
        VirusMedicine medicine2 = new VirusMedicine("a foul smelling potion", 7);

        organicChemistryLab.addContents(medicine);
        organicChemistryLab.addContents(vaccine1);
        staffLocker.addContents(medicine2);
        officeRoom.addContents(vaccine2);

        staffLocker.addContents(organicChemistryLabKey);
        staffLocker.addContents(staffUniform);
        utilityLocker.addContents(maintenanceKey);
        utilityLocker.addContents(jumpsuit);
        utilityLocker.addContents(slingshot);
        chest.addContents(crowbar);

        staffRoom.addContents(staffLocker);
        utilityRoom.addContents(utilityLocker);
        maintenanceRoom.addContents(chest);

        officeRoom.addContents(secondFloorKey);
        dustyLectureHall.addContents(engineeringLabKey);
        organicChemistryLab.addContents(labCoat);
        organicChemistryLab.addContents(bottleWeapon);
        electronicsRoom.addContents(wrench);
        campusPark.addContents(rock);
        nanoTechnologyLab.addContents(hazmatSuit);


        Shopkeeper receptionist = new Shopkeeper(
                "Linda, the school's workaholic receptionist", 10, 1, false, mainHall);
        Shopkeeper lunchLady = new Shopkeeper(
                "a hideous, yet friendly looking lunch lady", 20, 2, false, cafeteria);
        FriendlyNPC cook = new FriendlyNPC(
                "a busy looking cook running an entire kitchen by his lonesome", 15, 5, false, kitchen);
        FriendlyNPC unpleasantStudent = new FriendlyNPC(
                "a unpleasant girl that stares back", 8, 1, false, toilet);
        FriendlyNPC scaredResearcher = new FriendlyNPC(
                "a nerdy looking fellow shaking with fear", 10, 1, false, organicChemistryLab);
        FriendlyNPC mechanic = new FriendlyNPC(
                "a worn-out mechanic in red overalls and a long mustache", 10, 1, true, utilityRoom);
        Healer wackyProfessor = new Healer(
                "a wacky old professor mumbling to himself", 10, 1, true, echoingLectureHall);
        FriendlyNPC teenager = new FriendlyNPC(
                "an obvious teenager", 8, 5, true, campusPark);
        FriendlyNPC goth = new FriendlyNPC(
                "a teen dressed in all black, complete with black lipstick", 8, 1, false, campusPark);
        FriendlyNPC punk = new FriendlyNPC(
                "a scary looking punk kid with oily hair", 12, 2, false, campusPark);
        FriendlyNPC relaxedStudent = new FriendlyNPC(
                "a surprisingly chill looking student",10, 1, false, westBreakRoom);
        CompanionRobot robot1 = new CompanionRobot(2, 20, 5, roboticsLab);
        CompanionRobot robot2 = new CompanionRobot(33, 20, 5, roboticsLab);
        CompanionRobot robot3 = new CompanionRobot(42, 15, 5, electricalEngineeringLab);

        Enemy weakInfectedStudent = new Enemy(
                "a weak looking student with a bad case of the virus", 8, 1,
                false, toilet);
        Enemy infectedRats = new Enemy(
                "a horde of large and hungry lab rats looking for revenge", 6, 2,
                coinFlip(0.8), organicChemistryLab);
        Enemy infectedProfessor = new Enemy(
                "a zany old professor rambling on and on about something incomprehensible",
                12, 2, coinFlip(0.5), dustyLectureHall);
        Enemy infectedStudent = new Enemy(
                "a badly infected student that didn't practice social distancing",
                10, 2,
                false, observatory);
        Enemy infectedBureaucrat = new Enemy(
                "a slick but crazed man wearing a torn up suit", 10, 2,
                coinFlip(0.3), atrium);
        Enemy infectedEngineer = new Enemy(
                "a crazy engineer that can't seem to focus on anything for more than a flash",
                15, 4,
                coinFlip(0.3), mechanicalEngineeringLab);
        Enemy infectedLabWorker1 = new Enemy(
                "a rabid infected man trapped in a hazmat suit", 15, 4,
                coinFlip(0.5), nanoTechnologyLab);
        Enemy infectedLabWorker2 = new Enemy(
                "a lumbering man wearing a hazmat suit", 15, 4,
                coinFlip(0.5), nanoTechnologyLab);
        Enemy infectedElectricianGuard = new Enemy(
                "a sleepy looking infected repair-man", 10, 5,
                coinFlip(0.2), maintenanceHallway);

        Enemy infectedIntern = new Enemy(
                "a dozy intern with a bad case of the infection", 10, 2,
                coinFlip(0.5), emergencyExitPlatform);
        Enemy infectedResearcher = new Enemy(
                "a scientific mind tainted with the infection", 12, 2,
                coinFlip(0.5), planningRoom);
        Enemy mutantGiantBunny = new Enemy(
                "a rabbit that is simply too large for this world", 15, 4,
                false, electricalEngineeringLab);
        Enemy infectedBoss = new Enemy(
                "an important looking man coughing uncontrollably", 20, 5,
                true, officeRoom);

        ElectricalBox box1 = new ElectricalBox(
                false, false, "busted electrical box numbered \"1\"", electronicsRoom);
        ElectricalBox box2 = new ElectricalBox(
                false, true,
                "silent electrical box numbered \"2\"",
                "working electrical box numbered \"2\"",
                electronicsRoom);
        ElectricalBox box3 = new ElectricalBox(
                false, false,
                "busted electrical box numbered \"3\"",
                "working electrical box numbered \"3\"",
                electronicsRoom);
        ElectricalBox box4 = new ElectricalBox(
                false, false, "broken electrical box numbered \"4\"", electronicsRoom);

        new Computer("computer with no mouse", box1, computerRoom);
        new Computer("computer with a slightly broken screen", box2, computerRoom);
        new Computer("computer with a coffee stained keyboard", box3, computerRoom);
        new Computer("normal looking computer", box4, computerRoom);

        new FumeHood("disorganized fume hood", organicChemistryLab);
        new FumeHood("dirty fume hood", organicChemistryLab);
        new FumeHood("stained fume hood", nanoTechnologyLab);
        new FumeHood("overused fume hood", topSecretResearchLab);

        HealingItem smallChips = new HealingItem("small bag of chips", "eat", 3);
        HealingItem largeChips = new HealingItem("large bag of chips", "eat", 5);
        HealingItem croissant  = new HealingItem("packaged chocolate croissant", "devour", 6);
        HealingItem miniPizza  = new HealingItem("miniature packaged pizza", "devour", 10);

        HealingItem studentCoffee = new HealingItem("cheap student coffee", "drink", 2);
        HealingItem cappuccino = new HealingItem("decent cappuccino", "drink", 5);
        HealingItem cavemanCoffee = new HealingItem("huge cup of very strong coffee", "drink", 10);

        VendingMachine snackMachine1  = new VendingMachine("a well stocked snack machine", "snacks");
        VendingMachine coffeeMachine1 = new VendingMachine("an overused coffee machine", "coffee");
        VendingMachine snackMachine2  = new VendingMachine("a snack machine", "snacks");
        VendingMachine coffeeMachine2 = new VendingMachine("a pristine coffee machine", "coffee");
        VendingMachine snackMachine3  = new VendingMachine("a large snack machine", "snacks");
        VendingMachine coffeeMachine3 = new VendingMachine("a coffee machine", "coffee");

        snackMachine1.addItem(smallChips, 2);
        snackMachine1.addItem(largeChips, 3);
        coffeeMachine1.addItem(croissant, 2);

        snackMachine2.addItem(smallChips, 2);
        snackMachine2.addItem(largeChips, 3);
        snackMachine2.addItem(croissant, 4);
        coffeeMachine2.addItem(cappuccino, 2);
        coffeeMachine2.addItem(cavemanCoffee, 3);

        snackMachine3.addItem(miniPizza, 5);
        snackMachine3.addItem(croissant, 4);
        coffeeMachine1.addItem(studentCoffee, 1);
        coffeeMachine3.addItem(cappuccino, 2);

        cafeteria.addContents(snackMachine1);
        cafeteria.addContents(coffeeMachine1);
        eastBreakRoom.addContents(snackMachine2);
        eastBreakRoom.addContents(coffeeMachine2);
        westBreakRoom.addContents(snackMachine3);
        westBreakRoom.addContents(coffeeMachine3);

        Map map = new Map("A map of the first floor");
        Map dustyMap = new Map("A dusty and faded map of the first floor");
        receptionist.addItemToInventory(map, 5.0);
        mainHall.addContents(dustyMap);

        Notes kitchenNotes1 = new Notes("a scrawled note",
                "\"Guys, these cafeteria prices are preposterous!\"",
                "\"I mean, they're just way too low\"",
                "\"$4 for the small butter sandwich? How are we supposed to make any money off of these?\"",
                "\"At this rate we only make a 2000%% profit from every purchase, that's simply not enough.\"",
                "\"We need to bump up these prices even higher.\"",
                "Signed, \"Bill\"");
        Notes kitchenNotes2 = new Notes("a crumpled note",
                "\"We've been receiving price complaints from the students\"",
                "\"Many of them complain about our quote unquote ludicrous prices\"",
                "\"Please ignore these complaints, the students don't understand how much work goes into making one of these butter sandwiches\"",
                "\"Also, it's not like they have a choice here at the school\"",
                "Signed, \"Bill\"");
        Notes entranceNotes = new Notes("a large paper taped to the wall",
                "The paper reads: \"Due to the ongoing pandemic all university buildings are closed and off limits\"");
        Notes mathHomework = new Notes("a checkered notebook",
                "You flip through the pages of the notebook",
                "They're tattered with alien looking symbols that may as well be hieroglyphs",
                "One line vaguely reads \"ih d/dt |Y(t)> = H|Y(t)>\"",
                "It must be somebody's math homework");
        Notes chemistryHomework = new Notes("a bound notebook",
                "You flip through the pages of the notebook",
                "The pages contain equal parts chemical diagrams and badly drawn doodles",
                "It's probably somebodies chemistry notes");
        Notes mathNotes = new Notes("a weirdly square notebook",
                "You flip through the pages of the notebook",
                "They're tattered with alien looking symbols that may as well be hieroglyphs",
                "One line vaguely reads \"Boltzmann force term: f(r + p/m dt, p + Fdt, t + dt) d3r d3p = f(r, p, t) d3r d3p\"",
                "It must be somebody's math notes");
        Notes doodles = new Notes("a plain looking notebook",
                "You flip through the pages of the notebook",
                "It's mostly empty, except for a random selection of pages filled with doodles",
                "The doodles appear to be some kinds of superhero concepts",
                "One of them shows some anthropomorphized kangaroo hero aptly named \"Kangaroo Man\"",
                "Another one shows a slick looking hero dressed in blue titled \"Rewindo\"",
                "Huh, Rewindo, I guess he rewinds time or something?",
                "The writing indicate that all of these superheroes are a part of a larger team called \"Team S.C.I.E.N.C.E.\"");
        Notes organicReactions = new Notes("a giant tome titled \"Organic Reactions, Volume 90\"",
                "The book is filled with endless amounts of reaction diagrams and complicated equations",
                "It's completely beyond you",
                "You challenge yourself to read through one of the paragraphs",
                "You power through and start reading",
                "\"The organic compound lysergic acid diethylamide is a powerful hallucinogen by itself\"",
                "\"When mixed metallic copper, thiocyanic acid, and basic ammonia, the effects are even more powerful\"",
                "Your head already hurts",
                "That's enough chemistry for now");
        Notes adverseEffectsOfChemicals = new Notes("a large book titled \"Adverse effects of chemical compounds, Volume 51\"",
                "The book is filled with dry scientific explanations",
                "It's very short with pictures",
                "You challenge yourself to read through one of the paragraphs",
                "You power through and start reading",
                "\"Strong acids such as sulfuric acid H2SO4 can cause serious damage when ingested in concentrations as low as 1 milimolar\"",
                "\"With the addition of the base sodium hydroxide NaOH and organic L-isoleucine to the acid, the resulting compound was shown by Kekevar, et al. (1987) to have various healing properties\"",
                "Your head already hurts",
                "That's enough chemistry for now");
        Notes secretResearchNotes = new Notes("a lab journal",
                "You start reading through the journal",
                "The handwriting is very neat for a lab worker",
                "\"Oct 20: We've finally received the lab rabbits that we requested for our testing\"",
                "        \"The bigger one is called Fluffy, and the smaller one is Dean\"",
                "        \"Maybe I shouldn't give then names - it will only make working with them harder..\"",
                "        \"Oh I can't help it, too bad! I'm calling them Fluffy and Dean\"",
                "\"Oct 21: The rabbits were injected with 10mL of infectious solution\"",
                "        \"The injection was successful and the rabbits show no signs of distress\"",
                "        \"For this concentration symptoms are predicted to start in 1-2 hours\"",
                "        \"I hope we can find a way to cure them before it's too late\"",
                "\"Oct 22: The rats were each injected with 2mL of infectious solution\"",
                "        \"They immediately became very distressed and aggressive upon injection\"",
                "        \"Note: notify Albert about the rats\"",
                "The next few pages are filled with countless reaction diagrams",
                "\"Dec 07: Note: I think I'm on to something here, just a bit more.. you can do it Jane! :)\"",
                "\"Dec 11: Rats were injected with a mixture of acetic acid, metallic silver, and organic tetravinyllead\" 15mM",
                "\"Dec 12: Rats show signs of improvement! I've found a cure for the virus!\"",
                "\"Dec 13: Dean was cured as well, it actually works! Oh I'm gonna be so famous!\"",
                "        \"Need further testing before I go public with this\"",
                "        \"Note: stop writing so many notes - this is now top-secret research\"",
                "\"Dec 15: The rats show increasingly aggressive behaviour - side effect?\"",
                "\"Dec 20: Fluffy injected with a mixture of acetic acid and metallic silver 100mM\"",
                "        \"No adverse effects so far\"",
                "\"Dec 27: Dramatic increase in size, Fluffy now weighs 8 kilos\"",
                "        \"Note: notify Albert about increasing food supply\"",
                "\"Dec 31: Happy new year Fluffy and Dean - man being a researcher takes a toll on your life\"",
                "        \"But it's totally worth it Jane!\"",
                "\"Jan 02: Fluffy is increasingly aggressive\"",
                "\"Jan 03: Fluffy's cage broke open last night and he's missing\"",
                "        \"Note: Did he actually break the cage himself???\"",
                "\"Jan 04: I think I'm developing symptoms of the virus - I'll stay home for a while\"",
                "That was the last page in the notebook");
        Notes utilityNote = new Notes("a small note stuck to the wall",
                "You read the note",
                "It says \"Fix the damn electrical outage.. again..\"",
                "I guess they never got around to doing that");
        Notes maintenanceNote = new Notes("a small yellow note stuck to the wall",
                "You read the note",
                "It says \"TODO: Help Bob fix the electrical outage\"",
                "I guess they never got around to doing that");
        Notes conferencePosterAI = new Notes("a small poster",
                "You approach the poster",
                "It's a listing of a bunch of AI talks that were supposed to take place at the school",
                "\"Natural language processing for music information technology\"",
                "\"Understanding anomalous network behaviour with AI and ML\"",
                "\"Data science for business\"",
                "Some of these sound mildly interesting",
                "Too bad the school was closed",
                "They were all probably cancelled");
        Notes conferencePosterNanotech = new Notes("a large poster",
                "You approach the poster",
                "It's a listing of some conference talks that were supposed to take place in the city",
                "\"Characterization and modeling of Nanostructures and Devices\"",
                "\"Nanotechnology in Materials Science\"",
                "\"Advanced Ceramics and Composite Materials\"",
                "\"Smart Materials and Technology\"",
                "Some of these sound pretty interesting",
                "Too bad the school was closed",
                "They were all probably cancelled");
        Notes conferencePosterBio = new Notes("a large poster",
                "You approach the poster",
                "It's a listing of some biology talks that were supposed to take place",
                "\"Mathematics yields biological insights\"",
                "\"Strings, Trees, and RNA Folding\"",
                "\"What should biological theory look like?\"",
                "These all sound kind of interesting",
                "But you don't really know anything about biology");
        Notes conferencePosterMath = new Notes("a small dusty poster",
                "You approach the poster",
                "It's a listing of a bunch of mathematics talks that were supposed to take place at the school",
                "\"Foliation Theory and Complex Geometry\"",
                "\"New Directions in Representation Theory\"",
                "\"Random Graphs\" - I guess that's a talk as well",
                "\"Foundations and Frontiers of Probabilistic Proofs\"",
                "These all sound incredibly complicated",
                "But they were probably all cancelled anyway");
        Notes studyAssociationAdvertising = new Notes("a bright eye-catching poster",
                "This poster seems to be some sort of advertising for a so called \"study association\"",
                "They're asking for new members and advertising all the \"numerous perks\" of joining",
                "Ugh");
        Notes studySessionSignUp = new Notes("a dull white poster",
                "A sign up sheet for some maths study session that was supposed to take place months ago",
                "Only 2 people seem to have signed up");
        Notes researchPosterBulletinBoardChemistry = new Notes("a large bulletin board overflowing with research posters",
                "There are so many research posters on this board",
                "\"Theoretical Investigation on Structure-Property Relationship of Asymmetric Clusters\"",
                "\"Protective Effect of Coconut Oil Meal Phenolic Antioxidants against Macromolecular Damage: In Vitro and In Vivo Study\"",
                "\"Synthesis of Phenols via Metal-Free Hydroxylation of Aryl Boronic Acids with Aqueous TBHP\"",
                "There's many more",
                "These all seem to be chemistry - you think");
        Notes researchPosterBulletinCS = new Notes("a bulletin board filled with countless with research posters",
                "There's a lot of research posters on this bulletin board",
                "You look through some of them",
                "\"Identification of markers and artificial intelligence-based classification of radical twitter data\"",
                "\"ODCR: Energy Efficient and Reliable Density Clustered-based routing protocol for emergency sensor applications\"",
                "\"Swarm intelligence versus direct cover algorithms in synthesis of Multi-Valued Logic functions\"",
                "They all sound pretty interesting",
                "There's many more",
                "But you don't have much time to waste now");
        Notes researchPosterBulletinBoardPhysics = new Notes("a large bulletin board covered in research posters",
                "There are a couple of research posters on this board",
                "\"Propagation properties of quadrupole breather in nonlinear media with a nonlocal exponential-decay response\"",
                "\"Quantum information-entropic measures for exponential-type potential\"",
                "\"Multiwall carbon nanotube enhance the invisibility effect from radar\"",
                "You don't even have the faintest idea of what any of this means");

        computerRoom.addContents(mathHomework);
        computerRoom.addContents(chemistryHomework);
        entrance.addContents(entranceNotes);
        kitchen.addContents(kitchenNotes1);
        kitchen.addContents(kitchenNotes2);
        crampedClassroom.addContents(doodles);
        spaciousClassroom.addContents(mathNotes);
        organicChemistryLab.addContents(organicReactions);
        organicChemistryLab.addContents(adverseEffectsOfChemicals);
        nanoTechnologyLab.addContents(adverseEffectsOfChemicals);
        topSecretResearchLab.addContents(secretResearchNotes);
        utilityRoom.addContents(utilityNote);
        maintenanceRoom.addContents(maintenanceNote);
        mainHall.addContents(studySessionSignUp);
        mainHall.addContents(studyAssociationAdvertising);
        atrium.addContents(studySessionSignUp);
        atrium.addContents(studyAssociationAdvertising);
        eastBreakRoom.addContents(studyAssociationAdvertising);
        westBreakRoom.addContents(studyAssociationAdvertising);
        northEastHallway.addContents(studySessionSignUp);
        northEastHallway.addContents(researchPosterBulletinBoardChemistry);
        westHallway.addContents(researchPosterBulletinBoardPhysics);
        contaminatedHallway.addContents(researchPosterBulletinCS);
        contaminatedHallway.addContents(conferencePosterAI);
        officeRoom.addContents(conferencePosterAI);
        mainHall.addContents(conferencePosterNanotech);
        atrium.addContents(conferencePosterBio);
        atrium.addContents(conferencePosterMath);
        northWestHallway.addContents(conferencePosterAI);
        northWestHallway.addContents(conferencePosterMath);
        eastBreakRoom.addContents(conferencePosterNanotech);

        BunnyRabbit dean = new BunnyRabbit("Dean");
        RabbitCage deanCage = new RabbitCage(dean, topSecretResearchLab);

        Notes brokenCage = new Notes("A small broken cage labelled \"Fluffy\"",
                "The cage seems to have been broken through sheer force from the inside",
                "Scary",
                "There's a small empty bowl on the inside and another bowl filled with water");
        topSecretResearchLab.addContents(brokenCage);

        Player player = new Player(entrance);

        Initializer.setPropertiesWithDefault("rpgConfig");

        switch (initMethod) {
            case NORMAL:
                return player;
            case FROM_CONFIG:
                return loadProperties(player);
            case RESET_CONFIG:
                Initializer.createNewDefaultProperties("rpgConfig");
                Console.printLine("How would you like to start the game now?");
                Console.printLine("  (1) Play Normally");
                Console.printLine("  (2) Initialize from the default configs");
                String input = Console.readString();
                if (input.equals("1")){
                    Console.printWithPause("Playing normally");
                    return player;
                } else {
                    Console.printWithPause("Initialising from new configs");
                    return loadProperties(player);
                }
            default:
                throw new EnumConstantNotPresentException(initMethod.getClass(), "GameInitializer was not constructed with a valid InitMethod");
        }
    }

    /**
     * Tries to load the properties of a Player from a Java properties config file.
     * If the file can successfully be read the Player is initialized from the file.
     * Otherwise, an error message is printed out, and the Player is returned without change.
     *
     * @param player The Player whose properties to initialize from a config file.
     * @return The initialized Player.
     * @see Player
     */
    public Player loadProperties(Player player) {
        try {
            return Initializer.initPlayerFromProperties(player, "rpgConfig");
        } catch (IOException e) {
            Console.printWithPause("Could not load configs");
            Console.printWithPause("Initializing player without default configs");
            return player;
        }
    }


    /**
     * @param probabilityOfTrue probability that the function returns true
     * @return true with a given probability.
     */
    private boolean coinFlip(double probabilityOfTrue) {
        return rand.nextDouble() < probabilityOfTrue;
    }
}
