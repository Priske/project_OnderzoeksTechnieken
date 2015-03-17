/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.ArrayList;
import project.domain.Action;
import project.domain.Card;
import project.domain.CardDeck;
import project.domain.strategies.DealerPlayStyle;

/**

 @author Ben
 */
public class Dealer extends Participant {

	private final CardDeck deck;
	private final DealerPlayStyle playStyle;

	public Dealer(int numberOfDecks, DealerPlayStyle ps) {
		this.deck = new CardDeck(numberOfDecks);
		this.playStyle = ps;
	}

	public void deal(ArrayList<Player> players) {
		players.stream().forEach(player -> player.giveCard(this.deck.getTopCard()));
		this.hand.add(this.deck.getTopCard());
		players.stream().forEach(player -> player.giveCard(this.deck.getTopCard()));
		this.hand.add(this.deck.getTopCard());
	}

	public void deal(Player player) {
		player.giveCard(this.deck.getTopCard());
	}

	public void takeCard() {
		this.giveCard(this.deck.getTopCard());
	}

	public Action play(ArrayList<Player> playerHand) {
		return this.playStyle.play(this, playerHand);
	}

	public void resetDeck() {
		this.deck.reset();
	}

	public Card showTopCard() {
		return this.hand.get(0);
	}
}
