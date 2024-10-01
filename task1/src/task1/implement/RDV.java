package task1.implement;

import task1.abastract.Broker;
import task1.abastract.Channel;

public class RDV { // RDV de deux thread (à utiliser seulement pour les RDV des brokers)	
	Broker ba; // broker accept
	Broker bc; // broker connect
	
	int nexpected; 
	boolean attbc; // attente de bc true / attente de ba false
	
	ImChannel ca; // Channel broker accept
	ImChannel cc; // Channel broker connect
	
	CircularBuffer c1; // ca write et cc read
	CircularBuffer c2; // ca read et cc write
	
	static int capacity = 50; // Paramètre settings
	
	public RDV(Broker bc, Broker ba) { 
		this.nexpected = 2;
		this.ba = ba;
		this.bc = bc;
		if (bc != null) {
			attbc = false;
		}
	}
	
	public RDV() {
		
	}
	
	
	private void _wait() {
		while(ca==null || cc == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized Channel connect(Broker bc, int port) {
		this.bc = bc;
		cc = new ImChannel(bc, port);
		if(ca != null) {
			ca.connect(cc, bc.getname());
			notify();
		}
		else {
			_wait();
		}
		return cc;
	}
	
	synchronized Channel accept(Broker ba, int port) {
		this.ba = ba;
		ca = new ImChannel(ba, port);
		if(cc != null) {
			ca.connect(cc, ba.getname());
			notify();
		}
		else {
			_wait();
		}
		return ca;
	}

	public Channel accept(ImBroker ab, int port) {
		return accept((Broker)ab, port);
	}
	
	/*
	public RDV() {
		this.nexpected = 2;
	}
	
	public synchronized void come() {
		this.nexpected--;
		while(nexpected-1>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll(); // les deux Brokers sont arrivés au RDV
		channelcreation();
	}
	
	public synchronized Channel comeconnect() throws Exception {
		if(this.attbc == false) {
			throw new Exception("Illegal State : Waiting for accept not connect");
		}
		come();
		return this.cc;
	}
	
	public synchronized Channel comeaccept() throws Exception {
		if(this.attbc == true) {
			throw new Exception("Illegal State : Waiting for connect not accept");
		}
		come();
		return this.ca;
	}
	
	private synchronized void channelcreation() {
		c1 = new CircularBuffer(capacity);
		c2 = new CircularBuffer(capacity);
		ca = new ImChannel(this, c1, c2);
		cc = new ImChannel(this, c1, c2);
		
	}
	*/

}
