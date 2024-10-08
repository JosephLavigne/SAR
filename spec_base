								Serveur echo simple


Réalisation finale : 2 Classe différentes Serveur et Client. Lors de l’envoie d’un message par le client au serveur. Le serveur renvoie le même message à ce Client


abstract class Broker : cette classe va gérer les connections. Elle Permettra d'obtenir un Channel de connexion. 1 Broker peut gérer plusieurs tâches (Utilisation multi-thread) {
	Broker(String name) : Constructeur d’un Broker en donnant son nom. Un broker possède un nom unique permettant son identification afin de pouvoir l'identifié au moment de la connection. Port unique par broker possèdant un nom différent. 
	Channel accept(int port) : Renvoie le channel de connection accepté par le serveur au port donnée. 
	Channel connect(String name, int port) : Permet de se connecter à un serveur avec son nom et son port. En renvoyant un canal de connection
}


abstract class Channel : Canal de connection en FIFO, no lost, TCP, flux bydirectionnel (full duplex) {
	int read(byte[] bytes, int offset, int length) : Permet de lire dans le buffer du channel, à partir de la case n°(offset-1) et de taille : length. On renvoie le nombre d'octets lu. Le read est bloquant lorsqu'il la length n'est pas compléter.

	int write(byte[] bytes, int offset, int length) : Permet d'écrire dans le buffer du channel, à partir de la case n°(offset-1) et de taille : length. on renvoie le nombre d'octets écrit dans le channel. Bloquant si full. 

	void disconnect() : permet au client de se déconnecter du serveur. Le channel ne sera plus utilisable
	boolean disconnected() : True signifie que le channel est inutilisable car il est déconnecter du serveur. False signifie que la connection est toujours disponible, les méthodes read/write/disconnect sont toujours utilisables. 
}

Une tache peut avoir des communications avec plusieurs Broker




Note de correction : 

Définition : task / broker / Socket / Channel (Broker fabrique de cannal)
