import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;
/**
 *  This class is the main class of the "Lennox Castle" application. 
 *  "Lennox Castle" is a very simple, text based adventure game.  Users 
 *  can walk around a castle, pick up items and interact in a simple way with characters.
 *  
 *  The objective of this game is to reach the treasury room with sufficient items to win.
 *  There are three varied ending messages, depending on what items the player has.
 *  
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms; the characters and the items, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Jacob
 * @version 03/12/2017
 */

public class Game 
{
    private Parser parser;                      // The parser for reading user input
    private Player player;                      // An instance of class 'Player' that contains player data
    private HashSet <Characters> characters;    // A list of game characters
    private ArrayList <Room> jumpRooms;         // A list of rooms in the game to which the player can be transported
    private Random randomGenerator;             // A random number generator
    private HashMap <Items, Items> tidyMap;     // Relationship of items for the 'put' command
    /**
     * Create the game and initialise its state.
     */
    public Game() 
    {
        player = new Player();
        randomGenerator = new Random();
        parser = new Parser();
        characters = new HashSet<>();
        jumpRooms = new ArrayList<>();
        tidyMap = new HashMap<>();
        createGameState();
    }

    /**
     * Sets the initial game state by doing the following.
     * Creates the rooms and furnishes them, giving the items their attributes.
     * Creates the characters and positions them.
     * Creates the map of rooms and sets their exits.
     * Start the player in the 'entrance' room.
     */
    private void createGameState()
    {
        Room        teleporter, library, dungeons, tower, hall, entrance, treasury, bedroom, kitchen, storeroom, musicRoom, diningRoom;
        Items       treasuryKey, sword,  ring,  sweets, axe, needle, box, searchCrystal, rose, flute, cushion, chains, throne,
        suitOfArmour, haystack, diningRoomTable, historyBook, spoon, bread, candlesticks, rack;
        Characters  adventurer, orc, goblin, troll, skeleton;

        // create the rooms and add those that can be teleported to into the array list
        teleporter = new Room("teleporter", "in the castle teleporter");
        treasury = new Room("treasury", "in the treasury");
        dungeons = new Room("dungeons", "visiting the dungeons");
        jumpRooms.add(entrance = new Room("entrance", "in the entrance hall"));
        jumpRooms.add(library = new Room("library", "in the library"));
        jumpRooms.add(hall = new Room("hall", "in the great hall"));
        jumpRooms.add(diningRoom = new Room("dining room", "in the dining room"));
        jumpRooms.add(musicRoom = new Room("music room", "in the music room"));
        jumpRooms.add(tower = new Room("tower", "in the tall tower"));
        jumpRooms.add(bedroom = new Room("bedroom", "in the bedroom"));
        jumpRooms.add(kitchen = new Room("kitchen", "in the kitchen"));
        jumpRooms.add(storeroom = new Room("storeroom", "in the storeroom"));

        // create the items, setting their weight, whether they can be picked up and whether they can be found without the search crystal

        // items that can be picked up and are useful

        treasuryKey = new Items("key", "it is gold");
        treasuryKey.setWeight(3);
        treasuryKey.setPickUp(true);
        treasuryKey.setIsBasic(false); // can not be found without the search crystal

        ring = new Items("ring", "it is set with a jewel of brilliant radiance");
        ring.setWeight(2);
        ring.setPickUp(true);
        ring.setIsBasic(false); // can not be found without the search crystal

        sword = new Items("sword", "it is a weapon of good size and craft");
        sword.setWeight(6);
        sword.setPickUp(true);

        sweets = new Items("sweets", "they are neatly wrapped and appear to be strawberry flavour");
        sweets.setWeight(1);
        sweets.setPickUp(true);

        axe = new Items("axe", "it is blunt");
        axe.setWeight(3);
        axe.setPickUp(true);

        searchCrystal = new Items("crystal", "it is a dull purple");
        searchCrystal.setWeight(4);
        searchCrystal.setPickUp(true);

        rose = new Items("rose", "it is a red paper flower");
        rose.setWeight(1);
        rose.setPickUp(true);

        cushion = new Items("cushion", "it looks rather prestigious");
        cushion.setWeight(5);
        cushion.setPickUp(true);

        flute = new Items("flute", "it has been repaired with great care");
        flute.setWeight(4);
        flute.setPickUp(true);

        needle = new Items("needle", "it is very small");
        needle.setWeight(1);
        needle.setPickUp(true);

        candlesticks = new Items("candlesticks", "they are designed as if to light a dinner");
        candlesticks.setWeight(7);
        candlesticks.setPickUp(true);

        chains = new Items("chains", "very dark in colour, they seem to emanate suffering");
        chains.setWeight(8);
        chains.setPickUp(true);

        // items that can be picked up but serve no real purpose

        spoon = new Items("spoon", "it is silver and has been bent out of shape");
        spoon.setWeight(2);
        spoon.setPickUp(true);

        bread = new Items("bread", "it is definitely stale");
        bread.setWeight(2);
        bread.setPickUp(true);

        box = new Items("box", "it is very heavy");
        box.setWeight(11);
        box.setPickUp(true);

        // items that can not be picked up

        throne = new Items("throne", "a majestic golden chair but looks uncomfortable");
        throne.setWeight(20);

        rack = new Items("rack", "a cruel torture device, it seems to be missing something");
        rack.setWeight(10);

        suitOfArmour = new Items("armour", "tall and intimidating but unarmed");
        suitOfArmour.setWeight(15);

        haystack = new Items("haystack", "there is nothing in it");
        haystack.setWeight(7);

        diningRoomTable = new Items("table", "it is very large but too dark to have dinner");
        diningRoomTable.setWeight(15);

        historyBook = new Items("book", "it details the extensive history of the castle");
        historyBook.setWeight(4);

        // Set which items can be put on which

        tidyMap.put(needle,haystack);
        tidyMap.put(candlesticks,diningRoomTable);
        tidyMap.put(chains,rack);
        tidyMap.put(axe, suitOfArmour);
        tidyMap.put(cushion, throne);

        // furnish the rooms with their initial items

        dungeons.addItem(searchCrystal);
        dungeons.addItem(rack);

        entrance.addItem(axe);
        entrance.addItem(cushion);

        library.addItem(historyBook);
        library.addItem(rose);

        hall.addItem(throne);
        hall.addItem(sword);
        hall.addItem(candlesticks);

        diningRoom.addItem(diningRoomTable);
        diningRoom.addItem(sweets);

        musicRoom.addItem(flute);
        musicRoom.addItem(bread);

        tower.addItem(suitOfArmour);
        tower.addItem(box);

        bedroom.addItem(ring);
        bedroom.addItem(needle);

        kitchen.addItem(treasuryKey);
        kitchen.addItem(spoon);

        storeroom.addItem(haystack);
        storeroom.addItem(chains);

        // create the characters, set their initial locations and set their accepted items

        characters.add(adventurer = new Characters("adventurer"));
        adventurer.setCurrentRoom(dungeons);

        characters.add(skeleton = new Characters("skeleton"));
        skeleton.setCurrentRoom(kitchen);

        characters.add(orc = new Characters("orc"));
        orc.setCurrentRoom(tower);
        orc.setAcceptedItem(rose);

        characters.add(goblin = new Characters("goblin"));
        goblin.setCurrentRoom(teleporter);
        goblin.setAcceptedItem(sweets);

        characters.add(troll = new Characters("troll"));
        troll.setCurrentRoom(storeroom);
        troll.setAcceptedItem(flute);

        // initialise room exits

        teleporter.setExit("east", library);

        treasury.setExit("south",library);

        dungeons.setExit("east", storeroom);

        entrance.setExit("west", library);
        entrance.setExit("north", hall);
        entrance.setExit("east", diningRoom);
        entrance.setExit("up", bedroom);

        library.setExit("east", entrance);
        library.setExit("west", teleporter);
        library.setExit("north", treasury);

        hall.setExit("east", musicRoom);
        hall.setExit("up", tower);
        hall.setExit("south", entrance);

        diningRoom.setExit("west", entrance);
        diningRoom.setExit("north", musicRoom);
        diningRoom.setExit("down", kitchen);

        musicRoom.setExit("south", diningRoom);
        musicRoom.setExit("west", hall);

        tower.setExit("down", hall);
        tower.setExit("south", bedroom);

        bedroom.setExit("north", tower);
        bedroom.setExit("down", entrance);

        kitchen.setExit("up", diningRoom);
        kitchen.setExit("north", storeroom);

        storeroom.setExit("south", kitchen);
        storeroom.setExit("west", dungeons);

        // start game in the 'entrance' room
        player.setCurrentRoom(entrance); 
        player.addRoom(entrance);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        boolean quit = false;
        boolean gameOver = false;

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until either the user quits the game or the game is over.
        while (! gameOver && !quit) {
            Command command = parser.getCommand();
            quit = processCommand(command);
            gameOver = processEndGame();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Lennox Castle!");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Lennox, the owner of this castle, plans to use monsters of his creation");
        System.out.println("to steal gold to fill his treasury!");
        System.out.println("You are not the first to try to stop him, but you haved timed your visit well.");
        System.out.println("The castle is mostly empty at the moment, now is your chance to foil his plans!");

        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command; the command to be processed.
     * @return true if the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("knapsack")) {
            listKnapsackContents(command);
        }
        else if (commandWord.equals("search")){
            searchRoom(command);
        }
        else if (commandWord.equals("ask")){
            askCharacter(command);
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("back")) {
            goBack(command);
        }
        else if (commandWord.equals("forward")) {
            goForward(command);
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        else if (commandWord.equals("give")){
            giveItem(command);
        }
        else if (commandWord.equals("put")){
            putItem(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a message and a list of the 
     * command words with a description of the command
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the castle.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * List the items in the knapsack.
     */
    private void listKnapsackContents(Command command) 
    {
        // list knapsack contents
        player.getKnapsack().listKnapsackContents();
    } 

    /** 
     * Search the contents of room. Displays the items in the room.
     * To display all items the 'search crystal' must be in the knapsack.
     */
    private void searchRoom(Command command) 
    {
        // Search current room.
        if (player.getKnapsack().findInKnapsack("crystal") == null ){
            System.out.println("You look around the room and you find : ");
            player.getCurrentRoom().listBasicItems();
            return;
        }
        System.out.println(" The strange crystal glows, you find : ");
        player.getCurrentRoom().listAllItems();
        player.getCurrentRoom().setCrystalUsed(true);   // enables non basic items in the room to be taken
    }

    /** 
     * See what a character has to say or display an error.
     */
    private void askCharacter(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("'ask' requires a character");
            return;
        }

        String characterName = command.getSecondWord();
        Characters gameCharacter = isCharacter(characterName); // check if the second word is a valid character

        if(gameCharacter == null){
            System.out.println("There is no such character");
            return;
        }

        // Check if the character is in the room
        if (!(gameCharacter.getCurrentRoom().getName().equals(player.getCurrentRoom().getName()))){
            System.out.println("" + characterName + " is not here");
            return;
        }  // signal that we want to quit

        gameCharacter.printResponse();
    }

    /** 
     * Try to go in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("'Go' must include direction");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return;
        }

        //See if you have permission to enter the desired room
        if (nextRoom.getName().equals("dungeons") && player.getHasDungeonPass() == false){
            System.out.println( "You need a pass");
            return;
        }

        if ((nextRoom.getName().equals("treasury")) && (player.getKnapsack().findInKnapsack("key") == null)){
            System.out.println("This door is locked");
            return;
        }

        // If the player has recently used the 'back' command going somewhere else removes rooms the player has gone back from 
        if(!player.checkAtEnd()){
            player.clearNeedlessRooms();
        }

        //Go to the room and add the new room to the trail
        player.setCurrentRoom(nextRoom);
        
        //Process new room actions
        newRoom();
        player.addRoom(player.getCurrentRoom());
    }

    /** 
     * Return to the previous room.
     */
    private void goBack(Command command) 
    {
        if (player.roomStackEmpty() || (player.getStackPosition() == 0)) {
            System.out.println("No previous room applicable");
            return;
        }
        player.back();

        //Process new room actions
        newRoom();
    }

    /** 
     * Go forward to rooms previously visited.
     */
    private void goForward(Command command) 
    {
        if(player.checkAtEnd()){
            System.out.println("No previous room applicable");
            return;
        }
        player.forward();

        //Process new room actions
        newRoom();
    }

    /** 
     * Attempt to pick up an item. Display the appropriate error message if unavailable.
     */
    private void takeItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("'take' requires an item");
            return;
        }

        String itemName = command.getSecondWord();
        Items item = player.getCurrentRoom().findInRoom(itemName); // check the required item is present

        if(item == null){
            System.out.println("There is no such item");
            return;
        }

        // Special processing for 'book' item        
        if (item.getName().equals("book")){
            System.out.println("You read the book and find little of interest.");
            System.out.println("You decide to put it back");
            return;
        }

        if(item.getPickUp() == false){
            System.out.println("This item can not be picked up");
            return;
        }

        // If the room has not been searched with the 'search crystal' item, any non basic items have not been found
        if((player.getCurrentRoom().getCrystalUsed() == false) && (item.getIsBasic() == false)){
            System.out.println("You can not see this item");
            return;
        }

        // If collecting this item exceeds the maximum weight give an error
        if(player.getKnapsack().checkWeight(item)){
            System.out.println("It's too heavy! Please drop an item first.");
            return;
        }

        // If we are here the item can be picked up
        player.takeItem(item);
        System.out.println("You've got " + itemName);

    }

    /** 
     * Remove an item from the knapsack or display an error.
     */
    private void dropItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("'drop' requires an item");
            return;
        }

        String itemName = command.getSecondWord();
        Items item = player.getKnapsack().findInKnapsack(itemName); // check if the player has the item

        if(item == null){
            System.out.println("You do not have this item");
            return;
        }

        // If we are here the item can be dropped
        player.dropItem(item);
        System.out.println("You dropped " + itemName);

    }

    /** 
     * Give an item to a character or display an error.
     */
    private void giveItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to do...
            System.out.println("'give' requires a character and an item");
            return;
        }

        String characterName = command.getSecondWord();
        Characters gameCharacter = isCharacter(characterName); // check if the second word is a valid character

        if(gameCharacter == null){
            System.out.println("The second word is not a valid character");
            return;
        }

        // Check if the character is in the room
        if (!(gameCharacter.getCurrentRoom().getName().equals(player.getCurrentRoom().getName()))){
            System.out.println("" + characterName + " is not here");
            return;
        }

        if(!command.hasThirdWord()) {
            // If there is no third word, we don't know what to give...
            System.out.println("'give' requires an item");
            return;
        }

        String itemName = command.getThirdWord();
        Items item = player.getKnapsack().findInKnapsack(itemName); // Check if the player has the item

        if(item == null){
            System.out.println("You do not have this item");
            return;
        }

        // Check whether the character wants an item
        if(gameCharacter.getHasAcceptedItem()){
            System.out.println("" + characterName + " does not want anything");
            return;
        }

        // Check whether the character wants this item
        if(!(gameCharacter.getAcceptedItem().getName().equals(itemName))){
            System.out.println("" + characterName + " does not want this");
            return;
        }

        // If we get here the item can be given to the character
        player.dropItem(item);
        player.getCurrentRoom().removeItem(item); // remove the item from the rest of the game
        gameCharacter.setHasAcceptedItem(true);   // the character now has their desired item  
        System.out.println("You gave " + itemName + " to " + characterName);

        // Check whether all characters have their desired items. If so give the player the dungeon pass, allowing access to the dungeons.
        if(everybodyHappy()){
            player.setHasDungeonPass(true);
            System.out.println("You got the dungeon pass");
        }

    }

    /** 
     * Stack an item on another item. Finding all combinations allows for a different ending.
     */
    private void putItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to do...
            System.out.println("'put' requires two items");
            return;
        }

        String itemNameOne = command.getSecondWord();
        Items itemOne = player.getKnapsack().findInKnapsack(itemNameOne); // Item 1 must be in the player's knapsack

        if(itemOne == null){
            System.out.println("You do not have this item to put");
            return;
        }

        if(!command.hasThirdWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("'put' requires a second item");
            return;
        }

        String itemNameTwo = command.getThirdWord();
        Items itemTwo = player.getCurrentRoom().findInRoom(itemNameTwo); // Item 2 must be in the room

        if(itemTwo == null){
            System.out.println("The " + itemNameTwo + " is not available");
            return;
        }

        // Check whether this combination of items is valid
        if( !(tidyMap.get(itemOne) == itemTwo) ){
            System.out.println("This item does not belong here");
            return;
        }

        // If we are here then the items can be stacked
        player.dropItem(itemOne);
        player.getCurrentRoom().removeItem(itemOne); // Remove item 1 from the game
        player.getCurrentRoom().removeItem(itemTwo); // Remove item 2 from the game
        player.incrementNumberOfObjectsTidied();     // One more item has been put in its correct place

        System.out.println("You put " + itemNameOne + " on " + itemNameTwo);

        // Check if all combinations have been completed
        if(player.getNumberOfObjectsTidied() == tidyMap.size()){
            player.setTidiedCastle(true);    // Note that the player has found all combinations
            System.out.println("You have tidied the castle!");
            System.out.println("The skeleton appears and thanks you for efforts.");
            System.out.println("He says he will see to the rest.");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("If you want to quit type 'quit'");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /** 
     * Check whether conditions for winning the game have been met.
     * @return true if win conditions have been met.
     */
    private boolean processEndGame()
    {
        // The player must be in the treasury
        if(!(player.getCurrentRoom().getName().equals("treasury"))){
            return false;
        }

        // The player must have the 'ring' item 
        if (player.getKnapsack().findInKnapsack("ring") == null){
            System.out.println("A man stands surrounded by gold.");
            System.out.println("He waves his hands and you feel an unnatural force pass through you");
            player.setCurrentRoom(randomRoom());
            newRoom();
            return false;
        }

        // If we get here the player has won
        win();
        System.out.println("YOU WIN!!!!!");
        return true;
    }

    /** 
     * Prints the appropriate end message
     */
    private void win()
    {
        System.out.println("A man stands surrounded by gold.");
        System.out.println("He waves his hands and you feel an unnatural force pass through you");
        System.out.println("The feeling passes as your knapsack glows");
        System.out.println("You reveal the ring and the man steps back in terror");

        if (player.getKnapsack().findInKnapsack("sword") != null){
            System.out.println("You raise your sword at the man and he becomes angry.");
            System.out.println("He attacks you at full force but you defeat him");
            System.out.println("You've achieved the violent ending!");
            return;
        }

        // Check if all items in the castle have been put in their correct locations
        if (player.getTidiedCastle()){
            System.out.println("He regains his composure and smiles");
            System.out.println("You helped my friends, I will give up on my plans");
            System.out.println("You've achieved the peaceful ending!");
            return;
        }

        System.out.println("He runs from the room and is never heard from again");
        System.out.println("You've achieved the standard ending!");
    } 

    /** 
     * The player enters a new room where further actions will occur
     */
    private void newRoom()
    {
        // the teleporter room takes the player to another random room
        if(player.getCurrentRoom().getName().equals("teleporter")){
            player.setCurrentRoom(randomRoom());
        }

        randomMonsterRooms();               // certain monsters have their locations randomised

        System.out.println(player.getCurrentRoom().getLongDescription());   // Describe the new room
        findCharacters();       // See who is in there
    }

    /** 
     * Print which character are in the room
     */
    private void findCharacters()
    {
        for (Characters gameCharacter : characters){
            if (gameCharacter.getCurrentRoom().getName().equals(player.getCurrentRoom().getName())){
                System.out.println(gameCharacter.getName() + " is present");
            }
        }
    }

    /** 
     * Check if a string is a valid character.
     * @param the string to be check
     * @return the character that was found
     */
    private Characters isCharacter(String characterName)
    {
        for(Characters gameCharacter : characters) {
            if(gameCharacter.getName().equals(characterName))
                return gameCharacter;
        }
        // if we get here, there is no such character
        return null;
    }

    /** 
     * Generates a random room except for the 'teleporter', 'treasury' and 'dungeons'.
     * @return the generated room.
     */
    private Room randomRoom()
    {
        int roomNumber = randomGenerator.nextInt(jumpRooms.size());
        return jumpRooms.get(roomNumber);
    }

    /** 
     * Puts the goblin and skeleton in random rooms except for the 'teleporter', 'treasury' and 'dungeons'.
     */
    private void randomMonsterRooms()
    {
        for (Characters gameCharacter : characters){
            if (gameCharacter.getName().equals("goblin") || gameCharacter.getName().equals("skeleton")){
                gameCharacter.setCurrentRoom(randomRoom());
            }
        }
    }

    /** 
     * Checks whether every character has their desired item.
     * @return true if every character has their desired item
     */
    private boolean everybodyHappy()
    {
        for (Characters gameCharacter : characters){
            if(!(gameCharacter.getHasAcceptedItem())){
                return false;
            }
        }
        return true;
    }
}