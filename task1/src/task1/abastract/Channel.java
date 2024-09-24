package task1.abastract;

import task1.implement.RDV;

public abstract class Channel {
	public RDV rdv;
	public Channel(RDV rdv) {
		this.rdv = rdv;
	}
	public abstract int read(byte[] bytes, int offset, int length);
	public abstract int write(byte[] bytes, int offset, int length);
	public abstract void disconnect();
	public abstract boolean disconnected();
}
