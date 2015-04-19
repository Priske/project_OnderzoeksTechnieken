package project.domain.serializables;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.SimpleIntegerProperty;

public class SerialIntegerProperty extends SimpleIntegerProperty implements Externalizable {

	private static final long serialVersionUID = 1L;

	public SerialIntegerProperty() {
	}

	public SerialIntegerProperty(int initialValue) {
		super(initialValue);
	}

	public SerialIntegerProperty(Object bean, String name) {
		super(bean, name);
	}

	public SerialIntegerProperty(Object bean, String name, int initialValue) {
		super(bean, name, initialValue);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.set(in.readInt());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(this.get());
	}
}
