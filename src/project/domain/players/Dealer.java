package project.domain.players;

import java.util.List;
import project.domain.Action;
import project.domain.CardDeck;
import project.domain.card.Card;
import project.domain.strategy.DealerPlayStyle;
import project.domain.strategy.dealer.DealerPlayStyleDefault;

public class Dealer extends Participant {

	private final CardDeck deck;
	private final DealerPlayStyle playStyle;

	public Dealer(int numberOfDecks, DealerPlayStyleDefault ps) {
		super("Dealer");
		this.deck = new CardDeck(numberOfDecks);
		this.playStyle = ps;
	}

	public void collectCards(List<Player> players) {
		this.deck.addCards(this.emptyHand());
		players.stream().forEach(p -> this.deck.addCards(p.emptyHand()));
		this.deck.addCards(this.emptyHand());
	}

	public Card deal() {
		return this.deck.getNewCard();
	}

	public DealerPlayStyle getStrategy() {
		return this.playStyle;
	}

	public Action play(List<Player> players) {
		Action action = this.playStyle.play(this, players);
		if(action == Action.HIT) {
			this.takeCard();
		}
		return action;
	}

	public Card showTopCard() {
		return this.hand.get(0);
	}

	public void takeCard() {
		this.addCard(this.deck.getNewCard());
	}

	private void resetDeck() {
		this.deck.reset();
	}
}
