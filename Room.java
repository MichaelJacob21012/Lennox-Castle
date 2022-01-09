import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Lennox Castle" application. 
 * "Lennox Castle" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighbouring room.
 * 
 * @author  Michael Jacob
 * @version 04/12/2017
 */

public class Room 
{
    private String name;
    private String description;
    private HashMap<String, Room> exits;  // Stores exits of this room
    private HashSet<Items> items;         // All the items in the room
    private boolean crystalUsed;          // True if the player has searched in the room with the crystal

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "in a kitchen" or
     * "in an open court yard".
     * @param name The room's name.
     * @param description The room's description.
     */
    public Room(String name, String description) 
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet<>();
        crystalUsed = false;    // Player has not used the crystal yet
    }

    /**
     * Get the name of the room
     * @return The name of the room.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbour  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit + ",";
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Add an item to the room.
     * @param The item to be added.
     */
    public void addItem(Items item)
    {
        items.add(item);
    }

    /**
     * Find out how many items are in the room.
     * @return The number of items in the room.
     */
    public int getNumberOfItems()
    {
        return items.size();
    }

    /**
     * Remove an item from the room.
     * @param The item to be removed.
     */
    public void removeItem(Items item)
    {
        items.remove(item);
    }

    /**
     * Check if an item is in the room.
     * @param The name of the item to be checked.
     * @return The item if found, if not in the room return null.
     */
    public Items findInRoom(String itemName)
    {
        for(Items item : items) {
            if(item.getName().equals(itemName))
                return item;
        }
        // If we get here, the item was not found in the room
        return null;
    }

    /**
     * Print all the items in the room; name and description.
     */
    public void listAllItems()
    {
        if (items.isEmpty()){
            System.out.println("The room contains nothing of note");
        } else {
            for(Items item : items){
                System.out.println("" + item.getName() + ",  " + item.getDescription());
            }
        }
    }

    /**
     * Print all the basic items(items that can be found without the crystal)
     * in the room; name and description.
     */
    public void listBasicItems()
    {
        boolean showsEmpty = true;
        for(Items item : items){
            if (item.getIsBasic() == true){
                System.out.println("" + item.getName() + ",  " + item.getDescription());
                showsEmpty = false;
            }
        }

        if (showsEmpty == true ){
            System.out.println("The room contains nothing of note");
        }
    }

    /**
     * Check whether the player has searched in this room with the crystal.
     * @return True if the player has searched in this room with the crystal.
     */
    public boolean getCrystalUsed()
    {
        return crystalUsed;
    }

    /**
     * Set whether the player has searched in this room with the crystal.
     * @param The new value for the crystalUsed boolean.
     */
    public void setCrystalUsed(boolean beenUsed)
    {
        crystalUsed = beenUsed;
    }
}

