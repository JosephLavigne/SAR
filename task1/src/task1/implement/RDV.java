package task1.implement;

import task1.abastract.Broker;
import task1.abastract.Channel;

public class RDV { // RDV de deux thread (à utiliser seulement pour les RDV des brokers)	
	Broker ba; // broker accept
	Broker bc; // broker connect
	
	int nexpected; 
	boolean attbc; // attente de bc true / attente de ba false
	
	Channel ca; // Channel broker accept
	Channel cc; // Channel broker connect
	
	CircularBuffer c1; // ca write et cc read
	CircularBuffer c2; // ca read et cc write
	
	static int capacity = 50; // Paramètre settings
	
	public RDV(Broker bc, Broker ba) { 
		this.nexpected = 1;
		this.ba = ba;
		this.bc = bc;
		if (bc != null) {
			attbc = false;
		}
	}
	
	public synchronized void come() {
		this.nexpected--;
		while(nexpected>0) {
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

}
