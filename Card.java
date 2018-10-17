package com.example.yonsu.blackjack;

/** An Object class to represent each Card used in a game of BlackJack
 *
 * @author Yon Su Kim
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */

class Card {

    /** Add javadoc description of your instancd variables. E.g., The rank of a Card */
    protected String rank;
    protected String suit;
    protected int value;

    /**
     * A parameterized Constructor that instantiates Card's attributes.
     * @param ranks  -- Add parameter description here 
     * @param suits  -- the suit of the card
     */
    Card(String suits, String ranks) {
        this.rank = ranks;
        this.suit = suits;
    }

    /**
     * @return the card's corresponding image name
     */
    public String toString() {
        return rank + "_of_" + suit;
    }

    /**
     *@return the corresponding int value of a card's rank
     */
    public int getValue() {
        if(rank.equals("ace"))
            value = 11;
        else if(rank.equals("two"))
            value = 2;
        else if(rank.equals("three"))
            value = 3;
        else if(rank.equals("four"))
            value = 4;
        else if(rank.equals("five"))
            value = 5;
        else if(rank.equals("six"))
            value = 6;
        else if(rank.equals("seven"))
            value = 7;
        else if(rank.equals("eight"))
            value = 8;
        else if(rank.equals("nine"))
            value = 9;
        else if(rank.equals("ten"))
            value = 10;
        else if(rank.equals("jack"))
            value = 10;
        else if(rank.equals("queen"))
            value = 10;
        else if(rank.equals("king"))
            value = 10;
        return this.value;
    }




}
