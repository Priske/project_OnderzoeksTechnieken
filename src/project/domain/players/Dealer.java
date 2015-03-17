/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.ArrayList;
import project.domain.ActionEnum;
import project.domain.CardDeck;
import project.domain.strategies.DealerPlayStyle;

/**

 @author Ben
 */
public class Dealer extends Participant {

	private CardDeck deck;
	private final DealerPlayStyle playStyle;

	public Dealer(int Amount, DealerPlayStyle ps) {
		this.deck = new CardDeck(Amount);
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

	/*
	 public Dealer deal(Dealer player) {
	 player.getCard(this.getTopCard());
	 return player;
	 }
	 */
	public void getCard() {
		this.hand.add(this.deck.getTopCard());
	}

	public ActionEnum play(ArrayList<Player> playerHand) {
		return this.playStyle.play(this.hand, playerHand);
	}
}
