import java.util.*;
/**
 * Play the game UNO manually or automatically by adding bots and players.
 * 
 * @author Lorenzo Canali
 * @version 12/7/2024
 */
public class UNO {
    private String winner;
    private Player[] players;
    private UNODeck deck;
    private UNOCard topCard;
    private boolean isNotReverse = true;
    private boolean skip = false;
    /**
     * Constructor for UNO with a set number of players
     * 
     * @param numOfPlayers Total players in the game
     */
    public UNO(int numOfPlayers) {
        players = new Player[numOfPlayers];
        deck = new UNODeck();
        deck.shuffle();
        winner = "";
        initializePlayers();
        initializeTopCard();
        playGame();
    }
    /**
     * Initialize the players by adding bots and human players. Human players will
     * be chosen then bots will fill all spaces human players don't take. Has exception handling for invalid inputs
     */
    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many human players? (0-" + players.length + ")");
        int humanCount = -1;
        boolean validInput = false;
        while(!(humanCount >= 0 && humanCount <= players.length) || !validInput) {
            validInput = false;
            try {
                humanCount = scanner.nextInt();
                if(humanCount >= 0 && humanCount <= players.length) {
                    validInput = true;
                }
                else {
                    System.out.println("ENTER A VALID NUMBER IN THE RANGE");
                }
            }
            catch(Exception e) {
                System.out.println("ENTER AN INTEGER");
                scanner.nextLine();
            }
        }
        for(int i = 0; i < humanCount; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            players[i] = new HumanPlayer(scanner.next());
            dealCards(players[i]);
        }
        scanner.close();
        for(int i = humanCount; i < players.length; i++) {
            players[i] = new Bot("Bot " + (i - humanCount + 1));
            dealCards(players[i]);
        }
    }
    /**
     * Starts the game and runs turns until a winner is determined
     */
    protected void playGame() {
        while(winner.isEmpty()) {
            for(int i = isNotReverse ? 0 : players.length - 1;
                 isNotReverse ? i < players.length : i >= 0;
                 i += isNotReverse ? 1 : -1) {
                if(skip) {
                    System.out.println("\n" + players[i].getName() + " got skipped");
                    skip = false;
                    continue;
                }
                System.out.println("\nTop card: " + topCard.getName());
                players[i].takeTurn(this);
                if(players[i].getNumOfCards() == 0) {
                    winner = players[i].getName();
                    System.out.println(winner + " has won the game!");
                    return;
                }
            }
        }
    }
    /**
     * Deals 7 cards to a player
     * 
     * @param the player to be dealt 7 cards
     */
    private void dealCards(Player player) {
        for(int i = 0; i < 7; i++) {
            player.addCard(deck.drawCard());
        }
    }
    /**
     * Validates and plays a card
     * 
     * @param player the player who is playing the card who needs to consume the card
     * @param cardIndex The card they chose to play index value
     * @return returns true if is a valid card, false if not
     */
    public boolean playCard(Player player, int cardIndex) {
        UNOCard chosenCard = player.getCardAt(cardIndex);
        if(topCard.matches(chosenCard)) {
            topCard = chosenCard;
            player.removeCard(cardIndex);
            int currentIndex = findIndexOfPlayer(player);
            applyCardEffect(chosenCard, currentIndex);
            return true;
        }
        return false;
    }
    /**
     * Finds the index of the player
     * 
     * @param player the player to find the index of
     * @return the index of the player
     */
    private int findIndexOfPlayer(Player player) {
        for(int i = 0; i < players.length; i++) {
            if(players[i] == player) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Forces the next player to draw a specified number of cards.
     *
     * @param currentPlayerIndex The index of the current player.
     * @param numCards The number of cards the next player should draw.
     */
    private void drawCardsForNextPlayer(int currentPlayerIndex, int numCards) {
        int nextPlayerIndex = isNotReverse ? 
            (currentPlayerIndex + 1) % players.length : 
            (currentPlayerIndex - 1 + players.length) % players.length;
        System.out.println(players[nextPlayerIndex].getName() + " is drawing " + numCards + " cards.");
        for(int k = 0; k < numCards; k++) {
            drawCard(players[nextPlayerIndex]);
        }
    }
    /**
     * Draws a card for a player.4
     * 
     * @param player the player to draw a card
     */
    protected void drawCard(Player player) {
        player.addCard(deck.drawCard());
    }
    /**
     * Applies effects of special cards.
     * 
     * @param card checks to see if the card needs to apply any effects and if it
     * does the switch case will mutate according variables
     * @param playerIndex the index of the player who will be taking the cards for 
     * plus 2 and plus 4 because the int is easier to resolve than the actual player
     * cards
     */
    private void applyCardEffect(UNOCard card, int playerIndex) {
        switch(card.getRank()) {
            case "Skip":
                skip = true;
                break;
            case "Reverse":
                isNotReverse = !isNotReverse;
                break;
            case "Draw Two":
                skip = true;
                drawCardsForNextPlayer(playerIndex, 2);
                break;
            case "Wild Draw Four":
                handleWildCard(card, players[playerIndex].handleWild(this), playerIndex);
                break;
            case "Wild":
                handleWildCard(card, players[playerIndex].handleWild(this));
                break;
        }
    }
    /**
     * Handles wild card logic for setting suit
     * 
     * @param card The Wildcard that needs to be handled
     * @param newSuit the new suit to set the wildcard as
     */
    private void handleWildCard(UNOCard card, String newSuit) {
        card.setSuit(newSuit);
        System.out.println("New suit is " + card.getSuit());
    }
    /**
     * Handles wild card logic for setting suit
     * 
     * @param card The Wildcard that needs to be handled
     * @param newSuit the new suit to set the wildcard as
     * @param playerIndex the player of the card who played the Global Wild Draw Four
     * that needs to give it to the next person
     */
    private void handleWildCard(UNOCard card, String newSuit, int playerIndex) {
        card.setSuit(newSuit);
        System.out.println("New suit is " + card.getSuit());
        drawCardsForNextPlayer(playerIndex, 4);
        skip = true;
    }
    /**
     * Creates the topcard of the game to start playing
     */
    private void initializeTopCard() {
        topCard = deck.drawCard();
        while(topCard.getSuit().equals("Wild")) {
            deck.addCard(topCard);
            deck.shuffle();
            topCard = deck.drawCard();
        }
    }
    /**
     * Main method for the class UNO. This is where you actually play the game. 
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many total players do you want to play the game?");
        System.out.println("The minimum amount of players needed is 2.");
        System.out.println("The maximum amount of players needed is 6.");
        int numOfPlayers = 0;
        boolean validInput = false;
        while(!(numOfPlayers >= 2 && numOfPlayers <= 6) || !validInput) {
            try {
                numOfPlayers = scanner.nextInt();
                if(numOfPlayers >= 2 && numOfPlayers <= 6) {
                    validInput = true;
                }
                else {
                    System.out.println("ENTER A VALID NUMBER IN THE RANGE");
                }
            }
            catch(Exception e) {
                System.out.println("ENTER AN INTEGER");
                scanner.nextLine();
            }
        }
        scanner.close();

        @SuppressWarnings("unused")
        UNO uno = new UNO(numOfPlayers);
    }
}