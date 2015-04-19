package project.domain.serializables;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.SimpleStringProperty;

public class SerialStringProperty extends SimpleStringProperty implements Externalizable {

	private static final long serialVersionUID = 1L;

	public SerialStringProperty() {
	}

	public SerialStringProperty(String initialValue) {
		super(initialValue);
	}

	public SerialStringProperty(Object bean, String name) {
		super(bean, name);
	}

	public SerialStringProperty(Object bean, String name, String initialValue) {
		super(bean, name, initialValue);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.set(in.readUTF());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(this.get());
	}
}
