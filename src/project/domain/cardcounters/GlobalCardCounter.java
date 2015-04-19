package project.domain.cardcounters;

import project.domain.GameManager;
import project.domain.card.Card;

public class GlobalCardCounter {

	private static final GlobalCardCounter instance = new GlobalCardCounter();
	private GameManager mgr;

	public void setParticipantManager(GameManager mgr) {
		this.mgr = mgr;
	}

	public static GlobalCardCounter getInstance() {
		return instance;
	}

	public void usedCard(Card card) {
		this.mgr.countCard(card);
	}
}
