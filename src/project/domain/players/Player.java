package project.domain.players;

import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
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
	private final int DEFAULT_MONEY = 10000;
	private Bet bet;
	private final SerialObjectProperty<CardCounter> cardCounter;
	private final SerialDoubleProperty cardCounterValue = new SerialDoubleProperty(0);
	private transient final StatisticsCollector collector;
	private final SerialDoubleProperty money = new SerialDoubleProperty(this.DEFAULT_MONEY);
	private final SerialObjectProperty<PlayerPlayStyle> strategy;

	public Player(String name, PlayerPlayStyle playStyle) {
		super(name);
		this.strategy = new SerialObjectProperty<>(playStyle);
		this.cardCounter = new SerialObjectProperty<>(new DefaultCardCounter());
		this.collector = new StatisticsCollector();
		this.bet = this.makeBet();
	}

	public Player(String name, PlayerPlayStyle playStyle, List<Card> cards) {
		this(name, playStyle);
		this.hand.addAll(cards);
	}

	public void addBet(double value) {
		this.checkMoney(this.bet.getValue() + value);
		this.bet.add(value);
	}

	public void addCardCountValue(double value) {
		this.cardCounterValue.set(this.cardCounterValue.get() + value);
	}

	public ReadOnlyDoubleProperty betValueProperty() {
		return this.bet.valueProperty();
	}

	@Override
	public void blackJack() {
		super.blackJack();
		this.processBet(this.bet.getValue() * 1.5);
	}

	@Override
	public void burned() {
		super.burned();
		this.processBet(-this.bet.getValue());
	}

	public ReadOnlyObjectProperty<CardCounter> cardCounterProperty() {
		return this.cardCounter;
	}

	public ReadOnlyDoubleProperty cardCounterValueProperty() {
		return this.cardCounterValue;
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

	@Override
	public void loss() {
		super.loss();
		this.processBet(-this.bet.getValue());
	}

	public DoubleProperty moneyProperty() {
		return this.money;
	}

	public void multiplyBet(double value) {
		this.checkMoney(this.bet.getValue() * value);
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

	@Override
	public void reset() {
		super.reset();
		this.money.set(this.DEFAULT_MONEY);
	}

	public void resetCardCounter() {
		this.cardCounterValue.set(0);
	}

	public SimpleObjectProperty strategyProperty() {
		return this.strategy;
	}

	@Override
	public void won() {
		super.won();
		this.processBet(this.bet.getValue());
	}

	private Bet makeBet() {
		return this.makeBet(10);
	}

	private Bet makeBet(double value) {
		this.checkMoney(value);
		return new Bet(value);
	}

	private void processBet(double value) {
		this.money.set(this.money.get() + value);
		this.bet.reset();
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
