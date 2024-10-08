package task3.implement;

import task1.abastract.Broker;
import task2.abstarct.MessageQueue;
import task2.abstarct.QueueBroker;

public class EvQueueBroker extends QueueBroker {

	protected EvQueueBroker(Broker broker) {
		super(broker);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageQueue accept(int port) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageQueue connect(String name, int port) {
		// TODO Auto-generated method stub
		return null;
	}

}
