import java.util.*;
/**
 * Abstract class Person - Person in a game of BlackJack is either a dealer or a player
 *Superclass of Dealer and Player
 *
 * @author Cindy Liu
 * @version 29-10-18
 */
public abstract class Person
{
    protected static final int maxAceValue = 11; 
    protected static final int minAceValue = 1; 
    
    //The cards in current hand.
    protected ArrayList <Card> hand = new ArrayList <Card>();
    //The number of cards in current hand. 
    private int numCards;
    
    /**
     * Constructor for objects of class Player or class Dealer
     */
    public Person()
    {
        // set a player's hand to empty 
        this.emptyHand(); 
    }

    /**
     * Reset the player's hand to have no cards  
     */
    public void emptyHand()
    {
        this.hand.clear(); 
    }
 
    /**
     * Add a card to the player's hand. 
     * 
     * @param aCard the card to add 
     * @param   p   the Player
     * @param   d   the deck
     * @return  whether the sum of the new hand is below or equal to 21
     */
    public void addCard (Card aCard, Person p, Deck d)
    {
        // add new card in next slot and increment number cards counter 
        this.hand.add (aCard); 
        this.numCards ++; 
    }
    
    /**
     * Add a card to the player's hand. 
     * 
     * @param aCard the card to add 
     * @return  whether the sum of the new hand is below or equal to 21
     */
    public void addCard (Card aCard)
    {
        // add new card in next slot and increment number cards counter 
        this.hand.add (aCard); 
        this.numCards ++; 
    }
    
    /** 
     * Get the sum of the cards in the player's hand. 
     * 
     * @return  the sum
     */
    public int getHandSum ()
    {
        int sum = 0; 
        int numAce = 0;
        
        // adding up all the cards that are not aces 
        for (int i = 0; i < hand.size(); i++) 
        {
            if (hand.get(i).getValue() == 11)
            {
                numAce++;  
            }
            else 
            {
                sum = sum + hand.get(i).getValue(); 
            }
        }
        
        if (numAce == 0)
            return sum;
            
        // adding the aces 
        if (sum + numAce + 10 > 21)
        {
            sum = sum + numAce;
        }
        else 
        {
            sum = sum + numAce + 10; 
        }
        
        return sum; 
    }
    
    /**
     * Print the cards a person's hand 
     */
    public void printHand ()
    {
        for (int i = 0; i < hand.size(); i ++)
        {
            System.out.print (hand.get(i).getCard() + " ");
        }
        System.out.println (""); 
    }
    
    /**
     * Print the score 
     */
    public void printScore ()
    {
        System.out.println ("Player's Score: " + getHandSum()); 
    }
    
    /**
     * Returns the number of cards in a person's hand 
     * 
     * @return  the number of cards in the hand
     */
    public int getNumCards ()
    {
        return numCards; 
    }
    
    /**
     * Returns the name of the person
     * Used to be overwritten by Player class
     * 
     * @return  name of the person 
     */
    public String getName ()
    {
        return ""; 
    }
    
    /**
     * Used to be overwritten by Dealer class
     */
    public void printFinalHand ()
    {
    }
    
    /**
     * Used to be overwritten by Dealer class
     */
    public void printFinalScore ()
    {
    }
    
    /**
     * Used to be overwritten by Dealer class
     */
    public boolean shouldStand (Person p, Deck d)
    {
        return false; 
    }
}
