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
		//TODO
		
		
		
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
	
	
	
	static boolean Test2() { // le client envoie un message le serveur doit renvoyé ce message
		Broker serv = new ImBroker("Toto");
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		    	read()??
		    }
		};
		Task client = new ImTask(serv, runnable);
	}

	static boolean Test3() {
		Broker serv = new ImBroker("Server");
		Channel client = serv.connect("Client", 1000);
		
	    byte[] Wbuffer = new byte[20];
	    for (int i = 0; i < 20; i++) {
	    	Wbuffer[i] = (byte) (i + 1);
	    }
	    
	    int nb_Bytes = client.write(Wbuffer, 0, 20);
	    if (nb_Bytes != 20) {
	    	System.out.println("Test n°3 échec car seulement une lecture de " + nb_Bytes);
	    	return false;
	    }
	    System.out.println("Test n°3 validé");
	    return true;
	}


}
