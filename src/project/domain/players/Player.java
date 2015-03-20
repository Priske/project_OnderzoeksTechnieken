/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.Collections;
import java.util.List;
import project.domain.Action;
import project.domain.Card;
import project.domain.strategies.PlayStyle;

/**

 @author Ben
 */
public class Player extends Participant {

	private boolean done = false;
	private final PlayStyle playStyle;

	public Player(String name, PlayStyle playStyle) {
		super(name);
		this.playStyle = playStyle;
	}

	public Player(String name, PlayStyle playStyle, List<Card> cards) {
		this(name, playStyle);
		this.hand.addAll(cards);
	}

	public void done() {
		this.done = true;
	}

	public List<Card> getHand() {
		return Collections.unmodifiableList(this.hand);
	}

	public boolean isDone() {
		return this.done;
	}

	public Action play(Dealer dealer) {
		Action action = this.playStyle.play(this, dealer);
		if(action == Action.HIT) {
			this.requestCard(dealer);
		}
		return action;
	}

	private void requestCard(Dealer dealer) {
		this.addCard(dealer.deal());
	}
}
