package task1.implement;

import java.util.HashMap;

import task1.abastract.Broker;

public class BrokerManager {
	static BrokerManager self;
	static HashMap<String, Broker> matrice;
	
	
	static BrokerManager getSelf() {
		return BrokerManager.self;
	}
	
	static void addBroker(Broker b, String name) {
		BrokerManager.matrice.put(name, b);
	}
	
	static Broker getBroker(String name) {
		return BrokerManager.matrice.get(name);
	}
}
