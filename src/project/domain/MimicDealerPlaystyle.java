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
public class MimicDealerPlaystyle implements PlayStyle{

    @Override
    public ActionEnum play(ArrayList<Card> dealerHand, ArrayList<Card> playerHand) {
        int playerValue = this.getValue(playerHand);
		
		if(playerValue<17) {
			return ActionEnum.HIT;
		} else {
			return ActionEnum.STAY;
		}
        
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
                    if(valueOutput <= 11){
                        valueOutput+=9;
                    }else{
                      
                    }
                    return valueOutput;
                }
		

	}
    
}
