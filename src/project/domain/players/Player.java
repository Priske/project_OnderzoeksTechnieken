package project.domain.players;

import java.util.Collections;
import java.util.List;
import project.domain.Action;
import project.domain.Card;
import project.domain.statistics.StatisticsCollector;
import project.domain.statistics.Turn;
import project.domain.strategies.PlayStyle;

/**

 @author Ben
 */
public class Player extends Participant {

	private final PlayStyle playStyle;
	private StatisticsCollector collector;

	public Player(String name, PlayStyle playStyle) {
		super(name);
		this.playStyle = playStyle;
	}

	public Player(String name, PlayStyle playStyle, List<Card> cards) {
		this(name, playStyle);
		this.hand.addAll(cards);
	}

	public List<Card> getHand() {
		return Collections.unmodifiableList(this.hand);
	}

	public Action play(Dealer dealer) {
		Action action = this.playStyle.play(this, dealer);
		Turn turn;
		if(action == Action.HIT) {
			turn = new Turn(this, action, hand, this.requestCard(dealer));
		} else {
			turn = new Turn(this, action, hand);
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
