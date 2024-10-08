package task3.implement;

import task3.abastract.MessageQueue;

public class EvMessageQueue extends MessageQueue {

	@Override
	public void setListener(Listener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(byte[] bytes, int offset, int length) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] receive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean closed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void send(byte[] msg) {
		// TODO Auto-generated method stub
		
	}
	
	public void send(MessageQueue queue, String[] messages) {
		for (int i=0;i<messages.length;i++) {
			byte[] msg = messages[i].getBytes();
			queue.send(msg);
		}
	}

	

}
