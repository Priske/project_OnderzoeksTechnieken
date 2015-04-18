package project.domain.players;

import java.util.List;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import project.domain.Action;
import project.domain.Bet;
import project.domain.card.Card;
import project.domain.exceptions.NotEnoughMoneyException;
import project.domain.statistics.StatisticsCollector;
import project.domain.statistics.Turn;
import project.domain.strategy.player.PlayerPlayStyle;

public class Player extends Participant {

	private Bet bet;
	private final StatisticsCollector collector;
	private final SimpleDoubleProperty money = new SimpleDoubleProperty(1000);
	private final SimpleObjectProperty<PlayerPlayStyle> strategy;

	public Player(String name, PlayerPlayStyle playStyle) {
		super(name);
		this.strategy = new SimpleObjectProperty<>(playStyle);
		this.collector = new StatisticsCollector();
		this.bet = new Bet(1000);
	}

	public Player(String name, PlayerPlayStyle playStyle, List<Card> cards) {
		this(name, playStyle);
		this.hand.addAll(cards);
	}

	public ReadOnlyDoubleProperty betValueProperty() {
		return this.bet.valueProperty();
	}

	public Bet getBet() {
		return this.bet;
	}

	public PlayerPlayStyle getStrategy() {
		return this.strategy.get();
	}

	public void setStrategy(PlayerPlayStyle strategy) {
		if(strategy == null) {
			throw new IllegalArgumentException("Strategy cannot be null.");
		}
		this.strategy.set(strategy);
	}

	public ReadOnlyDoubleProperty moneyProperty() {
		return this.money;
	}

	public Action play(Dealer dealer) {
		if(dealer == null) {
			throw new IllegalArgumentException("Dealer cannnot be null.");
		}
		Action action = this.strategy.get().play(this, dealer);
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

	public SimpleObjectProperty strategyProperty() {
		return this.strategy;
	}

	private Bet makeBet() {
		return this.makeBet(10);
	}

	private Bet makeBet(double value) {
		this.checkMoney(value);
		this.money.set(this.money.subtract(value).get());
		return new Bet(value);
	}

	private Card requestCard(Dealer dealer) {
		if(dealer == null) {
			throw new IllegalArgumentException("Dealer cannnot be null.");
		}
		Card card = dealer.deal();
		this.addCard(card);
		return card;
	}

	public void addBet(double value) {
		this.checkMoney(value);
		this.money.set(this.money.subtract(value).get());
		this.bet.add(value);
	}

	public void multiplyBet(double value) {
		double multiply = this.bet.getValue() * value;
		this.checkMoney(multiply);
		this.money.set(this.money.subtract(multiply).get());
	}

	public void checkMoney(double value) {
		if(value > this.money.get()) {
			throw new NotEnoughMoneyException();
		}
	}
}
