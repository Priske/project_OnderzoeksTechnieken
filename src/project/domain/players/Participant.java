package project.domain.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.domain.card.Card;
import project.domain.card.CardFace;
import project.domain.cardcounters.GlobalCardCounter;
import project.domain.serializables.SerialIntegerProperty;
import project.domain.serializables.SerialStringProperty;

public abstract class Participant implements Serializable {

	private static int _id;
	private static final long serialVersionUID = 1L;
	protected final SerialIntegerProperty blackJack = new SerialIntegerProperty();
	protected final SerialIntegerProperty burned = new SerialIntegerProperty();
	protected final SerialIntegerProperty draw = new SerialIntegerProperty();
	protected transient final ObservableList<Card> hand = FXCollections.observableArrayList();
	protected final SerialIntegerProperty wins = new SerialIntegerProperty();
	private final int id;
	private final SerialStringProperty name;

	public Participant(String name) {
		this.name = new SerialStringProperty(name);
		this.id = _id++;
	}

	public void addCard(Card card) {
		GlobalCardCounter.getInstance().usedCard(card);
		this.hand.add(card);
	}

	public synchronized void blackJack() {
		this.blackJack.set(this.blackJack.get() + 1);
	}

	public ReadOnlyIntegerProperty blackJackProperty() {
		return this.blackJack;
	}

	public synchronized void burned() {
		this.burned.set(this.burned.get() + 1);
	}

	public ReadOnlyIntegerProperty burnedProperty() {
		return this.burned;
	}

	public long countAces() {
		return this.hand.stream().filter(c -> c.getFace() == CardFace.ACE).count();
	}

	public synchronized void draw() {
		this.draw.set(this.draw.get() + 1);
	}

	public ReadOnlyIntegerProperty drawProperty() {
		return this.draw;
	}

	public final List<Card> emptyHand() {
		List<Card> cards = new ArrayList<>(this.hand);
		this.hand.clear();
		return cards;
	}

	public boolean equalsId(int id) {
		return this.id == id;
	}

	public int getBurned() {
		return this.burned.get();
	}

	public ObservableList<Card> getHand() {
		return this.hand;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name.get();
	}

	public int getScore() {
		return Card.getScore(this.hand);
	}

	public int getWins() {
		return this.wins.get();
	}

	public SimpleStringProperty nameProperty() {
		return this.name;
	}

	public void reset() {
		this.burned.set(0);
		this.wins.set(0);
		this.draw.set(0);
		this.blackJack.set(0);
	}

	@Override
	public String toString() {
		return this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
	}

	public ReadOnlyIntegerProperty winsProperty() {
		return this.wins;
	}

	public synchronized void won() {
		this.wins.set(this.wins.get() + 1);
	}
}
