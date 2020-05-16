
import java.util.*; 
/**
 * Subclass of Person
 * Player/user of the game.
 *
 * @author Cindy Liu
 * @version 29-10-18
 */
public class Player extends Person 
{
    private String name;
    
    public Player (String n)
    {
        name = n; 
    }
    
    /**
     * Returns the name of the Player
     * Overwrites the getName method of its superclass Person
     * 
     * @return  the name of the Player.
     */
    public String getName ()
    {
        return name; 
    }
}
