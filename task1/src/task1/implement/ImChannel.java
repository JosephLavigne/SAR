package task1.implement;

import task1.abastract.Channel;

public class ImChannel extends Channel {
	
	boolean disconnect;
	
	public ImChannel() {
		this.disconnect = false;
	}

	@Override
	public int read(byte[] bytes, int offset, int length) {
		while(length > 0) {
			if(this.disconnected()) {
				//TODO read circular Buffer
			}
		}
		return 0;
	}

	@Override
	public int write(byte[] bytes, int offset, int length) {
		while(length > 0) {
			if(this.disconnected()) {
				//TODO write circular Buffer
			}
		}
		return 0;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean disconnected() {
		return this.disconnect;
	}

}
