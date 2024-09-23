package task1.implement;

import task1.abastract.Broker;

public class RDV { // RDV de deux thread (à utiliser seulement pour les RDV des brokers)	
	Broker b1;
	Broker b2;
	int nexpected;
	
	public RDV(Broker b1) {
		this.nexpected = 2;
		this.b1 = b1;
		this.b2 = null;
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
	}

}
