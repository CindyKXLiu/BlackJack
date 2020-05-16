    import java.util.*;
    /**
     * Game of BalckJack - simplified version of the game of “21”
     * Game ends when both Dealer and Player stands
     * Game is palyed with a standard 52 deck of cards
     * No money is involved
     *
     * @author Cindy Liu
     * @version 29/10/18
     */
    public class BlackJack
    {
        public static void main (String arg [])
        {
            BlackJack b = new BlackJack(); 
        }
        
        private ArrayList <Person> person = new ArrayList <Person>(); 
        private Deck d;
        private int numRoundWon = 0;
        private int numRoundPlayed = 0;
        /**
         * Constructor for BlackJack
         */
        public BlackJack() 
        {
            Scanner s = new Scanner(System.in); 
            printWelcomeMsg(); 
            
            //creating player and dealer
            person.add (new Dealer ()); 
            person.add (new Player (s.nextLine())); 
            
            //creating a standard deck 
            d = new Deck (); 
            d.printDeck ();
            System.out.println ("");
            do
            {
                // shuffle deck and reprint
                d.shuffle (); 
                d.printDeck ();
                    
                firstDeal (); 
                
                if (noBlackJack(person))
                {
                    // subsequent deals
                    do
                    {
                        System.out.println (person.get(1).getName() + ", hit or stand?[H/S]");
                        String answer = s.nextLine();
                        System.out.println ("");
                        
                        if (answer.charAt (0) == 'H' || answer.charAt (0) == 'h')
                        {
                            // deal out card 
                            // first to player, then dealer 
                            // dealer need to see player card before deciding to stand or hit 
                            for (int i = 1; i >= 0; i--)
                            {
                                person.get(i).addCard (d.dealNextCard(), person.get(1), d); 
                                
                                if (person.get(i).getHandSum() >=21)
                                {
                                    break;
                                }
                            }  
                            
                            // print hand and score 
                            // Dealer hand and score printed first then Player
                            for (int i = 0; i < person.size(); i++)
                            {
                                person.get(i).printScore(); 
                                person.get(i).printHand ();
                            } 
                        }
                        else
                        {
                            break;
                        }
                    }while (person.get(0).getHandSum() < 21 && person.get(1).getHandSum() < 21 && d.getNumCards() >= 2);
                    
                    // does dealer still want to play?
                    while (!person.get(0).shouldStand(person.get(1), d) && d.getNumCards() != 0)
                    {
                        person.get(0).addCard (d.dealNextCard(), person.get(1), d); 
                    }
                }
                        
                getResult();
                reinitailzation();
            } while(shouldReplay());
            
            // print number of rounds Player won
            System.out.println ("You have won " + numRoundWon + " rounds out of " + numRoundPlayed + " round(s)"); 
            
            // scanner closed
            s.close();
        }
        
        /**
         * Determines if the dealer or the player hits black jack.
         * 
         * @return  wether there is a black jack or not.
         * @param   p   arraylist of person. 
         */
        private static boolean noBlackJack (ArrayList <Person> p)
        {
            for (int i = 0; i < p.size(); i++)
            {
                if (p.get(i).getHandSum() == 21)
                {
                    return false;
                }
            }
            
            return true;
        }
        
        /**
         * Print welcome message 
         */
        private static void printWelcomeMsg () 
        {
            System.out.println ("Welcome Player, please enter your name: ");
        }
        
        /**
         * Determine wether the Player wants to play another round.
         * 
         * @return  does the Player want another round.
         */
        private static boolean shouldReplay ()
        {
            Scanner s = new Scanner(System.in); 
            
            System.out.println ("Would you like to play again?[Y/N]"); 
            String answer = s.nextLine (); 
            s.close(); 
            System.out.println ("");
            
            if (answer.charAt (0) == 'Y' || answer.charAt (0) == 'y')
                return true;
                
            return false; 
        }
        
        /**
         * Re-initalizes objects for new game.
         */
        private void reinitailzation ()
        {
            // clear player and dealer hand 
            for (int i = 0; i < person.size(); i++)
            {
                person.get(i).emptyHand(); 
            }
        }
        
    /**
     * First deal of a game 
     * Two cards are given both the Player and Dealer
     */
    private void firstDeal ()
    {
        for (int c = 0; c<2; c++)
        {
            for (int i = 0; i < person.size(); i++)
            {
                person.get(i).addCard (d.dealNextCard()); // recheck for dealer (once stand cannot hit)
            }
        }
        for (int i = 0; i < person.size(); i++)
            {
                person.get(i).printScore(); 
                person.get(i).printHand ();
            }
    }
    
    /**
     * Prints the result of the game 
     * Add one to rounds played counter
     * Add one to rounds won counter if Player wins
     */
    private void getResult ()
    {
        numRoundPlayed ++; 
        Person d = person.get(0);
        Person p = person.get(1); 
        
        System.out.println ("");
        d.printFinalScore(); 
        d.printFinalHand ();
        p.printScore(); 
        p.printHand ();
        
        if (p.getHandSum () > 21)
        {
            System.out.println ("You busted, dealer won the bet."); 
        }
        else if (p.getHandSum () == 21)
        {
            System.out.println ("Black Jack! You won the bet."); 
            numRoundWon ++;
        }
        else if (p.getHandSum () == d.getHandSum())
        {
            System.out.println ("It's a tie");
        }
        else if (p.getHandSum () < d.getHandSum())
        {
            if (d.getHandSum ()== 21)
            {
                System.out.println ("Dealer won the bet.");
            }
            else if (d.getHandSum () < 21)
            {
                System.out.println ("Dealer won the bet.");
            }
            else if (d.getHandSum () > 21)
            {
                System.out.println ("Dealer busted, you won the bet."); 
                numRoundWon ++;
            }
        }
        else if (p.getHandSum () > d.getHandSum())
        {
            System.out.println ("You won the bet.");
            numRoundWon ++;
        }
        else 
        {
            System.out.println ("Error"); 
            System.exit (0);
        }
    }
}
