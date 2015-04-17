package project.domain;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bet {

	private final ReadOnlyIntegerProperty initialValue;
	private final SimpleIntegerProperty value;

	public Bet(int value) {
		this.initialValue = new SimpleIntegerProperty(value);
		this.value = new SimpleIntegerProperty(value);
	}

	public void add(int value) {
		this.value.set(this.value.add(value).get());
	}

	public ReadOnlyIntegerProperty valueProperty() {
		return this.value;
	}

	public ReadOnlyIntegerProperty initialValueProperty() {
		return this.initialValue;
	}
}
