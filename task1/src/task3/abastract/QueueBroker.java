package task3.abastract;

import task2.implement.ImMessageQueue;

public abstract class QueueBroker {
	String name;
	protected QueueBroker(String name) {
		this.name = name;
	}
	public interface AcceptListener {
			public void accepted(ImMessageQueue q);
	}
	public abstract boolean bind(int port, AcceptListener listener);
	public abstract boolean unbind(int port);
	public interface ConnectListener {
		public void connected(ImMessageQueue q);
		public void refused();
	}
	public abstract boolean connect(String name, int port, ConnectListener listener);
}
