package task1.test;


import task1.abastract.Broker;
import task1.abastract.Channel;
import task1.abastract.Task;
import task1.implement.ImBroker;
import task1.implement.ImTask;

public class Maintest {
	static boolean testbool = true;

	public static void main(String[] args) {
		System.out.println("Lancement de la phase de Test...");
		
		testbool = Test1();
		if(testbool) {
			testbool = Test2();
		}
		if(testbool) {
			testbool = Test3();
		}
		if(testbool) {
			testbool = Test4();
		}
		
		if(testbool) {
			System.out.println("Tous les Tests ont réussi");
		}
		else {
			System.out.println("Au moins un Tests a échoué");
		}

	}
	
	
	static boolean Test1() { // simple connection/deconnection d'un client vers le serveur
		Broker serv = new ImBroker("Toto");
		Channel client = serv.connect("Client", 1000);
		
		if(client.disconnected()) {
			System.out.println("Test n°1 échec");
			return false;
		}
		
		client.disconnect();
		
		if(client.disconnected()) {
			System.out.println("Test n°1 validé");
			return true;
		}
		else {
			System.out.println("Test n°1 échec");
			return false;
		}
	}

	static boolean Test2() {
		Broker serv = new ImBroker("Server");
		Channel client = serv.connect("Client", 1000);
		
	    byte[] Wbuffer = new byte[20];
	    for (int i = 0; i < 20; i++) {
	    	Wbuffer[i] = (byte) (i + 1);
	    }
	    
	    int nb_Bytes = client.read(Wbuffer, 0, 20);
	    if (nb_Bytes != 20) {
	    	System.out.println("Test n°2 échec car seulement une lecture de " + nb_Bytes);
	    	return false;
	    }
	    System.out.println("Test n°2 validé");
	    return true;
	}
	
	static boolean Test3() { // test fonctionne getBroker (class Task)
		Broker serv = new ImBroker("Server");
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		    }
		};
		Task client = new ImTask(serv, runnable);
        Broker broker = client.getBroker();
        
        if(broker == null) {
        	System.out.println("Test n°3 échec : getBroker null");
        	return false;
        }
        else if(serv != broker) {
        	System.out.println("Test n°3 échec : getBroker non identique");
        	return false;
        }
        else {
        	System.out.println("Test n°3 validé");
        	return true;
        }
	}
	
	static boolean Test4() { // Test de multi Threading du buffer
		Broker serv = new ImBroker("Toto");
		byte[] Wbuffer = new byte[20];
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
			    for (int i = 0; i < 20; i++) {
			    	Wbuffer[i] = (byte) (i + 1);
			    }
		    }
		};
		Task client1 = new ImTask(serv, runnable);
		Task client2 = new ImTask(serv, runnable);
		
		client1.start();
        client2.start();
	    for (int i = 0; i < 20; i++) {
	    	if(Wbuffer[i] != i+1) {
	    		System.out.println("Test n°4 échec");
	    		return false;
	    	}
	    }
	    System.out.println("Test n°4 validé");
	    return true;
        
	}

}
