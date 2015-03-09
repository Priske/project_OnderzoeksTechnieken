/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */
public class Dealer {
    DeckOfCards deck;
    ArrayList<Card> hand;
    PlayStyle pS ;

    public Dealer(int Amount, PlayStyle ps) {
        deck = new DeckOfCards(Amount);
        hand = new ArrayList<Card>();
        this.Shoufle();
        this.pS = ps;
        
        
    }
    private Card getTopCard(){
        Card card = deck.getDeck().get(0);
        
        deck.getDeck().remove(0);
        return card;
        
    }
    private void Shoufle(){
        deck.shoufle();
    }
    
    public Player[] deal(Player[] players){
        this.getTopCard();
        for(Player player: players){
           player.getCard(this.getTopCard());
           
        }
        this.getCard(this.getTopCard());
        
        for(Player player: players){
           player.getCard(this.getTopCard());
        }
        
        this.getCard(this.getTopCard());
        
        return players;
    }
    public Player deal(Player player){
        player.getCard(this.getTopCard());
        
        return player;
    }
       public Dealer deal(Dealer player){
        player.getCard(this.getTopCard());
        
        return player;
    }
    public ActionEnum Play(ArrayList<Card> playerHand){
       return pS.Play(hand,playerHand );
        
    }
    public void getCard(Card card){
      hand.add(card);
    }
    
    public String toString(){
        String output="";
        for(Card card : hand){
           output+= card.toString();
        }
        return output;
    }
    
    
}
