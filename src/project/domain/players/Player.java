/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import project.domain.Action;
import project.domain.Card;
import project.domain.strategies.PlayStyle;

/**

 @author Ben
 */
public class Player extends Participant {

	private final String name;
	private final PlayStyle playStyle;

	public Player(String name, PlayStyle playStyle) {
		this.name = name;
		this.playStyle = playStyle;
	}

	public String getName() {
		return this.name;
	}

	public void giveCard(Card card) {
		this.hand.add(card);
	}

	public Action play(Dealer dealer) {
		return this.playStyle.play(this, dealer);
	}
}
