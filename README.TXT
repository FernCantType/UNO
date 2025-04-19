------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: UNO
PURPOSE OF PROJECT: Class Project & Allows the user to play the game UNO
VERSION or DATE: 12/16/2024
HOW TO START THIS PROJECT: Use the main method on the UNO class
AUTHORS: Lorenzo Canali

USER INSTRUCTIONS: Add the number of total players then choose how many human players
you want to add; the rest will be bots. Give your real players names if they exist, which
might not always be the case because you can have 0 real players. Then when it is your turn pick your card,
you can pick the index of the card you want to play. If is a special card certain 
special things will happen. Make sure to only use valid responses or you will have to 
keep repeating. The bots will auto-play when it is their turn. The game goes until someone
wins, bot or not.

Demonstrates OOP principles: Encapsulation, Inheritance, Polymorphism, Abstraction:
 * 
 * Encapsulation: Encapsulation is achieved by using private attributes and exposing 
 * them through methods. My UNODeck Class demonstrates encapsulation by hiding the 
 * internal details of how the deck of cards is stored and managed by using a private 
 * cards list and providing controlled access through public methods like shuffle(), 
 * drawCard(), and isEmpty().
 * 
 * Inherticance: Player Class is the superclass. HumanPlayer and Bot inherit from 
 * Player and override abstract methods takeTurn() and handleWild() to define 
 * player-specific behavior. This allows the code to treat both human players and 
 * bots uniformly while providing specific functionality.
 * 
 * Polymorphism: The abstract methods takeTurn(UNO game) and handleWild(UNO game) are
 * overridden in HumanPlayer and Bot to implement their distinct behaviors. Method 
 * Overloading for handleWildCard() one made for Global Wild and one for Global Wild 
 * Draw Four. Also, players[i].takeTurn(this) works seamlessly for both HumanPlayer 
 * and Bot because they share the same interface.
 * 
 * Abstraction: Abstraction hides implementation details and exposes only essential 
 * functionality and the functionality how the game handles special cards i.e. Skip
 * Draw Two, Reverse, and Wild cards are hidden from the user but it will always work