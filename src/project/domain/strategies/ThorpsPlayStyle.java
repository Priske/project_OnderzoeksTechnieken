/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.strategies;

import java.util.ArrayList;
import project.domain.ActionEnum;
import project.domain.Card;
import project.domain.CardFace;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class ThorpsPlayStyle implements PlayStyle {

	private Card getNonAceCard(ArrayList<Card> hand) {
		for (Card card : hand) {
			if(card.getFace() != CardFace.ACE) {
				return card;
			}
		}
		return null;
	}

	@Override
	public ActionEnum play(Player player, Dealer dealer) {
		if(player.countAces() == 1 && player.getValue() < 16) {
			return this.acePlay(dealer.showTopCard(), this.getNonAceCard(player.getHand()));
		} else {
			if(player.countAces() == 2) {
				if(player.getValue() < 17) {
					return ActionEnum.HIT;
				} else {
					return ActionEnum.STAY;
				}
			} else {
				System.out.println(dealer.showTopCard().getFace());
				switch (dealer.showTopCard().getFace()) {
					case ACE:
						if(player.getValue() < 17) {
							return ActionEnum.HIT;
						} else {
							return ActionEnum.STAY;
						}

					case DEUCE:
						if(player.getValue() < 13) {
							return ActionEnum.HIT;
						} else {
							return ActionEnum.STAY;
						}
					case THREE:
						if(player.getValue() < 13) {
							return ActionEnum.HIT;
						} else {
							return ActionEnum.STAY;
						}
					case FOUR:
					case FIVE:
					case SIX:
						if(player.getValue() < 12) {
							return ActionEnum.HIT;
						} else {
							return ActionEnum.STAY;
						}
					case SEVEN:
					case EIGHT:
					case NINE:
					case TEN:
					case JACK:
					case QUEEN:
					case KING:
						if(player.getValue() < 17) {
							return ActionEnum.HIT;
						} else {
							return ActionEnum.STAY;
						}
					default:
						return null;
				}
			}
		}
		/*
		 System.out.println(dealerHand.get(0).getFace());
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


		 }
		 */
	}

	private ActionEnum acePlay(Card dealerCard, Card playerCard) {
		switch (playerCard.getFace()) {
			case DEUCE:
			case THREE:
			case FOUR:
			case FIVE:
			case SIX:
				return ActionEnum.HIT;
			case SEVEN:
				if(dealerCard.getValue() < 9) {
					return ActionEnum.STAY;
				} else {
					return ActionEnum.HIT;
				}
			case EIGHT:
			case NINE:
				return ActionEnum.STAY;
			default:
				return null;
		}
	}
}
