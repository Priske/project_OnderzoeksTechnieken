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
public class Player {

    private String name;
    private ArrayList<Card> hand;
    public Player(String name) {
    this.name = name;
    hand = new ArrayList<Card>();
    }
    
    public String getName(){
        return name;
    }
    public void getCard(Card card){
      hand.add(card);
    }
    
    public ArrayList<Card> getHand(){
        return hand;
    }
    public String toString(){
        String output="";
        for(Card card : hand){
           output+= card.toString();
        }
        return output;
    }
    
    public  void Play(){
        int value= 0;
        for(Card c: hand){
            value+= c.getValue();
            
        }
        System.out.println(value);
                
    }
    
}
