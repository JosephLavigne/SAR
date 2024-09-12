								Serveur echo simple


Réalisation finale : 2 Classe différentes Serveur et Client. Lors de l’envoie d’un message par le client au serveur. Le serveur renvoie le même message à ce Client


abstract class Broker : qui va gérer les connections {
	Broker(String name) : Constructeur d’un Broker en donnant son nom.
	Channel accept(int port) : Renvoie le channel de connection accepté par le serveur au port donnée. 
	Channel connect(String name, int port) : Permet de se connecter à un serveur avec son nom et son port. En renvoyant un canal de connection
}


abstract class Channel : Canal de connection {
	int read(byte[] bytes, int offset, int length) : Permet de lire dans le buffer du channel, à partir de la case n°(offset-1) et de taille : length. 

	int write(byte[] bytes, int offset, int length) : Permet d'écrire dans le buffer du channel, à partir de la case n°(offset-1) et de taille : length. 

	void disconnect() : permet au client de se déconnecter du serveur. Le channel ne sera plus utilisable
	boolean disconnected() : True signifie que le channel est inutilisable car il est déconnecter du serveur. False signifie que la connection est toujours disponible, les méthodes read/write/disconnect sont toujours utilisables. 
}

