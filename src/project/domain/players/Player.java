/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.ArrayList;
import project.domain.Card;

/**

 @author Ben
 */
public class Player {

	private final ArrayList<Card> hand = new ArrayList<>();
	private final String name;

	public Player(String name) {
		this.name = name;
	}

	public void play() {
		int value = 0;
		value = this.hand.stream().mapToInt(c -> c.getValue()).sum();
		System.out.println(value);
	}

	public void getCard(Card card) {
		this.hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		String output = this.hand.stream().map(card -> card.toString()).reduce("", String::concat);
		return output;
	}
}
