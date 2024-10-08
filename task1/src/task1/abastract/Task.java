package task1.abastract;

import task2.abstarct.QueueBroker;

public abstract class Task extends Thread {
	
	public Broker broker;
	public QueueBroker qbroker;
	Runnable runnable;
	
	public Task(Broker b, Runnable r) {
		this.broker = b;
		this.runnable = r;
	}
	public Task(QueueBroker b, Runnable r) {
		this.qbroker = b;
		this.runnable = r;
	}

	public abstract Broker getBroker();
	public abstract QueueBroker getQueueBroker();
}
