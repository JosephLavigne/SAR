package task2.implement;

import task1.abastract.Channel;
import task1.implement.DisconnectedException;
import task2.abstarct.MessageQueue;

public class ImMessageQueue extends MessageQueue {
	
	Channel c;
	int offset; // offset received
	
	public ImMessageQueue(Channel c) {
		this.c = c;
		this.offset = 0;
	}

	@Override
	public void send(byte[] bytes, int offset, int length) {
		if(!this.closed()) {
			byte[] len = new byte[4]; // taille d'un int
			// CONVERSION INT EN BYTE
			len[0] = (byte) (length >> 24);
			len[1] = (byte) (length >> 16);
			len[2] = (byte) (length >> 8);
			len[3] = (byte) (length);
			
			byte[] message = new byte[length + 4];
			message[0] = len[0];
			message[1] = len[1];
			message[2] = len[2];
			message[3] = len[3];
			
			System.arraycopy(bytes, offset, message, 4, length);
			
			int ecriture = 0;
			try {
				while(ecriture != length) {
					ecriture += this.c.write(message, ecriture, length - ecriture);
				}
			} catch (DisconnectedException e) {
				// fin car deconnecter 
				e.printStackTrace();
			}
		}
	}

	@Override
	public byte[] receive() {
		byte[] length = new byte[4];
		try {
			//Recuperation de la taille du message
			int reponse = 0;
			while(reponse < 4) {
				reponse += this.c.read(length, reponse, 4-reponse);
			}
			// CONVERSION BYTES en INT
			int len = (length[0] << 24) | ((length[1] & 0xFF) << 16) | ((length[2] & 0xFF) << 8) | ((length[3] & 0xFF));
			
			//Lecture du Message
			int lecture = 0;
			byte[] message = new byte[len];
			while(lecture < len) {
				lecture += this.c.read(message, lecture, len - lecture);
			}
			return message;
		} catch (DisconnectedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void close() {
		this.c.disconnect();
		
	}

	@Override
	public boolean closed() {
		return this.c.disconnected();
	}

}
