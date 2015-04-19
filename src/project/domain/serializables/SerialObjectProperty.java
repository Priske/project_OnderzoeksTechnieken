package project.domain.serializables;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.SimpleObjectProperty;

public class SerialObjectProperty<T> extends SimpleObjectProperty<T> implements Externalizable {

	private static final long serialVersionUID = 1L;

	public SerialObjectProperty() {
	}

	public SerialObjectProperty(Object bean, String name) {
		super(bean, name);
	}

	public SerialObjectProperty(Object bean, String name, T initialValue) {
		super(bean, name, initialValue);
	}

	public SerialObjectProperty(T initialValue) {
		super(initialValue);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.set((T)in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.get());
	}
}
