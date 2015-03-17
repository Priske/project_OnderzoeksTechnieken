/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.ArrayList;
import project.domain.ActionEnum;
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

	public ActionEnum play(ArrayList<Card> playerHand) {
		return this.playStyle.play(this.hand, playerHand);
	}
	/*
	 public ActionEnum play() {
	 int value = 0;
	 value = this.hand.stream().mapToInt(c -> c.getValue()).sum();
	 System.out.println(value);
	 return
	 }
	 */
}
