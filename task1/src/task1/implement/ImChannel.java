package task1.implement;

import task1.abastract.Channel;

public class ImChannel extends Channel {
	
	boolean disconnect;
	CircularBuffer in; // read
	CircularBuffer out; // write
	
	public ImChannel(RDV rdv,CircularBuffer read, CircularBuffer write) {
		super(rdv);
		this.disconnect = false;
		this.in = read;
		this.out = write;
	}

	@Override
	public int read(byte[] bytes, int offset, int length) {
		int read = 0;
		synchronized(in) {
			while(read <= length || !in.empty()) {
				if(!this.disconnected()) {
					bytes[read] = in.pull();
					read++;
				}
			}
		}
		return read;
	}

	@Override
	public int write(byte[] bytes, int offset, int length) {
		int write = 0;
		while(length > 0 || !out.full()) {
			if(!this.rdv.cc.disconnected() && !this.rdv.ca.disconnected()) {
				out.push(bytes[write]);
				write++;
			}
		}
		return write;
	}

	@Override
	public void disconnect() {
		this.disconnect = true;	
	}

	@Override
	public boolean disconnected() {
		return this.disconnect;
	}

}
