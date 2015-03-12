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
import project.domain.DeckOfCards;
import project.domain.strategies.DealerPlayStyle;

/**

 @author Ben
 */
public class Dealer {

	private DeckOfCards deck;
	private final ArrayList<Card> hand = new ArrayList<>();
	private final DealerPlayStyle pS;
	private int wins = 0;

	public Dealer(int Amount, DealerPlayStyle ps) {
		this.deck = new DeckOfCards(Amount);
		this.pS = ps;
	}

	public ArrayList<Player> deal(ArrayList<Player> players) {
		for (Player player : players) {
			player.getCard(this.getTopCard());
		}
		hand.add(getTopCard());
		for (Player player : players) {
			player.getCard(this.getTopCard());
		}
		hand.add(getTopCard());
		return players;
	}

	public Player deal(Player player) {
		player.getCard(this.getTopCard());
		return player;
	}

	/*
	 public Dealer deal(Dealer player) {
	 player.getCard(this.getTopCard());
	 return player;
	 }
	 */
	public void getCard() {
		this.hand.add(getTopCard());
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public ActionEnum play(ArrayList<Player> playerHand) {
		return this.pS.play(this.hand, playerHand);
	}

	public void retrieveCards(ArrayList<Player> players) {
		for (Player player : players) {
			//System.out.println("test");
			ArrayList<Card> hand = player.getHand();
			for (Card card : hand) {
				deck.returnCardInDeck(card);

			}
			player.getHand().clear();
			for (Card card : this.hand) {
				deck.returnCardInDeck(card);
			}
			this.hand.clear();
			//System.out.println(deck.getDeckSize());
			//deck.printDeck();
		}
	}

	@Override
	public String toString() {
		String output = this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
		return output;
	}

	private Card getTopCard() {

		Card card = this.deck.getCards().get(0);
		this.deck.getCards().remove(0);
		//System.out.println("cards in deck: " + this.deck.getDeckSize());
		return card;
	}
}
