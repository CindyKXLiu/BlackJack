
import java.util.*; 

/**
 * A standard deck of card composed of 52 cards 
 * No joker, face cards and number cards only 
 * Four suits included 
 *
 * @author Cindy Liu
 * @version 10-29-18
 */

public class Deck 
{
    //The array for cards in the deck, where the top card is in the first index. 
    private ArrayList <Card> myCards = new ArrayList <Card>();
    //The number of cards in a standard deck. 
    private int numCards = 52; 
    
    /**
     * Constructor for a standard deck of 52 cards 
     */
    public Deck()
    {
        for (int i = 0; i <= this.numCards - 1; i++)
        {
            this.myCards.add(new Card (i)); 
        }
    }
    
    /**
     * Return deck size 
     * 
     * @return the deck size (number of cards in the deck).
     */
    public int getSize ()
    {
        return myCards.size();
    }
    
    /**
     * Return the card of the given index 
     * 
     * @return   the card of the given index
     * @param   the index of the card seeked.
     */
    public Card getThisCard (int i)
    {
        return myCards.get (i);
    }
    
    /**
     * Shuffle deck by randomly swapping pairs of cards 
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void shuffle ()
    {
        Random rand = new Random (); 
        
        // temp card 
        Card temp; 
        
        int j;
        
        for (int i = 0; i < this.numCards ; i++)
        {
            // get a random card j to swap i's value with
            j = rand.nextInt (this.numCards);  
            
            //do swap 
            temp = this.myCards.get(i); 
            this.myCards.set(i,myCards.get(j));
            this.myCards.set(j,temp); 
        }
    }
    
    /** 
     * Deal the next card from the top of the deck.
     * If the deck of card has less or equal to 5 cards, new deck is created and used. 
     * 
     * @return  the next card from the top of the deck.
     */
    public Card dealNextCard ()
    {
        if (this.numCards <= 5) 
        {
            System.out.println ("Cards are running out! New deck used.");
            resetDeck(); 
            printDeck(); 
            System.out.println ("");
            shuffle (); 
            printDeck();
        }
        
        // get the top card 
        Card top = this.myCards.get(0);
        this.myCards.remove(0); 
        this.numCards--; 
        return top; 
    }
    
    /** 
     * Print deck
     */
    public void printDeck ()
    {
        for (int i = 0; i < this.numCards; i++) 
        {
            System.out.print (this.myCards.get(i).getCard() + " "); 
        }
        System.out.println ("");
    }
    
    /**
     * Returns the number of cards in the deck 
     * 
     * @return   the number of cards in the deck.
     */
    public int getNumCards ()
    {
        return this.numCards;
    }
    
    /**
     * Resets the deck to a standard 52 cards deck.
     */
    public void resetDeck ()
    {
        myCards.clear(); 
        this.numCards = 52; 
        
        for (int i = 0; i <= this.numCards - 1; i++)
        {
            this.myCards.add(new Card (i)); 
        }
    }
}
