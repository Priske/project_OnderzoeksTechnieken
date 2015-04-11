package project.domain.players;

import java.util.List;
import project.domain.Action;
import project.domain.card.Card;
import project.domain.statistics.StatisticsCollector;
import project.domain.statistics.Turn;
import project.domain.strategy.PlayerPlayStyle;

public class Player extends Participant {

	private static int _id;
	private StatisticsCollector collector;
	private final int id;
	private PlayerPlayStyle strategy;

	public Player(String name, PlayerPlayStyle playStyle) {
		super(name);
		this.id = _id++;
		this.strategy = playStyle;
	}

	public Player(String name, PlayerPlayStyle playStyle, List<Card> cards) {
		this(name, playStyle);
		this.hand.addAll(cards);
	}

	public boolean equalsId(int id) {
		return this.id == id;
	}

	public int getId() {
		return this.id;
	}

	public PlayerPlayStyle getStrategy() {
		System.out.println("strategy get " + this.strategy);
		return this.strategy;
	}

	public void setStrategy(PlayerPlayStyle strategy) {
		if(strategy == null) {
			throw new IllegalArgumentException("Strategy cannot be null.");
		}
		this.strategy = strategy;
	}

	public Action play(Dealer dealer) {
		if(dealer == null) {
			throw new IllegalArgumentException("Dealer cannnot be null.");
		}
		Action action = this.strategy.play(this, dealer);
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
		if(dealer == null) {
			throw new IllegalArgumentException("Dealer cannnot be null.");
		}
		Card card = dealer.deal();
		this.addCard(card);
		return card;
	}
}
