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
public class DealerPlayStyle implements PlayStyle {

    

    @Override
    public  ActionEnum Play(ArrayList<Card> DealerHand, ArrayList<Card> PlayerHand) {
        int dealerValue;
        int playerValue;
        playerValue =this.getValue(PlayerHand);
        dealerValue = this.getValue(DealerHand);
        
        if(playerValue>dealerValue){
            return ActionEnum.Hit;
        }else{
            return ActionEnum.Stay;
        }
        
    }
    
    public int getValue(ArrayList<Card> hand){
        int valueOutput=0;
        for(Card c: hand){
            valueOutput+= c.getValue();
            
        }
        return valueOutput;
    }
    
}
