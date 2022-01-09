/**
 * This class is part of the "Lennox Castle" application. 
 * "Lennox Castle" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Jacob
 * @version 04/12/2017
 */

public class CommandWords
{
    // A 2d array that holds all valid command words and their descriptions
    private static final String[][] validCommands = {
        {"help","displays help"}, 
        {"knapsack"," lists knapsack contents"},
        {"search","look around the room"}, 
        {"ask","ask <character>, see what a character has to say"},
        {"go", "go <direction>, travel in a particular direction"},
        {"back","return to previous room"},
        {"forward","go forward to previous room"},
        {"take","take <item>, used to pick up an item"},
        {"drop","drop <item>, used to release an item"},
        {"give", "give <character> <item>, used to hand an item to a character"},
        {"put","put <item1> <item2>, used to leave one item on another"},
        {"quit", "suspend game"},   
         
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i][0].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands with descriptions to System.out.
     */
    public void showAll() 
    {
        for(String[] commandArray: validCommands) {
            System.out.println(commandArray[0] + ":  " + commandArray[1]);
        }
    }
}
