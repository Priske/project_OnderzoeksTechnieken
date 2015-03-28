package project.domain.statistics;

import java.util.ArrayList;
import java.util.List;
import project.domain.Action;
import project.domain.Card;
import project.domain.players.Player;

public class Turn {

	private final Action action;
	private final List<Card> cards = new ArrayList<>();
	private final Card lastCard;
	private final Player player;

	public Turn(Player player, Action action, List<Card> cards) {
		this(player, action, cards, null);
	}

	public Turn(Player player, Action action, List<Card> cards, Card lastCard) {
		this.player = player;
		this.action = action;
		this.cards.addAll(cards);
		this.lastCard = lastCard;
	}

	public Action getAction() {
		return this.action;
	}

	public List<Card> getHand() {
		return this.cards;
	}

	public Card getLastCard() {
		return this.lastCard;
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getScore() {
		return Card.getScore(this.cards);
	}

	@Override
	public String toString() {
		return "Turn: " + this.action + "\n\tLast card: " + this.lastCard + "\n\tHand: " + this.cards;
	}
}
