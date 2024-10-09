package task3.abastract;

abstract class QueueBroker {
	String name;
	QueueBroker(String name) {
		this.name = name;
	}
	interface AcceptListener {
			void accepted(MessageQueue queue);
	}
	abstract boolean bind(int port, AcceptListener listener);
	abstract boolean unbind(int port);
	interface ConnectListener {
		void connected(MessageQueue queue);
		void refused();
	}
	abstract boolean connect(String name, int port, ConnectListener listener);
}
