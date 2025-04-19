import java.util.Random;
/**
 * Is a subclass of the parent class Player. This class will create objects intended 
 * for bots. It will automatically play cards for the bot by iterating through their
 * hand until they can play a valid card or draw a card if they have no valid cards to
 * play
 * 
 * @author: Lorenzo Canali
 * version: 12/15/2024
 */
public class Bot extends Player {
    /**
     * Constructor for the subclass Bot for the superclass Player
     * 
     * @param name the name of the human player
     */
    protected Bot(String name) {
        super(name);
    }
    /**
     * Overrides the abstract method takeTurn and allows the bot to play a card. It 
     * iterates through the whole deck and if one of the cards is allowed to be played
     * it will play it and exit the method; if not, it will draw a card
     */
    @Override
    protected void takeTurn(UNO game) {
        try {
            Thread.sleep(3000);
        } 
        catch(InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        System.out.println(name + "'s turn.");
        System.out.println(name + " has " + getNumOfCards() + " cards left");
        for(int i = 0; i < cardsInHand.size(); i++) {
            UNOCard playedCard = cardsInHand.get(i);
            if(game.playCard(this, i)) {
                System.out.println(name + " played " + playedCard.getName());
                return;
            }
        }
        game.drawCard(this);
        System.out.println(name + " drew a card.");
    }
    /**
     * Overrides the abstract method handleWild and randomly selectes the wild card's
     * new suit
     */
    @Override 
    protected String handleWild(UNO game) {
        String[] suits = {"Red", "Yellow", "Green", "Blue"};
        return suits[new Random().nextInt(4)];
    }
}