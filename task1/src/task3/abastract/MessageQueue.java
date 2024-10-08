package task3.abastract;

public abstract class MessageQueue {
	public interface Listener{
		abstract void receive(byte[] msg);
		void closed();
	}
	public abstract void setListener(Listener l);
	public abstract void send(byte[] bytes, int offset, int length);
	public abstract byte[] receive();
	public abstract void close();
	public abstract boolean closed();
	public abstract void send(byte[] msg);

}
