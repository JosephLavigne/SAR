package task3.abastract;

public abstract class EventPump extends Thread {
	public abstract void post(Runnable r);
	public abstract void run();
	public abstract void kill();
}
