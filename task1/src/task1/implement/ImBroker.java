package task1.implement;

import task1.abastract.Broker;
import task1.abastract.Channel;

public class ImBroker extends Broker{
	public ImBroker(String name) {
		super(name);
	}
	@Override
	public Channel accept(int port) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Channel connect(String name, int port) {
		// TODO Auto-generated method stub
		return null;
	}

}
