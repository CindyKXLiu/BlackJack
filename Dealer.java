import java.util.*; 

/**
 * A subclass of the superclass Person. 
 * Dealer of the game. 
 * 
 * @author Cindy Liu
 * @version 29-10-18
 */
public class Dealer extends Person 
{
    private int standMarker = 0; 
    
    /**
     * Print hand with one card revealed and one card covered 
     */
    public void printHand ()
    { 
            System.out.println (hand.get(0).getCard() + " ?");
    }
    
    /**
     * Overwrites the printFinalHand method of its superclass Person
     * Print final hand - all cards
     */
    public void printFinalHand ()
    {
         super.printHand(); 
    }
    
    /**
     * Print the score of the dealer before game ends (i.e. when both dealer and player stand)
     * The value of the card shown
     */
    public void printScore ()
    {
            System.out.println ("Dealer's Score: " + hand.get(0).getValue());
    }
    
    /**
     * Overwrites the printFinalScore method of its superclass Person
     * Print the final score of the dealer after game ends 
     * Total sum of the cards on dealer's hand  
     */
    public void printFinalScore ()
    {
            System.out.println ("Dealer's Score: " + getHandSum()); 
    }
    
    /**
     * Decides wether dealer should stand or hit. 
     * Dealer hits if they do noty
     * when dealer does not have a sum smaller than 21 and when player does not have a sum greater than 21 and either Player has a higher sum or a percentage of busting is less than 50%, a sum that is less or equal 16
     * 
     * @return  wether dealer should stand
     * @param   p   the player/ opponent.  
     * @param   d   the current deck used. 
     */
    public boolean shouldStand (Person p, Deck d)
    {
        // occasions for hit 
        // when dealer does not have a sum smaller than 21 and when player does not have a sum greater than 21 and either Player has a higher sum or a percentage of busting is less than 50%, a sum that is less or equal 16
        if (p.getHandSum() <=21 && getHandSum() < 21 && (getHandSum() < p.getHandSum()||getChance (p,d) < 0.5||getHandSum() <= 16))
        {
            return false;
        }
        return true;
    }
    
    /** 
     * Get the chance of the dealer busting
     * 
     * @return  the chance of the dealer busting 
     * @param   p   the player/ opponent.  
     * @param   d   the current deck used. 
     */
    private double getChance (Person p, Deck d) 
    {
        // count my sum 
        // see what cards are left
        // find the number of the card that will give a value of 21 or less 
        
        int mySum = getHandSum(); 
        int myChance = 0;
        for (int i = 0; i < d.getSize(); i++)
        {
            if (d.getThisCard(i).getValue() == 11)
            {
                if (mySum + maxAceValue > 21 || mySum + minAceValue > 21)
                {
                    myChance ++;
                }
            }
            else
            {
                if (mySum + d.getThisCard(i).getValue() > 21)
                {
                    myChance ++; 
                }
            }
        }
     
        return (double) myChance/ (double) d.getSize();
    }
    
    /**
     * Overwrites the addCard method of its superclass Person
     * Add card to hand if decides to hit 
     * Once dealer decides to stand, they can not longer chose to hit 
     * 
     * @param   c   the top card of the deck.
     * @param   p   the Player. 
     * @param   d   the current deck.
     */
    public void addCard (Card c, Person p, Deck d)
    {
        if (standMarker == 0)
        {
            if (!shouldStand (p,d)) 
            {
                super.addCard (c); 
            }  
            else 
            {
                standMarker = 1;
            }
        }
    }
}
