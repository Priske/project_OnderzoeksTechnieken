/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.ArrayList;
import java.util.stream.Collectors;
import project.domain.ActionEnum;
import project.domain.Card;
import project.domain.strategies.PlayStyle;

/**

 @author Ben
 */
public class Player {

	private final ArrayList<Card> hand = new ArrayList<>();
	private int losses = 0;
	private final String name;
	private final PlayStyle playStyle;
	private int wins = 0;

	public Player(String name, PlayStyle playStyle) {
		this.name = name;
		this.playStyle = playStyle;
	}

	public void getCard(Card card) {
		this.hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public int getLosses() {
		return this.losses;
	}

	public void loss() {
		this.losses++;
	}

	public String getName() {
		return this.name;
	}

	public int getWins() {
		return wins;
	}

	public void win() {
		this.wins++;
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

	@Override
	public String toString() {
		return this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
	}
}
