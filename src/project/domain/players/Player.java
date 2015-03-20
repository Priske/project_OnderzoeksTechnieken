/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import project.domain.Action;
import project.domain.strategies.PlayStyle;

/**

 @author Ben
 */
public class Player extends Participant {

	private final String name;
	private final PlayStyle playStyle;
	private boolean done = false;

	public Player(String name, PlayStyle playStyle) {
		this.name = name;
		this.playStyle = playStyle;
	}

	public void done() {
		this.done = true;
	}

	public boolean isDone() {
		return this.done;
	}

	public String getName() {
		return this.name;
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
