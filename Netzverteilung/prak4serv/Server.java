package prak4serv;

import java.io.*;

import prak4gemklassen.Exceptions.BenutzerVerwaltungException;
import prak4serv.Benutzerverwaltung.*;

public class Server {

		public Server()
				throws IOException, ClassNotFoundException,
				BenutzerVerwaltungException {

				BenutzerVerwaltungAdmin bva = new BenutzerVerwaltungAdmin();
				ServerOrb so = new ServerOrb(bva);
		}

		public static void main(String[] args)
				throws BenutzerVerwaltungException, IOException,
				ClassNotFoundException {

						Server s = new Server();
		}
}