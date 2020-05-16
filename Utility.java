
/**
 * Given methods 
 *
 * @author Ms. Strelkovska
 */
public class Utility
{
    private static char heart = '\u2665'; 
    private static char diamond = '\u2666';
    private static char spade = '\u2660';
    private static char club = '\u2663';
    private static char [] suit = {heart, diamond, spade, club};
    
    /**
     * Return the card's suit and number 
     * 
     * @param numInList the assigned number of the card 
     * @return  the card's suit and number 
     */
    public static String cardToString(int numInList)
    {
        int cardType = numInList%13;
        char cardNum;
        String cardIs = "";
        //If card is A, K, J, or Q, sets it as such, otherwise card is equal to number%13
        if (cardType == 0)
        {
            cardNum = 'K';
        }
        else if (cardType==1)
        {
            cardNum='A';
        }
        else if (cardType==11)
        {
            cardNum = 'J';
        }
        else if (cardType==12)
        {
            cardNum='Q';
        }
        else 
        {
            cardNum = (char)(cardType+'0');
        }
        //ASCII value of suit is equal to concatenated value of number/13+3 becuase (char)3 is heart icon, (char)4 is dimond //icon and so on
        char cardSuit = suit[numInList/13];
        if (cardType!=10)
        {
            cardIs=""+cardNum+cardSuit;
        }
        else 
        {
            cardIs="10"+cardSuit;
        }
        return cardIs;
    }
        
    /**
     * Return the value of the card 
     * 
     * @param numOfList the assigned value of the card 
     * @return the actual card value 
     */
    public static int cardToValue(int numOfList)
    {
        int cardValue;
        //If card is K,J,Q, returns value of 10
        if (numOfList%13==0||(numOfList%13>=10 && numOfList%13<=12))
        {
            cardValue=10;
        }
        //If ace, return value of 11
        else if (numOfList%13==1)
        {
            cardValue=11;
        }
        //All other cards values are their number%13
        else
        {
            cardValue=numOfList%13;
        }
        return cardValue;
    }
}
