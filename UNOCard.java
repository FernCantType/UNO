/**
 * A UNO card object with its state consisting of its suit and rank. I based this off
 * the in-class assignment were we made a card object.
 * 
 * Lorenzo Canali
 * 10/25/24
 */
public class UNOCard   {
    private String suit;
    private String rank;
    /**
     * Intializes are suit and rank fields
     * 
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    protected UNOCard(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }
    /**
     * Gets the suit value of the card
     * 
     * @return the suit field
     */
    protected String getSuit() {
        return suit;
    }
    /**
     * Gets the rank value of the card
     * 
     * @return the rank field
     */
    protected String getRank() {
        return rank;
    }
    /**
     * Returns both the rank and suit of the card in one formal string.
     * 
     * @return A string of the rank and suit
     */
    protected String getName() {
        return getSuit() + " " + getRank();
    }
    /**
     * Get the value of the rank  Skip = 10, Draw Two = 11, Reverse = 12, Wild = 13, 
     * Wild Draw Four = 14 and return it
     * 
     * @return the value of the rank
     */
    protected int getRankValue() {
        int value = 0;
        for(int i = 0; i <= 9; i++) { 
            if(rank.equals(String.valueOf(i))) {
                value = i;
                break; 
            }
        }
        String[] ranks = new String[] {"Skip", "Draw Two", "Reverse", "Wild", "Wild Draw Four"};
        for(int i = 0; i < ranks.length; i++) {
            if(rank.equals(ranks[i])) {
                value = i + 10;
                break;
            }
        }
        return value;
    }
    /**
     * Get the value of the suit Red = 1, Yellow = 2, Green = 3, Blue = 4, 5 is Global 
     * and return it
     * 
     * @return the value of suit
     */
    protected int getSuitValue() {
        String[] suitArray = {"Red", "Yellow", "Green", "Blue", "Global"};;
        int value = 0;
        for(int i = 0; i < suitArray.length; i++) {
            if(suit == suitArray[i]) { 
                value = i; 
            }
        }
        return value;
    }
    /**
     * Allows us to change the suit of the card
     * 
     * @param suit the suit of the card
     */
    protected void setSuit(String suit) {
       this.suit = suit; 
    }
    /**
     * Allows us to change the rank of the card
     * 
     * @param rank the rank of the card
     */
    protected void setRank(String rank) {
        this.rank = rank;
    }
    /**
     * Allows the comparison of another card to see if they can be played on top of each
     * other
     * 
     * @param compareCard the card to compare the suit and rank of
     */
    protected boolean matches(UNOCard compareCard) {
        if(compareCard.getRank().equals(getRank()) || 
                    compareCard.getSuit().equals(getSuit()) ||
                    compareCard.getSuit().equals("Global")) {
            return true;
        }
        return false;
    }
}