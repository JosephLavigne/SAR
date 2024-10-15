package task3.implement;

import java.util.HashMap;

import task1.abastract.Broker;
import task1.implement.BrokerManager;
import task1.implement.ImBroker;
import task1.implement.ImChannel;
import task2.implement.ImMessageQueue;
import task3.abastract.MessageQueue;
import task3.abastract.NewTask;
import task3.abastract.QueueBroker;

public class EvQueueBroker extends QueueBroker {
	
	Broker b;
	private HashMap<Integer, Thread> acceptport;

	EvQueueBroker(String name) {
		super(name);
		b = new ImBroker(name);
		this.acceptport = new HashMap<Integer, Thread>();
		
		
	}

	@Override
	public synchronized boolean bind(int port, AcceptListener listener) {
		if(!this.acceptport.containsKey(port)) {
			Thread t = new Thread(()-> {
				while(true) {
					ImChannel c = (ImChannel) this.b.accept(port);
					ImMessageQueue q = new ImMessageQueue(c);
					ImNewTask task = new ImNewTask();
					task.post(()-> listener.accepted(q));
				}
			});
			acceptport.put(port, t);
			t.start();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public synchronized boolean unbind(int port) {
		if(this.acceptport.containsKey(port)) {
			Thread t = acceptport.get(port);
			t.interrupt();
			acceptport.remove(port);
			return true;
			
		}
		return false;
	}

	@Override
	public boolean connect(String name, int port, ConnectListener listener) {
		BrokerManager bm = BrokerManager.self;
		Broker b = bm.getBroker(name);
		if(b != null) {
			Thread t = new Thread(() -> {
				ImChannel c = (ImChannel) b.connect(name, port);
				ImMessageQueue q = new ImMessageQueue(c);
				ImNewTask task = new ImNewTask();
				task.post(()-> listener.connected(q));
			});
			t.start();
			return true;
		}
		return false;
	}
	
	
	public String name() {
		return this.b.getname();
	}
	
	public class ConnecteListener implements ConnectListener{


		@Override
		public void refused() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void connected(ImMessageQueue q) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class AccepteListener implements AcceptListener{

		@Override
		public void accepted(ImMessageQueue q) {
			// TODO Auto-generated method stub
			
		}
		
	}


}
