package task2.abstarct;

import task1.abastract.Broker;

public abstract class QueueBroker {
	public Broker broker;
	protected QueueBroker(Broker broker) {
		this.broker = broker;
	}
	public abstract String name();
	public abstract MessageQueue accept(int port);
	public abstract MessageQueue connect(String name, int port);
}
