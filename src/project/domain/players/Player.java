package project.domain.players;

import java.util.List;
import project.domain.Action;
import project.domain.card.Card;
import project.domain.statistics.StatisticsCollector;
import project.domain.statistics.Turn;
import project.domain.strategy.PlayerPlayStyle;

public class Player extends Participant {

	private StatisticsCollector collector;
	private final PlayerPlayStyle playStyle;

	public Player(String name, PlayerPlayStyle playStyle) {
		super(name);
		this.playStyle = playStyle;
	}

	public Player(String name, PlayerPlayStyle playStyle, List<Card> cards) {
		this(name, playStyle);
		this.hand.addAll(cards);
	}

	public PlayerPlayStyle getStrategy() {
		return this.playStyle;
	}

	public Action play(Dealer dealer) {
		Action action = this.playStyle.play(this, dealer);
		Turn turn;
		if(action == Action.HIT) {
			turn = new Turn(this, action, this.hand, this.requestCard(dealer));
		} else {
			turn = new Turn(this, action, this.hand);
		}
		if(this.collector != null) {
			this.collector.addTurn(turn);
		}
		return action;
	}

	private Card requestCard(Dealer dealer) {
		Card card = dealer.deal();
		this.addCard(card);
		return card;
	}
}
