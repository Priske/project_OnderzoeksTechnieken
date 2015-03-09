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
	public ActionEnum play(ArrayList<Card> dealerHand, ArrayList<Card> playerHand) {
		int playerValue = this.getValue(playerHand);
		int dealerValue = this.getValue(dealerHand);
		if(playerValue > dealerValue) {
			return ActionEnum.HIT;
		} else {
			return ActionEnum.STAY;
		}
	}

	public int getValue(ArrayList<Card> hand) {
		int valueOutput = 0;
		for (Card c : hand) {
			int value = c.getValue();
			valueOutput += value;
		}
		return valueOutput;
	}
}
