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
    public ActionEnum play(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        if(checkForAces(playerHand) == 1 && getValue(playerHand)<16){
            return acePlay(dealerHand.get(0), getNonAceCard(playerHand));
        }else{
            if(checkForAces(playerHand) ==2){
                if(getValue(playerHand)<17) {
			return ActionEnum.HIT;
		} else {
			return ActionEnum.STAY;
		}
            }else{
                System.out.println(dealerHand.get(0).getFace());
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
                if(getValue(playerHand)<12){
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
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
                
            }
            
        }
        /*System.out.println(dealerHand.get(0).getFace());
           switch(dealerHand.get(0).getFace()){
            case "Ace":
                System.out.println("A");
                if(getValue(playerHand)<17)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
                  
              
            case "Deuce": 
                System.out.println("2");
                if(getValue(playerHand)<13)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
            case "Three":
                System.out.println("3");
                if(getValue(playerHand)<13)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
            case "Four": 
            case "Five": 
            case "Six":
                System.out.println("456");
                if(getValue(playerHand)<12){
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
            case "Seven": 
            case "Eight": 
            case "Nine": 
            case "Ten":
            case "Jack":
            case "Queen":
            case "King":
                System.out.println("78910JQK");
                if(getValue(playerHand)<17)
                {
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
                
            
        }*/
        

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
     public ActionEnum acePlay(Card dealer, Card player){
         switch(player.getFace()){
            case "Deuce":
            case "Three":
            case "Four":
            case "Five":
            case "Six":
                return ActionEnum.HIT;
            case "Seven":
                if(dealer.getValue() <9){
                    return ActionEnum.STAY;
                }else{
                    return ActionEnum.HIT;
                }
            
            case "Eight":
            case "Nine":
                return ActionEnum.STAY;
                
        }
         return null;
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
     public Card getNonAceCard(ArrayList<Card> hand){
         for(Card card: hand){
             if(card.getFace().equals("Ace")){
                 
             }else{
                 return card;
             }
         
     }
         return null;
     }
}
