package task1.implement;

import task1.abastract.Broker;
import task1.abastract.Task;

public class ImTask extends Task {
	
	public ImTask(Broker b, Runnable r) {
		super(b,r);
	}

	@Override
	public Broker getBroker() {
		return super.broker;
	}

}
