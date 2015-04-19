package project.domain.players;

import java.util.List;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import project.domain.Action;
import project.domain.Bet;
import project.domain.card.Card;
import project.domain.cardcounters.CardCounter;
import project.domain.cardcounters.DefaultCardCounter;
import project.domain.exceptions.NotEnoughMoneyException;
import project.domain.serializables.SerialDoubleProperty;
import project.domain.serializables.SerialObjectProperty;
import project.domain.statistics.StatisticsCollector;
import project.domain.statistics.Turn;
import project.domain.strategy.player.PlayerPlayStyle;

public class Player extends Participant {

	private static final long serialVersionUID = 1L;
	private Bet bet;
	private final SerialObjectProperty<CardCounter> cardCounter;
	private final SerialDoubleProperty cardCounterValue = new SerialDoubleProperty(0);
	private transient final StatisticsCollector collector;
	private final SerialDoubleProperty money = new SerialDoubleProperty(1000);
	private final SerialObjectProperty<PlayerPlayStyle> strategy;

	public Player(String name, PlayerPlayStyle playStyle) {
		super(name);
		this.strategy = new SerialObjectProperty<>(playStyle);
		this.cardCounter = new SerialObjectProperty<>(new DefaultCardCounter());
		this.collector = new StatisticsCollector();
		this.bet = new Bet(1000);
	}

	public Player(String name, PlayerPlayStyle playStyle, List<Card> cards) {
		this(name, playStyle);
		this.hand.addAll(cards);
	}

	public void addBet(double value) {
		this.checkMoney(value);
		this.money.set(this.money.subtract(value).get());
		this.bet.add(value);
	}

	public void addCardCountValue(double value) {
		this.cardCounterValue.set(this.cardCounterValue.add(value).get());
	}

	public ReadOnlyDoubleProperty betValueProperty() {
		return this.bet.valueProperty();
	}

	public SimpleObjectProperty<CardCounter> cardCounterProperty() {
		return this.cardCounter;
	}

	public void checkMoney(double value) {
		if(value > this.money.get()) {
			throw new NotEnoughMoneyException();
		}
	}

	public void countCard(Card card) {
		this.cardCounter.get().countCard(this, card);
	}

	public Bet getBet() {
		return this.bet;
	}

	public double getCardCountValue() {
		return this.cardCounterValue.get();
	}

	public PlayerPlayStyle getStrategy() {
		return this.strategy.get();
	}

	public ReadOnlyDoubleProperty moneyProperty() {
		return this.money;
	}

	public void multiplyBet(double value) {
		double multiply = this.bet.getValue() * value;
		this.checkMoney(multiply);
		this.money.set(this.money.subtract(multiply).get());
		this.bet.multiplyBet(value);
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

	public void resetCardCounter() {
		this.cardCounterValue.set(0);
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
}
