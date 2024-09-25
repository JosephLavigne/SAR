package task1.implement;

import task1.abastract.Broker;
import task1.abastract.Channel;

public class ImChannel extends Channel {
	
	int port;
	boolean dangling;
	boolean disconnect;
	CircularBuffer in; // read
	CircularBuffer out; // write
	String rname;
	ImChannel rch; // remote channel
	
	public ImChannel(Broker b,CircularBuffer read, CircularBuffer write) {
		super(b);
		this.disconnect = false;
		this.in = read;
		this.out = write;
	}
	
	protected ImChannel(Broker b, int port) {
		super(b);
		this.port = port;
		this.in = new CircularBuffer(64);
	}
	
	void connect(ImChannel rch, String name) {
		this.rch = rch;
		rch.rch = this;
		this.out = rch.in;
		rch.out = this.in;
		rname = name;
		
	}

	@Override
	public int read(byte[] bytes, int offset, int length) {
		if(disconnected()) {
			throw new DisconnectedException();
		}
		int nbytes = 0;
		try {
			while(nbytes == 0) {
				if(in.empty()) {
					synchronized(in) {
						while(in.empty()) {
							if(disconnected() || dangling) {
								throw new DisconnectedException();
							}
							try {
								in.wait();	
							} catch(InterruptedException ex) {
								//nothing
							}
						}
					}
				}
				while(nbytes < length && in.empty()) {
					byte val = in.pull();
					bytes[offset + nbytes] = val;
					nbytes++;
				}
				if(nbytes != 0) {
					synchronized(in) {
						in.notify();
					}
				}
			}
		} catch(DisconnectedException ex) {
			if(!disconnected()) {
				this.disconnect = true;
				synchronized(out) {
					out.notify();
				}
			}
			throw ex;
		}
		return nbytes;
	}

	
	@Override
	public int write(byte[] bytes, int offset, int length){
		if(disconnected()) {
			throw new DisconnectedException();
		}
		int nbytes = 0;
		while(nbytes == 0) {
			if(out.full()) {
				synchronized(out) {
					while(out.full()) {
						if(disconnect) {
							throw new DisconnectedException();
						}
						if(dangling) {
							return length;
						}
						try {
							out.wait();
						} 
						catch (InterruptedException ex){
							//nothing
						}
					}
				}
			}
			while(nbytes < length && !out.full()) {
				byte val = bytes[offset+nbytes];
				out.push(val);
				nbytes++;
			}
			if(nbytes != 0) {
				synchronized(out) {
					out.notify();
				}
			}
		}
		return nbytes;
	}


	@Override
	public void disconnect() {
		synchronized(this) {
			if(disconnect) {
				return;
			}
			disconnect = true;
			rch.dangling = true;
		}
		synchronized(out) {
			out.notifyAll();
		}
		synchronized(in) {
			in.notifyAll();
		}
	}

	@Override
	public boolean disconnected() {
		return this.disconnect;
	}

}
