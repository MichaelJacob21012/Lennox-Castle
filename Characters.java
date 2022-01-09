
/**
 * This class is part of the "Zuul Castle" application. 
 * "Zuul Castle" is a very simple, text based adventure game.  
 * 
 * Characters appear in the rooms. The player can give a particular item to a character
 * and receive a message from them. 
 * 
 * @author Michael Jacob
 * @version 04/12/17
 */
public class Characters
{
    private String name;
    private Room currentRoom;           // The current location of the character
    private Items acceptedItem;         // The item required by the character
    private boolean hasAcceptedItem;    // Whether the character has said item

    /**
     * Create a character with no initial location or requested item.
     * Initially, the player can not enter the dungeons and has not tidied any items.
     */
    public Characters(String name)
    {
        // initialise instance variables
        this.name = name;
        currentRoom = null;
        acceptedItem = null;
        hasAcceptedItem = true;     // As no item is required 
    }

    /**
     * Get the name of the character.
     * @return The name of the character.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the current location of the character.
     * @return The current room the character is in.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Set the room the character is in.
     * @param The new room for the character.
     */
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }

    /**
     * Get the item that is accepted by the character.
     * @return The item accepted by the character.
     */
    public Items getAcceptedItem()
    {
        return acceptedItem;
    }

    /**
     * Set the item accepted by the character.
     * @param The item to be set as accepted.
     */
    public void setAcceptedItem(Items item)
    {
        acceptedItem = item;
        if (item != null){
            setHasAcceptedItem(false); // The character now requires an item
        }
    }

    /**
     * Check whether the character has their accepted item.
     * @return True if the character has their accepted item.
     */
    public boolean getHasAcceptedItem()
    {
        return hasAcceptedItem;
    }

    /**
     * Set whether or not the character has their accepted item.
     * @param The new truth value of whether the character has their accepted item.
     */
    public void setHasAcceptedItem(boolean hasAcceptedItem)
    {
        this.hasAcceptedItem = hasAcceptedItem;
    }

    /**
     * Print what the character says.
     */
    public void printResponse()
    {
        System.out.print(getName() + " says : ");
        if(getName().equals("adventurer")){
            System.out.println("You have freed me, thank you.");
            System.out.println("I heard there is a ring that the owner fears,I have never seen it");
            return;
        }  

        if(getName().equals("skeleton")){
            System.out.println("Must tidy the castle");
            return;
        }

        if (acceptedItem == null){
            System.out.println("Enjoy your visit");
            return;
        }
        
        if (getHasAcceptedItem()){
            System.out.println("Thank you");
            return;
        }
        
        // If we get here the character needs their item
        System.out.println("Give me " + getAcceptedItem().getName());
    }

}
