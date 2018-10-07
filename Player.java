package com.example.yonsu.blackjack;
import java.lang.reflect.Array;
import java.util.ArrayList;

/** An Object class to represent each player in a game of BlackJack
 *
 * @author Yon Su Kim
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */

public class Player{
    public ArrayList<Card> hand;
    public boolean myTurn;
    public int hand_value;
    public int numHits;

    /**
     * A constructor that instantiates player's attributes.
     */
    public Player()
    {
        hand = new ArrayList<Card>();
        myTurn = false;
        numHits = 0;
        hand_value= 0 ;
    }

    /**
     *Adds another card from deck to hand
     * @param d
     * @return void
     */
    public void Hit(Deck d)
    {
        Card rand_Card = d.drawCard();
        this.hand.add(rand_Card);
    }


}