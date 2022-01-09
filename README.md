# Lennox-Castle
A text game based off zuul

User level description

This is a text based adventure game in which the player is required to travel through different rooms and collect and use items to win.  Type various commands to navigate the game and stop Lennox from achieving his goal. 

Brief implementation description

This project has nine different classes of which the primary class is the Game class.  The game class collects and uses information from the other classes to make the game run and contains the play method. 

The Command, CommandWords and Parser classes serve to receive player input and store it in the form of a three word command.  The Room, Items, Characters, Player and Knapsack classes contain information about each instance of these object types that are created in the Game class.  The Knapsack instance is created in the Player class. 

Base Tasks

	The game has several locations/rooms

There is a Room class.  The Game class creates twelve objects of type Room and adds some of them to the jumpRooms HashSet (those rooms that can be teleported to). 

	The player can walk through the locations. 

There is a goRoom method in the Game class that is called when the Parser receives an input beginning with ‘go’.  If the right conditions are met it modifies the currentRoom field of the Player instance, created in the constructor, using the exits HashMap in the Room class and the setCurrentRoom method in the Player class.  The exits are set when the rooms are created in the createGameState method. The goRoom method also adds the room just left to the previousRooms ArrayList in the Player class using its addRoom method which makes the necessary adjustment to the stack position. 

	There are items in some rooms.  Every room can hold any number of items.  Some items can be picked up by the player, others can’t. 

There is an Items class.  The Game class creates many instances of this Items class and adds them to a the items HashSet in the Room class for their respective starting locations.  The Player can pick up items through use of the takeItem method in the Game class called when the Parser receives an input beginning with take. The take command is a two word command of the from take item.  If the right conditions are met, it adds the item to the items HashSet in the Player’s Knapsack instance.  The player’s Knapsack instance is created in the constructor of the Player class.  The takeItem method in the game adds the item to the player’s knapsack by using the takeItem method in the Player class. This calls the addItem method in the Knapsack class and also removes the item from the HashSet in the player’s current room by calling the removeItem method in the Room class.  
Each item has a canBePickedUp boolean which is checked in the takeItem method in the Game class.  The item can only be picked up if that boolean is true.  When the items are created in the Game class those which can be picked up have that boolean set to true. 

	The player can carry some items with him.  Every item has a weight.  The player can carry items only up to a certain total weight. 

The Knapsack class contains all items collected by the Player in a HashSet.  There are methods within the Knapsack class that can be used to check if the Player has an item.  Items in the knapsack do not exist in the items HashSet of any room.  The Items class has a weight field which is set in the Game class when the item is created.  The Knapsack class has a maxWeight field which is of type final to store the maximum weight of items that can be carried in the knapsack.  The Knapsack class also has a checkWeight method to see if adding an item would cause the maximum weight to be exceeded.  This is used by the Game class in the takeItem method to make sure that an item can not be picked up if it would cause the maximum weight to be exceeded. There is a drop command which enables the player to remove items from their knapsack to make space. The drop command is a two word command of the form drop item. When the parser receives an input beginning with drop, the dropItem method in the Game class is called. If the right conditions are met, this method removes the item from the player’s knapsack by using the dropItem method in the Player class. This calls the removeItem method in the Knapsack class and also adds the item to the HashSet in the player’s current room by calling the addItem method in the Room class. 

	The player can win.  There has to be some situation that is recognised as the end of the game where the player is informed that he/she has won. 

The play method in the Game class has a boolean called gameOver that must be false for the game to continue.  After every command is processed it is set equal to the return value of the processEndGame method.  This method always returns false unless the conditions required for winning the game have been met.  These conditions are that the player is in the treasury room with the ring item.  If the player has won, the processEndGame method will inform the player of this by printing a different victory message depending on the ending achieved. 

	Implement a command “back” that takes you back to the last room you’ve been in. 

‘Back’ and ‘Forward’ were added to the array of valid command words in the CommandWords class.  They are both single word commands.  There is a method goForward in the Game class that is called when the parser receives an input beginning with ‘forward’ and a method goBack that is called when the parser receives an input beginning with ‘back.  The back command allows the player to navigate back through every previous room visited in order and the forward command allows the player to return through the list.  These previous rooms are stored in the roomStack ArrayList in the Player class.  There is a roomStackPosition variable in the Player class that keeps track of where in the list the player is and is decremented when goBack is called and incremented when the player enters a new room and when goForward is called. When back is input, the stack position is decremented and then the player’s room is changed to the room at the stack position using the getStackPositionRoom method and setCurrentRoom method, both in the Player class.  Similarly, when forward is input the position is incremented sending the player forward through the list. Using the go command after back triggers the clearNeedlessRooms method in the Player class. This removes all rooms beyond the room stack position, to remove them from the rooms that can be reached by using forward, from the ArrayList. The teleporter and treasury rooms should not be reached by using back and forward so they are not added to the roomStack list when entered.

	Add at least four new commands (in addition to those that were present in the code you got from us).

The validCommands array in the CommandWords class now contains twelve commands instead of just three.  Each of these command words has been added to processCommands in the Game class and the matching method in the Game class has been created for when the command is input. The following commands can be used: help, knapsack, search, ask, go, back, forward, take, drop, give, put, quit.

Challenge Tasks

	Add characters to your game.  Characters are people or animals or monsters – anything that moves, really.  Characters are also in rooms (like the player and the items).  Unlike items, characters can move around by themselves. 

There is a Characters class.  Characters are created by the Game class in the createGameState method that is called in the constructor and added to the characters HashSet.  The Characters class has a field currentRoom which stores the character’s location.  The game class has a method randomMonsterRooms which randomises the current room of the goblin and skeleton characters and is called every time the player moves rooms. 

	Extend the parser to recognise three-word commands.  

There is a new variable in the getCommand method in the Parser class, word3, which as with word1 and word2 stores the third word of input from the user.  In the Command class there is a field thirdWord that will contain this value and methods getThirdWord and hasThirdWord.  There are two three word commands, the give command and the put command.  Both of their respective methods in the Game class contain conditions to check for whether or not the input has a third word and to output an error if not. The getCommand method has also been modified using the toLowerCase method in the string class such that upper case versions of acceptable commands are treated the same way as their lower case counterparts.

	Add a magic transporter room – every time you enter it you are transported to a random room in your game. 

The createGameState method in the Game class creates a room ‘teleporter’ which is located west of the library.  The goRoom method checks whether this room has been entered and if it has sets the player’s current room to a random room using the randomRoom method.  This method uses the Random type field randomGenerator to generate a random number within the bounds of the jumpRooms HashSet and returns the room of that index. This method is also called in the randomMonsterRooms method.  Certain rooms such as the teleporter room itself are not included in the jumpRooms HashSet so can not be teleported to. 

	The put command/ tidying

The put command can be used to place one item on/in another.  It is a three word command of the format put item1 item2 where item1 is in the player’s knapsack and item2 is in the current room.  There is a method putItem in the Game class that is called when the parser receives an input beginning with put.  This method removes both items from the game by removing them from the knapsack/room using their respective methods.  It then displays a message informing the player of their success.  The possible combinations are stored in a HashMap in the Game class and if the player attempts to put items that do not go together it will fail and the user will be informed.  If the player successfully finds all combinations a boolean, tidiedCastle in the Player class will be set to true, which is necessary for the peaceful ending, and a message will be printed. 

	Ask

The ask command can be used to receive a response from a character.  It is a two word command; of the format ask character where character is a character in the same room as the player.  There is a method askCharacter in the Game class that is called when the parser receives an input beginning with ‘ask’.  This method checks whether conditions are met and then calls the printResponse method in the Characters class.  The adventurer and skeleton have one standard response each and the other characters have two responses that depend on whether or not the player has given them a particular item. 

	Give/dungeon pass

The give command can be used to give an item to a character.  It is a three word command of the format give item character where item is an item in the player’s knapsack and character is a character in the same room as the player.  There is a method giveItem in the Game class that is called when the parser receives an input beginning with ‘give’.  Each character has a particular item they accept which is set using the setAcceptedItem method in the Characters class when they are created.  If it possible to give the item to the character then the giveItem method removes the item from the knapsack and thus the game and uses the setHasAcceptedItem method in the Characters class to indicate the character has received their item.  The everybodyHappy method in the Game class checks whether every character has their item and if that is the case then upon giving the final character their item the player will receive the dungeon pass, enabling access to the dungeons, and the hasDungeonPass boolean in the Player class will be set to true. 

	Search

The search command can be used to display the items in a room with their descriptions.  It is a single word command.  There is a method searchRoom in the Game class that is called when the parser receives an input beginning with search.  There is a crystal item that is required in order to display the full search.  Certain items which are not basic as indicated by the isBasic boolean in the Items class will not be displayed unless the player has the crystal and can not be taken unless the player has searched in the room with the crystal.  There is a crystalUsed boolean in the Room class which is set to true when the player searches in a room with the crystal and the takeItem method in the Game class has a condition to check if this is true.

	Modify the help method

The help method now shows a description of how to use each command.  The validCommands array in the CommandWords class has been changed to a 2d array to store these descriptions and the listCommandWords method displays the description as well as the name of each command. 

Walkthrough

In order to complete the game the player must enter the treasury room with the ring. The player can use the search and ask commands to uncover helpful information. 
Quit can be used to suspend the game, help to bring up the list of commands and knapsack to display the items in the player’s knapsack. 

One possible way to win is as follows:

(the player’s current location after each command is written in brackets, the player starts in the entrance)
go west(library); take rose(library); go east(entrance); go up(bedroom); go north(tower); give orc rose(tower); go down(great hall); go east(music room); take flute(music room); go south(dining room); take sweets(dining room)
*note* at some point after this the player must encounter the goblin and type 
‘give goblin sweets’.  The goblin’s location is randomised every time the player moves room so encountering it is luck based. 
go down(kitchen); go north(storeroom); give troll flute(storeroom)
*note* if the goblin has been given the sweets the player will now receive the dungeon pass.  Otherwise the player must look for the goblin and will receive the pass when they give the goblin the sweets. 
go west(dungeons); take crystal(dungeons); go east(storeroom); go south(kitchen); search(kitchen); take key(kitchen); go up(dining room); go west(entrance); go up(bedroom); search(bedroom); take ring(bedroom); go down(entrance); go west(library); go north(treasury)
This path will achieve the standard end. 
In order to achieve the violent end the player must have the sword which can be taken from the great hall. 
In order to achieve the peaceful end the player must not have the sword and must have successfully tidied the castle. One way this can be achieved is as follows:
(starting in the entrance)
take cushion(entrance); take axe(entrance); go north(great hall); put cushion throne(great hall); take candlesticks(great hall); go up(tower); put axe armour(tower); go south(bedroom); take needle(bedroom); go down(entrance); go east(dining room); put candlesticks table(dining room); go down(kitchen); go north(storeroom); put needle haystack(storeroom); take chains(storeroom)

*note* the following can only be done after the dungeon pass is obtained.
go west(dungeons); put chains rack(dungeons)



