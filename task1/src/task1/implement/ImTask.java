package task1.implement;

import task1.abastract.Broker;
import task1.abastract.Task;
import task2.abstarct.QueueBroker;

public class ImTask extends Task {
	
	public ImTask(Broker b, Runnable r) {
		super(b,r);
	}
	
	public ImTask(QueueBroker b, Runnable r) {
		super(b,r);
	}

	@Override
	public Broker getBroker() {
		Thread t = Thread.currentThread();
		Task task = (Task) t;
		return task.broker;
	}

	@Override
	public QueueBroker getQueueBroker() {
		return this.qbroker;
	}

}
