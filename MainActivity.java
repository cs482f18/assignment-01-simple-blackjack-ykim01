package com.example.yonsu.blackjack;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

/** Main controller to run BlackJack app
 *
 * @author Yon Su Kim
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public class MainActivity  extends AppCompatActivity{

    //Instantiate needed Objects to run game
    public static BlackJack game;

    //index to go through image ArrayLists
    int handIndex = 2;

    //creates ArrayLists of ImageView type to update card images
    final ArrayList<ImageView> playerHand = new ArrayList<>();
    final ArrayList<ImageView> dealerHand = new ArrayList<>();

    @Override
    /**
     * Runs when app is loaded, instantiates game and Image ArrayLists
     *@return Nothing
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new BlackJack();

        //adds to ArrayLists of images for player's hand
        playerHand.add((ImageView) findViewById(R.id.Player_card1));
        playerHand.add((ImageView) findViewById(R.id.Player_card2));
        playerHand.add((ImageView) findViewById(R.id.Player_card3));
        playerHand.add((ImageView) findViewById(R.id.Player_card4));
        playerHand.add((ImageView) findViewById(R.id.Player_card5));

        //adds to ArrayLists of images for dealer's hand
        dealerHand.add((ImageView) findViewById(R.id.Dealer_card1));
        dealerHand.add((ImageView) findViewById(R.id.Dealer_card2));
        dealerHand.add((ImageView) findViewById(R.id.Dealer_card3));
        dealerHand.add((ImageView) findViewById(R.id.Dealer_card4));
        dealerHand.add((ImageView) findViewById(R.id.Dealer_card5));

        //Fills cards with back images
        for (int i = 0; i < 5; i++) {
            playerHand.get(i).setImageResource(getResources().getIdentifier("back", "drawable", getPackageName()));
            dealerHand.get(i).setImageResource(getResources().getIdentifier("back", "drawable", getPackageName()));
        }

    }

            /**
             * Runs when player presses 'New Game' button
             * @param v  (View v)
             *@return Nothing
            */
            public void game_NewGame(View v){
                //make a completely new game
                game = new BlackJack();

                //resets buttons to enabled
                Button hitButton = findViewById(R.id.Hit_Button);
                Button standButton = findViewById(R.id.Stand_Button);
                hitButton.setEnabled(true);
                standButton.setEnabled(true);

                final TextView playerScore = findViewById(R.id.playerScore);
                final TextView dealerScore = findViewById(R.id.dealerScore);
                final TextView endText = findViewById(R.id.endText);

                //deals first two cards for players
                for (int i = 2; i < 5; i++) {
                    playerHand.get(i).setImageResource(getResources().getIdentifier("back", "drawable", getPackageName()));
                    dealerHand.get(i).setImageResource(getResources().getIdentifier("back", "drawable", getPackageName()));
                }

                //reset TextViews to default
                endText.setText(" ");
                playerScore.setText("0");
                dealerScore.setText("0");

                //resets index for image ArrayLists
                handIndex = 2;

                //clear user and dealer hands
                game.dealer.hand.clear();
                game.player.hand.clear();

                //deal first two cards
                for(int i = 0; i < 2; i++) {
                    game.player.Hit(game.deck);
                    game.dealer.Hit(game.deck);
                    String playerCard = game.player.hand.get(i).toString();
                    String dealerCard = game.dealer.hand.get(i).toString();
                    playerHand.get(i).setImageResource(getResources().getIdentifier(playerCard, "drawable", getPackageName()));
                    dealerHand.get(i).setImageResource(getResources().getIdentifier(dealerCard, "drawable", getPackageName()));
                    game.player.hand_value += game.player.hand.get(i).getValue();
                    game.dealer.hand_value += game.dealer.hand.get(i).getValue();
                }

                //end game early if either player has BlackJack
                if(game.dealer.hand_value == 21) {
                    endText.setText(R.string.lost);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                }
                if(game.player.hand_value == 21) {
                    endText.setText(R.string.blackjacked);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                }

                //Update score TextViews with new dealt cards
                playerScore.setText(String.valueOf(game.player.hand_value));
                dealerScore.setText(String.valueOf(game.dealer.hand_value));
            }


             /**
             * Runs when player presses 'Hit' button
             * @param v  (View v)
             *@return Nothing
             */
            public void game_Hit(View v) {

                //instantiates buttons and TextViews
                Button hitButton = findViewById(R.id.Hit_Button);
                Button standButton = findViewById(R.id.Stand_Button);
                final TextView playerScore = (TextView) findViewById(R.id.playerScore);
                final TextView endText = (TextView) findViewById(R.id.endText);

                //Deal card
                game.player.Hit(game.deck);

                if (game.player.numHits < 3) {
                    //get image name by getting card's string
                    String playerCard = game.player.hand.get(handIndex).toString();

                    //set image at hand index the card just drawn
                    playerHand.get(handIndex).setImageResource(getResources().getIdentifier(playerCard, "drawable", getPackageName()));

                    //update player's hand value and number of hits
                    game.player.hand_value += game.player.hand.get(handIndex).getValue();
                    game.player.numHits++;

                    //Index increases by 1 to place next drawn card in the next image space
                    handIndex++;

                    //updates player score
                    playerScore.setText(String.valueOf(game.player.hand_value));
                } else if (game.player.numHits == 3)
                {
                    //disables button if hit > 3
                    hitButton.setEnabled(false);
                }

                //checks to see if player wins or loses
                if (game.player.hand_value == 21) {
                    endText.setText(R.string.blackjacked);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                }

                if (game.player.hand_value > 21) {
                    endText.setText(R.string.lost);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                }
            }


            /**
             * Runs when player presses 'Stand' button
             * @param v  (View v)
             *@return Nothing
              */
            public void game_Stand(View v) {
                //instantiates buttons and TextViews
                Button hitButton = findViewById(R.id.Hit_Button);
                Button standButton = findViewById(R.id.Stand_Button);
                handIndex = 2;
                final TextView dealerScore = (TextView) findViewById(R.id.dealerScore);
                final TextView endText = (TextView) findViewById(R.id.endText);

                //Dealer will hit if their hand is less or equal to 17
                while(game.dealer.hand_value <= 17) {
                    //dealer draws card
                    game.dealer.Hit(game.deck);
                    game.dealer.numHits++;

                    //get image name by getting card's string
                    String dealerCard = game.dealer.hand.get(handIndex).toString();
                    dealerHand.get(handIndex).setImageResource(getResources().getIdentifier(dealerCard, "drawable", getPackageName()));
                    game.dealer.hand_value += game.dealer.hand.get(handIndex).getValue();
                    dealerScore.setText(String.valueOf(game.dealer.hand_value));
                    handIndex++;
                }

                //checks outcome of game
                if(game.player.hand_value > game.dealer.hand_value || game.dealer.hand_value > 21) {
                    endText.setText(R.string.won);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                }

                if(game.player.hand_value < game.dealer.hand_value && game.dealer.hand_value <= 21) {
                    endText.setText(R.string.lost);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                }

                if(game.player.hand_value == game.dealer.hand_value) {
                    gameEnd.setText(R.string.tied);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);

                }
            }

}

