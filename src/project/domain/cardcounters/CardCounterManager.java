package project.domain.cardcounters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CardCounterManager {

	private final ObservableList<CardCounter> cardCounters = FXCollections.observableArrayList();

	public CardCounterManager() {
		this.cardCounters.add(new DefaultCardCounter());
		this.cardCounters.add(new HiLoCounter());
		this.cardCounters.add(new HiOpt1Counter());
		this.cardCounters.add(new HiOpt2Counter());
		this.cardCounters.add(new KOCounter());
		this.cardCounters.add(new Omega2Counter());
		this.cardCounters.add(new ZenCountCounter());
	}

	public ObservableList<CardCounter> getCardCounters() {
		return this.cardCounters;
	}
}
