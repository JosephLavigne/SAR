package task1.implement;

import java.util.HashMap;

import task1.abastract.Broker;
import task1.abastract.Channel;

public class ImBroker extends Broker{
	HashMap<Integer, RDV> portRDV;
	
	public ImBroker(String name) {
		super(name);
		portRDV = new HashMap<Integer, RDV>(); // Integer : port
	}
	
	@Override
	public Channel accept(int port) {
		RDV rdv = this.portRDV.get(port);
		if(rdv != null){
			try {
				return rdv.comeaccept();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			rdv = new RDV(this, null);
			this.portRDV.put(port, rdv);
			try {
				return rdv.comeaccept();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Impossible d'accpet sur Le port : "+port+" du broker "+this.name);
		return null;
	}
	
	@Override
	public Channel connect(String name, int port){
		Broker b = BrokerManager.getBroker(name); // pourrait-etre mis en une ligne mais c'est plus clair
		if(b == null) {
			try {
				throw new Exception("Illegal State : Broker doesn't exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.connect(b, port);
	}
	
	private Channel connect(Broker b, int port) { // EN COURS
		synchronized(b) {
			if(this.portRDV.containsKey(port)) { // le broker courrant ne doit pas avoir le port demandé déjà utilisé
				try {
					throw new Exception("Le port : "+port+" déjà utilisé pour le broker "+ this.name); // ERREUR
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			else if(b.portRDV.containsKey(port)){
				RDV rdv = b.portRDV.get(port);
				if(rdv.nexpected == 0 || rdv.attbc == false) {
					System.out.println("le Broker "+b.name+" n'est pas en attente de connexion sur le port : "+port);
				}
				System.out.println("Le port : "+port+" déjà utilisé pour le broker "+ b.name); // Il y a peut-etre un accept en attente
				return null;
			}
			else {
				RDV rdv = new RDV(this, b);
				b.portRDV.put(port, rdv);
				try {
					return rdv.comeconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}	
				}
			return null;
			}
		}
	}
		
	
