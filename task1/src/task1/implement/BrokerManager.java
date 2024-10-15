package task1.implement;

import java.awt.geom.IllegalPathStateException;
import java.util.HashMap;

import task1.abastract.Broker;

public class BrokerManager {
	public static BrokerManager self;
	HashMap<String, Broker> brokers;
	
	private BrokerManager() {
		brokers = new HashMap<String, Broker>();
	}
	
	static {
		self = new BrokerManager();
	}
	
	static BrokerManager getSelf() {
		return BrokerManager.self;
	}
	
	synchronized void addBroker(Broker b) {
		if(b == null) {
			throw new IllegalPathStateException("Le broker donné ne peut pas être null");
		}
		String name = b.name;
		Broker bro = self.brokers.get(name); // verifier que le broker n'est pad déjà dans la HashMap
		if(bro != null) {
			throw new IllegalPathStateException("Le Broker est déjà défini dans l'environnement");
		}
		self.brokers.put(name, b);
	}
	
	public synchronized Broker getBroker(String name) {
		return self.brokers.get(name);
	}
	
	public synchronized void remove(Broker broker) {
		String name = broker.name;
		self.brokers.remove(name);
	}
}
