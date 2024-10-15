package task3.implement;

import java.util.concurrent.LinkedBlockingQueue;

import task3.abastract.EventPump;

public class ImEventPump extends EventPump {
	
	static ImEventPump self;
	static LinkedBlockingQueue<Runnable> queue;
	static boolean alive; // true = pump alive / false = pump is dead
	
	static {
		self = new ImEventPump();
		self.alive = true;
		self.start();
	}
	
	private ImEventPump() {
		this.queue = new LinkedBlockingQueue<Runnable>(); 
	}
	
	public synchronized static ImEventPump getinstance() {
		return ImEventPump.self;
	}

	@Override
	public synchronized void post(Runnable r) { // Car Post va etre appeler par le monde Threadé
		if(!this.alive) {
			return;
		}
		try {
			self.queue.put(r);
			notify();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public synchronized boolean remove(Runnable r) { // supprimer l'event dans la queue
		if(!this.alive) {
			return false;
		}
		return this.queue.remove(r);
	}
	

	@Override
	public synchronized void run() {
		Runnable r;
		while(alive) {
			r = queue.poll();
			while (r!=null) {
				r.run();
				r = queue.poll();
			}
			this.sleep();
			}
		
	}
	
	
	private void sleep() {
		try {
			wait();
		} catch (InterruptedException ex){
		// nothing to do here.
		}
	}

	@Override
	public void kill() {
		this.alive = false;
	}

}
