import java.util.Scanner;
/**
 * Is a subclass of the parent class Player. This class will create objects intended 
 * for real people. It will allow the user to play a turn using a scanner and a while 
 * loop
 * 
 * @author: Lorenzo Canali
 * version: 12/15/2024
 */
public class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);
    /**
     * Constructor for the subclass HumanPlayer for the superclass Player
     * 
     * @param name the name of the human player
     */
    protected HumanPlayer(String name) {
        super(name);
    }
    /**
     * Overrides the abstract method takeTurn and allows the player to manually play 
     * the card they want to play until they play a valid card or choose to draw a 
     * card. Uses a while loop and a scanner to achieve this logic. Has exception handling for invalid inputs
     */
    @Override
    protected void takeTurn(UNO game) {
        boolean validMove = false;
        while(!validMove) {
            System.out.println(name + ", your cards:");
            for(int i = 0; i < cardsInHand.size(); i++) {
                System.out.println((i + 1) + ". " + cardsInHand.get(i).getName());
            }
            System.out.print("Choose a card to play (or type 0 to draw): ");
            int choice = -2;
            boolean validInput = false;
            while(!(choice >= -1 && choice <= cardsInHand.size()) || !validInput) {
                try {
                    choice = scanner.nextInt() - 1;
                    if(choice >= -1 && choice <= cardsInHand.size() - 1) {
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
            if(choice == -1) {
                game.drawCard(this);
                System.out.println("Drew a card.");
                validMove = true;
            } 
            else if(choice >= 0 && choice < cardsInHand.size()) {
                validMove = game.playCard(this, choice);
                if(!validMove) {
                    System.out.println("Invalid move. Try again.");
                }
            }
        }
    }
    /**
     * Overrides the abstract method handleWild and allows the user to select the new
     * suit they want. Keeps repeating until they enter a valid suit index. Has exception handling for invalid inputs
     */
    @Override 
    public String handleWild(UNO game) {
        String[] suits = {"Red", "Yellow", "Green", "Blue"};
        System.out.println("Your choices for your new suit colors are:");
        for(int i = 1; i < suits.length + 1; i++) {
            System.out.println(i + ". " + suits[i - 1]);
        }
        boolean valid = false;
        String newSuit = "";
        while(!valid) {
            int choice = 0;
            boolean validInput = false;
            while(!validInput) {
                try {
                    choice = scanner.nextInt() - 1;
                    validInput = true;
                }
                catch(Exception e) {
                    System.out.println("ENTER AN INTEGER");
                    scanner.nextLine();
                }
            }
            switch(choice) {
                case 1:
                case 2:
                case 3:
                case 0:
                    newSuit = suits[choice];
                    valid = true;
                    break;
                default: 
                    System.out.println("ENTER A VALID INTEGER LISTED");
            }
        }
        return newSuit;
    }
}