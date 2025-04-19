import java.util.*;
/**
 * The players who are playing the game UNO. This is a superclass with two subclasses:
 * HumanPlayer and Bot.
 * 
 * @author: Lorenzo Canali
 * version: 12/10/2024
 */
public abstract class Player {
    protected String name;
    protected List<UNOCard> cardsInHand;
    /**
     * Constructor for the Player class
     * 
     * @param name the name of the player
     */
    protected Player(String name) {
        this.name = name;
        cardsInHand = new ArrayList<>();
    }
    /**
     * An abstract method that allows the players to "take" a turn
     * 
     * @param game the game where they are attempting to make a turn
     */
    protected abstract void takeTurn(UNO game);
    /**
     * An abstract method that allows the players to change the wild card suit
     * 
     * @param game the game where they are changing the suit of the wild card
     * @return the new suit of the card
     */
    protected abstract String handleWild(UNO game);
    /**
     * Adds a card the cards in the players hand
     * 
     * @param card the card to add to the hand
     */
    protected void addCard(UNOCard card) {
        cardsInHand.add(card);
    }
    /**
     * Finds the card at the specified index
     * 
     * @param index the index value to find the card of
     * @return the card at the index
     */
    protected UNOCard getCardAt(int index) {
        return cardsInHand.get(index);
    }
    /**
     * Removes a card from the players hand
     * 
     * @param index the index to remove the card
     */
    protected void removeCard(int index) {
        cardsInHand.remove(index);
    }
    /**
     * The number of cards in the players hand
     * 
     * @return the number of cards
     */
    protected int getNumOfCards() {
        return cardsInHand.size();
    }
    /**
     * Returns the name of the player
     * 
     * @return the name of the player
     */
    protected String getName() {
        return name;
    }
}