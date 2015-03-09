/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Ben
 */
public class DeckOfCards {
    private ArrayList<Card> deck;
    private static final int NUMBER_OF_CARDS= 52;
    private int aantalDecks;

    public DeckOfCards(int decks) {
        deck = new ArrayList<Card>();
        String[] faces = {"Ace","Deuce","Three","Four","Five", "Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
        String [] suits= {"Hearts","Diamonds","Clubs","Spades"};
        aantalDecks= decks;
        
        for(int t=0; t< decks;t++){
            for(int c = 0;c < NUMBER_OF_CARDS;c++){
                  deck.add(new Card(faces[c % 13], suits[c / 13]));
                   
            }
        }
        

    }
    public ArrayList<Card> getDeck(){
        return deck;
    }
    public void shoufle(){
    
        for(int s= 0; s< aantalDecks*2; s++)
        {
            Collections.shuffle(deck);
        }
        this.printDeck();
            
    }
    
    
      public void printDeck(){
          int output=0;
          for(Card card: deck){
              card.printCard();
              output+=1;
          }
          System.out.println("Aantal kaarten:"+output + ", Aantal Decks: "+ aantalDecks);
          
      }  
        
     
    
    
}
