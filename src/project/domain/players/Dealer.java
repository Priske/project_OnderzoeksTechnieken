/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.ArrayList;
import java.util.Iterator;
import project.domain.ActionEnum;
import project.domain.Card;
import project.domain.DeckOfCards;
import project.domain.PlayStyle;

/**

 @author Ben
 */
public class Dealer {

	private final DeckOfCards deck;
	private final ArrayList<Card> hand = new ArrayList<>();
	private final PlayStyle pS;

	public Dealer(int Amount, PlayStyle ps) {
		this.deck = new DeckOfCards(Amount);
		this.shoufle();
		this.pS = ps;
	}

	public ActionEnum play(ArrayList<Card> playerHand) {
		return this.pS.play(this.hand, playerHand);
	}

	public Player[] deal(Player[] players) {
		this.getTopCard();
		for (Player player : players) {
			player.getCard(this.getTopCard());
		}
		this.getCard(this.getTopCard());
		for (Player player : players) {
			player.getCard(this.getTopCard());
		}
		this.getCard(this.getTopCard());
		return players;
	}

	public Player deal(Player player) {
		player.getCard(this.getTopCard());
		return player;
	}

	public Dealer deal(Dealer player) {
		player.getCard(this.getTopCard());
		return player;
	}

	public void getCard(Card card) {
		this.hand.add(card);
	}

	@Override
	public String toString() {
		String output = this.hand.stream().map(card -> card.toString()).reduce("", String::concat);
		return output;
	}

	private void shoufle() {
		this.deck.shoufle();
	}

	private Card getTopCard() {
//		Card card = this.deck.getDeck().get(0);
		this.deck.getDeck().remove(0);
		Iterator<Card> iter = this.deck.getDeck().iterator();
		Card card = iter.next();
		iter.remove();
		return card;
	}
}
