import java.util.*;
/**
 * This creates a deck of UNO cards. It can also manipulate cards in certain ways. I 
 * based this off the in-class assignment were we made a deck of cards 
 *
 * @author Lorenzo Canali
 * @version 10/26/24
 */
public class UNODeck
{
    private List<UNOCard> cards;
    /**
     * Creates a deck of cards / makes 108 unique card objects and stores them them into the
     * cards field
     */
    protected UNODeck() {
        cards = new ArrayList<>();
        String[] suit = {"Red", "Yellow", "Green", "Blue"};
        String[] royals = {"Skip","Draw Two", "Reverse"};
        for(int i = 0; i < 4; i++) {
            cards.add(new UNOCard(suit[i], "0"));
            for(int p = 0; p < 12; p++ ) {
                for(int k = 0; k < 2; k++) {
                    String cardRank;
                    if(p <= 8) {
                        cardRank = "" + (p + 1);
                    }
                    else {
                        cardRank = royals[p - 9];
                    }
                    cards.add(new UNOCard(suit[i], cardRank)); 
                }
            }
            cards.add(new UNOCard("Global", "Wild"));
            cards.add(new UNOCard("Global", "Wild Draw Four"));
        }
    }
    /**
     * Draws the card at index 0
     */
    protected UNOCard drawCard() {
        UNOCard saveCard = cards.get(0);
        cards.remove(0);
        return saveCard;
    }
    /**
     * Returns the card at a certain array
     * 
     * @param index the index of the card being returned
     * 
     * @return the card at the index
     */
    protected UNOCard getCardAt(int index) {
        return cards.get(index);
    }
    /**
     * Shuffles the cards in the deck.
     * 
     * @info Uses a list for the copyCards because we are need it to be dynamic to 
     * remove cards and arrays are static
     */
    protected void shuffle() {
        Random random = new Random();
        List<UNOCard> copyCards = new ArrayList<>();
        for(UNOCard card : cards) {
            copyCards.add(card);
        }
        for(int i = 0; i < cards.size(); i++) {
            int randomCard = random.nextInt(copyCards.size()); 
            cards.set(i,copyCards.get(randomCard)); 
            copyCards.remove(randomCard);
        }   
    }
    /**
     * Draws the amount of cards the user wants to draw
     * 
     * @param numOfCards The number of cards the user wants to draw
     */
    protected void draw(int numOfCards) {
        Random random = new Random();
        if(cards.size() - numOfCards >= 0) {
            for(int i = 0; i < numOfCards; i++) { 
                int randomCard = random.nextInt(cards.size()); 
                System.out.println("The card is " + cards.get(randomCard).getName());
                cards.remove(randomCard); 
            }
        }
        else {
            System.out.println("You will not have enough cards in the deck to draw that many cards! You have " + cards.size() + " cards left.");
        }
        if(cards.size() == 0) {
            System.out.println("You are out of cards to draw!");
        }
    }
    /**
     * Add cards back to the deck
     * 
     * @param Card the card being added back in
     */
    protected void addCard(UNOCard card) {
        cards.add(card);
    }
    /**
     * Prints all the cards in a nice organized manner
     */
    protected void printDeck() {
        for(UNOCard card : cards) {
            System.out.println(card.getName());
        }
    }
    /**
     * This sorts the decks into numerical and suit order
     */
    protected void sort() {
        for (int i = 0; i < cards.size(); i++) {
            int greatestIndex = i;
            for (int p = i + 1; p < cards.size(); p++) {
                if(cards.get(p).getSuitValue() < cards.get(greatestIndex).getSuitValue() || 
                (cards.get(p).getSuitValue() == cards.get(greatestIndex).getSuitValue() && 
                cards.get(p).getRankValue() < cards.get(greatestIndex).getRankValue())) {
                    greatestIndex = p;
                }
            }
            UNOCard change = cards.get(i); 
            cards.set(i, cards.get(greatestIndex)); 
            cards.set(greatestIndex, change);
        }
    }
    /**
     * The number of elements in the deck
     * 
     * @return The number of elements
     */
    protected int sizeOfDeck() {
        return cards.size();
    }
}