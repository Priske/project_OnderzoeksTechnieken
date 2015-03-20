package project.domain.players;

import java.util.List;
import project.domain.Action;
import project.domain.Card;
import project.domain.CardDeck;
import project.domain.strategies.DealerPlayStyle;

public class Dealer extends Participant {

	private final CardDeck deck;
	private final DealerPlayStyle playStyle;

	public Dealer(int numberOfDecks, DealerPlayStyle ps) {
		this.deck = new CardDeck(numberOfDecks);
		this.playStyle = ps;
	}

	public void collectCards(List<Player> players) {
		this.deck.addCards(this.emptyHand());
		players.stream().forEach(p -> this.deck.addCards(p.emptyHand()));
	}

	public void deal(List<Player> players) {
		players.stream().forEach(player -> player.addCard(this.deck.getNewCard()));
		this.hand.add(this.deck.getNewCard());
		players.stream().forEach(player -> player.addCard(this.deck.getNewCard()));
		this.hand.add(this.deck.getNewCard());
	}

	public Card deal() {
		return this.deck.getNewCard();
	}

	public Action play(List<Player> players) {
		Action action = this.playStyle.play(this, players);
		if(action == Action.HIT) {
			this.takeCard();
		}
		return action;
	}

	private void resetDeck() {
		this.deck.reset();
	}

	public Card showTopCard() {
		return this.hand.get(0);
	}

	public void takeCard() {
		this.addCard(this.deck.getNewCard());
	}
}
