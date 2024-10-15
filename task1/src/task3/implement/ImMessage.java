package task3.implement;

import task3.abastract.Message;

public class ImMessage extends Message{
	
	public ImMessage(byte[] bytes, int offset, int length) {
		super(bytes, offset, length);
	}
	
	public ImMessage(byte[] bytes) {
		super(bytes);
	}

	@Override
	public int getLength() {
		return this.length;
	}

	@Override
	public int getOffset() {
		return this.offset;
	}

	@Override
	public byte[] getBytes() {
		return this.bytes;
	}


}
