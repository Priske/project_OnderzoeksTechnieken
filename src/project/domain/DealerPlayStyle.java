/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

import java.util.ArrayList;

/**

 @author Ben
 */
public class DealerPlayStyle implements PlayStyle {

	@Override
	public ActionEnum play(ArrayList<Card> DealerHand, ArrayList<Card> PlayerHand) {
		int playerValue = this.getValue(PlayerHand);
		int dealerValue = this.getValue(DealerHand);
		if(playerValue > dealerValue) {
			return ActionEnum.Hit;
		} else {
			return ActionEnum.Stay;
		}
	}

	public int getValue(ArrayList<Card> hand) {
		int valueOutput = hand.stream().mapToInt(c -> c.getValue()).sum();
		return valueOutput;
	}
}
