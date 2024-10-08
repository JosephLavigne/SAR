package task2.implement;

import task1.abastract.Broker;
import task1.abastract.Channel;
import task2.abstarct.MessageQueue;
import task2.abstarct.QueueBroker;

public class ImQueueBroker extends QueueBroker {
	

	ImQueueBroker(Broker broker) {
		super(broker);
	}

	@Override
	public String name() {
		return ((Broker) this.broker).getname();
	}

	@Override
	public MessageQueue accept(int port) {
		Channel c = this.broker.accept(port);
		return new ImMessageQueue(c);
	}

	@Override
	public MessageQueue connect(String name, int port) {
		Channel c = this.broker.connect(name, port);
		return new ImMessageQueue(c);
	}

}
