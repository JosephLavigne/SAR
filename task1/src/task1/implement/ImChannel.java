package task1.implement;

import task1.abastract.Channel;

public class ImChannel extends Channel {
	
	public ImChannel() {
		super();
	}

	@Override
	public int read(byte[] bytes, int offset, int length) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int write(byte[] bytes, int offset, int length) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean disconnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
