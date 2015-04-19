package project.domain.serializables;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.SimpleDoubleProperty;

public class SerialDoubleProperty extends SimpleDoubleProperty implements Externalizable {

	private static final long serialVersionUID = 1L;

	public SerialDoubleProperty(double initialValue) {
		super(initialValue);
	}

	public SerialDoubleProperty(Object bean, String name, double initialValue) {
		super(bean, name, initialValue);
	}

	public SerialDoubleProperty(Object bean, String name) {
		super(bean, name);
	}

	public SerialDoubleProperty() {
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.set(in.readDouble());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeDouble(this.get());
	}
}
