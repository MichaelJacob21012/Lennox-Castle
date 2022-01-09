import java.util.HashSet;
import java.util.ArrayList;
/**
 * This class is part of the "Lennox Castle" application. 
 * "Lennox Castle" is a very simple, text based adventure game.  
 * 
 * The player is a user running the game. The player has a knapsack, 
 * for carrying items up to a maximum weight. The player is in a room and may
 * have completed certain tasks.
 * The dungeon pass can be obtained by providing all characters with their desired items.
 * 
 * @author Michael Jacob
 * @version 04/12/17
 */
public class Player
{
    private Knapsack knapsack;              // Set of items in the knapsack
    private Room currentRoom;               // The player's current room
    private ArrayList<Room> roomStack;      // A trail of rooms for 'back' and 'forward'
    private int roomStackPosition;          // The position in the previous rooms list
    private boolean hasDungeonPass;         // Whether the player has permission to enter the dungeons
    private int numberOfObjectsTidied;      // The number of items placed correctly by the player
    private boolean tidiedCastle;           // Whether the player has placed all items

    /**
     * Create a player with a new knapsack.
     * Initially, the player can not enter the dungeons and has not tidied any items.
     * The room stack position is -1 as the the list of rooms is initially empty.
     */
    public Player()
    {
        // initialise instance variables
        knapsack = new Knapsack();
        currentRoom = null;
        roomStack = new ArrayList<>();
        roomStackPosition = -1;
        hasDungeonPass = false;
        numberOfObjectsTidied = 0;
        tidiedCastle = false;
    }

    /**
     * Take an item, adding it to the knapsack and removing it from the room.
     * @param The item to be picked up.
     */
    public void takeItem(Items item)
    {
        knapsack.addItem(item);
        currentRoom.removeItem(item);
    }

    /**
     * Drop an item, removing it from the knapsack and adding it to the room.
     * @param The item to be dropped.
     */
    public void dropItem(Items item)
    {
        knapsack.removeItem(item);
        currentRoom.addItem(item);
    }

    /**
     * Set the current location of the player.
     * @param The room to be set as the location.
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * Add a new location of the player and increment the stack position.
     * @param The room to be added.
     */
    public void addRoom(Room room)
    {
        roomStack.add(room);
        incrementStackPosition();
    }

    /**
     * Get the current location of the player.
     * @return The room the player is in.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Remove a room the player was in from the list.
     * @param the room to be removed.
     */
    public void removeRoom(Room room)
    {
        roomStack.remove(room);
    }

    /**
     * Remove all rooms greater with an
     * index greater than the stack position from the list.
     */
    public void clearNeedlessRooms()
    {
        roomStack.subList((roomStackPosition + 1), roomStack.size()).clear();
    }

    /**
     * Get the current room stack position.
     * @return The current room stack position.
     */
    public int getStackPosition()
    {
        return roomStackPosition;
    }

    /**
     * Check if the stack position is at the last element.
     * @return True if the stack position is at the last element.
     */
    public boolean checkAtEnd()
    {
        if (roomStackPosition >= (roomStack.size() - 1)){
            return true;
        }
        return false;
    }

    /**
     * Get the room in the list indicated by the stack position.
     * @return The room in the list indicated by the stack position.
     */
    public Room getStackPositionRoom()
    {
        return roomStack.get(roomStackPosition);
    }

    /**
     * Check if there are no  rooms visited.
     * @return True if there are no rooms.
     */
    public boolean roomStackEmpty()
    {
        if (roomStack.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Lower the room stack position by one.
     */
    private void decrementStackPosition()
    {
        roomStackPosition--;
    }

    /**
     * Raise the room stack position by one.
     */
    private void incrementStackPosition()
    {
        roomStackPosition++;
    }
    
    /**
     * Go to the previous room in the stack.
     */
    public void back()
    {
        decrementStackPosition();
        setCurrentRoom(getStackPositionRoom());
    }
    
     /**
     * Go to the next room in the stack.
     */
    public void forward()
    {
        incrementStackPosition();
        setCurrentRoom(getStackPositionRoom());
    }

    /**
     * Check if the player has the dungeon pass.
     * @return True if the player has the dungeon pass.
     */
    public boolean getHasDungeonPass()
    {
        return hasDungeonPass;
    }

    /**
     * Set whether the player has the dungeon pass.
     * @param The new truth value of whether the player has the dungeon pass.
     */
    public void setHasDungeonPass(boolean hasDungeonPass)
    {
        this.hasDungeonPass = hasDungeonPass;
    }

    /**
     * Check if the player has put all items in their appropriate places.
     * @return True if the player has completed this.
     */
    public boolean getTidiedCastle()
    {
        return tidiedCastle;
    }

    /**
     * Set whether the player has tidied the Castle.
     * @param The new truth value of whether the player has tidied the Castle.
     */
    public void setTidiedCastle(boolean tidiedCastle)
    {
        this.tidiedCastle = tidiedCastle;
    }

    /**
     * Get the number of items placed correctly by the player.
     * @return The number of items placed correctly by the player.
     */
    public int getNumberOfObjectsTidied()
    {
        return numberOfObjectsTidied;
    }

    /**
     * Increase the number of items placed correctly by the player by one.
     */
    public void incrementNumberOfObjectsTidied()
    {
        numberOfObjectsTidied++;
    }

    /**
     * Get the player's knapsack.
     * @return The player's knapsack.
     */
    public Knapsack getKnapsack()
    {
        return knapsack;
    }
}
