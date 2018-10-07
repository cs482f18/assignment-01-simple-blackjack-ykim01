package com.example.yonsu.blackjack;
import java.util.ArrayList;
import java.util.Collections;


/** An Object class to represent a Deck of 52 cards used in a game of BlackJack
 *
 * @author Yon Su Kim
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */

class Deck {
    public ArrayList<Card> deck;

    /**
     * Constructor that instantiates Deck's attributes.
     */
    Deck() {
        this.deck = fill();
        this.deck_Shuffle();
    }

    /**
     * Fills deck with all 52 cards used to play BlackJack
     * @return Arraylist of type Card
     */
    public ArrayList<Card> fill(){
        deck = new ArrayList<Card>();
        String[] suits = {"clubs", "diamonds", "spades", "hearts"};
        String[] ranks = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};

        for(int i = 0;i < suits.length; i++){
            for(int j = 0; j < ranks.length; j++){
                deck.add(new Card(suits[i],ranks[j]));
            }
        }
        return deck;
    }

    /**
     * Removes a random card from deck to put into hand
     * @return Card
     */
    public Card drawCard() {
            this.deck = deck_Shuffle();
            return this.deck.remove(0);
        }


    /**Shuffles the deck using Java's own shuffle method
     * @return  deck (ArrayList of type Card)
     */
    public ArrayList<Card> deck_Shuffle() {
        Collections.shuffle(deck);
        return deck;
    }
}