
/**
 * Individual poker card
 * Either face or numerical card 
 * Identified by an unique number assigned 
 *
 * @author Cindy Liu
 * @version 28-10-18
 */
public class Card
{
    private int myNumber; 
    private static Utility u = new Utility (); 
        
    /**
     * Card constructor 
     * 
     * @param aNumber   the number of the card
     */
    public Card (int aNumber) 
    {
        if (aNumber >= 0 && aNumber < 52)
        {
            this.myNumber = aNumber;
        }
        else
        {
            System.err.println (aNumber + " is not a valid card number");
            System.exit (1); 
        }
    }
    
    /**
     * Return the number of the card and its suit. 
     * 
     * @return  the number and suit of the card
     */
    public String getCard()
    {
        return u.cardToString(this.myNumber); 
    }
    
    /** 
     * Returns the value of the card 
     * Ace = 11, face cards = 10, number cards = their number
     * 
     * @return the value of the card
     */
    public int getValue ()
    {
        return u.cardToValue (this.myNumber); 
    }
}
