package project.domain;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Bet {

	private final ReadOnlyDoubleProperty initialValue;
	private final SimpleDoubleProperty value;

	public Bet(double value) {
		this.initialValue = new SimpleDoubleProperty(value);
		this.value = new SimpleDoubleProperty(value);
	}

	public void setBet(double value) {
		this.value.set(value);
	}

	public ReadOnlyDoubleProperty initialValueProperty() {
		return this.initialValue;
	}

	public ReadOnlyDoubleProperty valueProperty() {
		return this.value;
	}

	public void add(double value) {
		this.value.set(this.value.add(value).get());
	}

	public double getValue() {
		return this.value.get();
	}
}
