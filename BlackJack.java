package com.example.yonsu.blackjack;
import java.util.*;

/** An Object class to represent a whole BlackJack game
 *
 * @author Yon Su Kim
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */

public class BlackJack {

    protected Player player;
    protected Player dealer;
    protected Deck deck;

    /**
     * Constructor that instantiates BlackJack's attributes.
     */
    protected BlackJack(){
        player = new Player();
        dealer = new Player();
        deck = new Deck();
    }

}
