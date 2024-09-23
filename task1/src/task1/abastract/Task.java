package task1.abastract;

public abstract class Task extends Thread {
	
	protected Broker broker;
	Runnable runnable;
	
	public Task(Broker b, Runnable r) {
		this.broker = b;
		this.runnable = r;
	}

	public abstract Broker getBroker();
}
