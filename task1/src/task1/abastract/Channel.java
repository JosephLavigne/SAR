package task1.abastract;

import task1.implement.DisconnectedException;
import task1.implement.RDV;

public abstract class Channel {
	public Broker b;
	public Channel(Broker b) {
		this.b = b;
	}
	public abstract int read(byte[] bytes, int offset, int length) throws DisconnectedException;
	public abstract int write(byte[] bytes, int offset, int length) throws DisconnectedException;
	public abstract void disconnect();
	public abstract boolean disconnected();
}
