/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */
public class ThorpsPlayStyle implements PlayStyle {

    @Override
    public ActionEnum play(ArrayList<Card> dealerHand, ArrayList<Card> playerHand) {
        switch(dealerHand.get(0).getFace()){
            case "Ace":
                if(getValue(playerHand)<17)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
                    
              
            case "Deuce": 
                if(getValue(playerHand)<13)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
            case "Three":
                if(getValue(playerHand)<13)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
            case "Four": 
            case "Five": 
            case "Six":
            case "Seven": 
            case "Eight": 
            case "Nine": 
            case "Ten":
            case "Jack":
            case "Queen":
            case "King":
                if(getValue(playerHand)<17)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
                
            
        }
        return null;
    }
     public int getValue(ArrayList<Card> hand) {

                int aces= 0;
		int valueOutput = hand.stream().mapToInt(c -> c.getValue()).sum();
                for(Card c : hand){
                    if(c.getFace().equals("Ace")){
                        aces++;
                    }
                }
                if(aces == 0){
                 return valueOutput;    
                }else{
                    if(valueOutput <= 11 && aces == 1){
                        valueOutput+=9;
                    }else{
                      
                    }
                    return valueOutput;
                }
		

	}
     
     public int checkForAces(ArrayList<Card> hand){
         int aces=0;
         for(Card card: hand){
             if(card.getFace().equals("Ace")){
                 aces+=1;
             }
             
         }
         return aces;
     }
}
