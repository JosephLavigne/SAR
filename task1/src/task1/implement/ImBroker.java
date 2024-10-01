package task1.implement;

import java.util.HashMap;

import task1.abastract.Broker;
import task1.abastract.Channel;

public class ImBroker extends Broker{
	BrokerManager bm;
	HashMap<Integer, RDV> portRDV;
	
	public ImBroker(String name) {
		super(name);
		portRDV = new HashMap<Integer, RDV>(); // Integer : port
		bm = BrokerManager.getSelf();
		bm.addBroker(this);
	}
	
	@Override
	public Channel accept(int port)  {
		RDV rdv = null;
		synchronized(portRDV) {
			rdv = this.portRDV.get(port);
			if(rdv != null) {
				throw new IllegalStateException("Impossible d'accepter sur Le port : "+port);
			}
			rdv = new RDV();
			portRDV.put(port, rdv);
			portRDV.notifyAll();
		}
		Channel ch = null;
		try {
			ch = rdv.accept(this, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ch;
	}
	
	@Override
	public Channel connect(String name, int port){
		ImBroker b = (ImBroker) bm.getBroker(name);
		if(b == null) {
			try {
				throw new Exception("Illegal State : Broker doesn't exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.connect(b, port);
	}
	
	private Channel connect(Broker b, int port) {
		RDV rdv = null;
		synchronized(portRDV) {
			rdv = portRDV.get(port);
			while(rdv == null) {
				try {
					portRDV.wait();
				} catch (InterruptedException ex) {
					//nothing
				}
				rdv = portRDV.get(port);
			}
			portRDV.remove(port);
		}
		Channel ch = null;
		try {
			ch = rdv.connect(b, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ch;
	}
	
	
	public String getname() {
		return this.name;
	}
}
		
	
