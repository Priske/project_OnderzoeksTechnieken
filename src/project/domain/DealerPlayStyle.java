/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

import java.util.ArrayList;
import project.domain.players.Player;

/**

 @author Ben
 */
public class DealerPlayStyle  {

	
	public ActionEnum play(ArrayList<Card> dealerHand, ArrayList<Player> players) {
            ArrayList<Player> clean= new ArrayList<>();
            for(Player player: players){
                if(getValue(player.getHand()) > 21){
                    
                }else{
                    clean.add(player);
                }
            }
            if(clean.size() == 1){
                if (getValue(clean.get(0).getHand()) > getValue(dealerHand) ){
                    return ActionEnum.HIT;
                }else{
                    return ActionEnum.STAY;
                }
            }else{
                if(getValue(dealerHand)<=16) {
			return ActionEnum.HIT;
		} else {
			return ActionEnum.STAY;
		}
                
            }
            
            
            
		
                    
		
	}

	public int getValue(ArrayList<Card> hand) {

		int aces = 0;
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
