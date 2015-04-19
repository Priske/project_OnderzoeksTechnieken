package project.domain;

import java.io.Serializable;
import javafx.beans.property.ReadOnlyDoubleProperty;
import project.domain.serializables.SerialDoubleProperty;

public class Bet implements Serializable {

	private static final long serialVersionUID = 1L;
	private final double initialValue;
	private final SerialDoubleProperty value;

	public Bet(double value) {
		this.initialValue = value;
		this.value = new SerialDoubleProperty(value);
	}

	public void add(double value) {
		this.value.set(this.value.add(value).get());
	}

	public void setBet(double value) {
		this.value.set(value);
	}

	public double getValue() {
		return this.value.get();
	}

	public void multiplyBet(double value) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public ReadOnlyDoubleProperty valueProperty() {
		return this.value;
	}
}
