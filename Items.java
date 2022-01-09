
/**
 * This class is part of the "Lennox Castle" application. 
 * "Lennox Castle" is a very simple, text based adventure game.  
 * 
 * Items represent objects or furniture in a room or in the player's knapsack.
 * They can be used for various tasks and can be picked up, dropped or given away 
 * by the player.
 * 
 * @author Michael Jacob
 * @version 04/12/17
 */
public class Items
{
    private String name;
    private String description;
    private int weight;             // The weight of the item
    private boolean canBePickedUp;  // Whether or not the item can be picked up
    private boolean isBasic;        // Whether or not the item can be found without the crystal

    /**
     * Create an item described "description". Initially, it has
     * no weight, can not be picked up and can be found without the crystal.
     * "description" is something like "it's a spoon" or
     * "this tablecloth has a pattern".
     * @param name The item's name.
     * @param description The item's description.
     */
    public Items( String name, String description)
    {
        // Initialise instance variables
        this.name = name;
        this.description = description;
        weight = 0;
        canBePickedUp = false;
        isBasic = true;
    }

    /**
     * Get the name of the item.
     * @return  The item's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the description of the item.
     * @return  The item description.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Set the weight of the item.
     * @param  The new weight of the item.
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    /**
     * Get the weight of the item.
     * @return  The weight of the item.
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Set whether or not the 'take' command can be used to pick up the item
     * @param  The new truth value of whether or not it can be picked up.
     */
    public void setPickUp(boolean canBePickedUp)
    {
        this.canBePickedUp = canBePickedUp;
    }

    /**
     * Check whether the item can be picked up.
     * @return True if the item can be picked up.
     */
    public boolean getPickUp()
    {
        return canBePickedUp;
    }

    /**
     * Set whether or not the item is basic, i.e. if it can be found without the crystal.
     * @param  The new truth value of whether or not the item is basic.
     */
    public void setIsBasic(boolean isBasic)
    {
        this.isBasic = isBasic;
    }

    /**
     * Check whether or not the item is basic.
     * @return  True if the item is basic.
     */
    public boolean getIsBasic()
    {
        return isBasic;
    }

}
