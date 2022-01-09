import java.util.HashSet;

/**
 * This class is part of the "Lennox Castle" application. 
 * "Lennox Castle" is a very simple, text based adventure game.  
 * 
 * The knapsack is used by the playerfor carrying items up to a maximum weight.
 * 
 * @author Michael Jacob
 * @version 04/12/17
 */
public class Knapsack
{
    private final int maxWeight;        // The total maximum weight of items that be carried in the knapsack.
    private HashSet < Items > knapsack; // Set of items in the knapsack

    /**
     * Create an empty knapsack of maximum weight 15.
     */
    public Knapsack()
    {
        // initialise instance variables
        knapsack = new HashSet <>();
        maxWeight = 15;
    }

    /**
     * Calculate the weight of all items currently in the knapsack.
     * @return The weight of all items currently in the knapsack.
     */
    public int getKnapsackWeight()
    {
        int knapsackWeight = 0;
        for (Items item : knapsack){
            knapsackWeight += item.getWeight();
        }
        return knapsackWeight;
    }

    /**
     * Take an item, adding it to the knapsack.
     * @param The item to be picked up.
     */
    public void addItem(Items item)
    {
        knapsack.add(item);
    }

    /**
     * Drop an item, removing it from the knapsack.
     * @param The item to be dropped.
     */
    public void removeItem(Items item)
    {
        knapsack.remove(item);
    }

    /**
     * Print all the items currently in the knapsack with their weight and description.
     */
    public void listKnapsackContents()
    {
        if (knapsack.isEmpty()){
            System.out.println("It's empty");
            return;
        }

        for(Items item : knapsack){
            System.out.println("" + item.getName() + "  " + item.getDescription()
                + "  Weight: " + item.getWeight());
        }
    }

    /**
     * Check whether an item is in the knapsack.
     * @return If found, the item, otherwise null.
     */
    public Items findInKnapsack(String itemName)
    {
        for(Items item : knapsack) {
            if(item.getName().equals(itemName))
                return item;
        }
        // If we get here, the item was not found in the knapsack
        return null;
    }

    /**
     * Get the maximum weight that can be stored in the knapsack.
     * @return The maximum weight that can be stored in the knapsack.
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }

    /**
     * Check if picking up an item would cause the player to exceed the maximum weight.
     * @param The item to be checked.
     * @return True if picking up an item would cause the player to exceed the maximum weight.
     */
    public boolean checkWeight(Items item)
    {
        if((getKnapsackWeight() + item.getWeight()) > maxWeight){
            return true;
        }
        return false;
    }   
}
