package prak4client;

import java.io.*;
import java.net.Socket;
import prak4gemklassen.*;
import prak4gemklassen.Exceptions.*;
import prak4serv.Benutzerverwaltung.*;

public class ClientOrb implements BenutzerVerwaltung,
		prak4client.Benutzerverwaltung.BenutzerVerwaltung {

		private String address;

		private int port;

		private Client refClient;

		public void setOrb(Client c) {
				refClient = c;
		}

		public ClientOrb() {
				this.address = "localhost";
				this.port = 5555;
		}

		public ClientOrb(String address, int port){
				this.address = address;
				this.port = port;
		}

		public void setAddress(String address) {
				this.address = address;
		}

		@Override
		public void benutzerEintragen(Benutzer benutzer)
				throws BenutzerVerwaltungException, IOException,
				ClassNotFoundException {
				Socket server = new Socket(address, port);
				ObjectOutputStream out =  new ObjectOutputStream(server.getOutputStream());
				ObjectInputStream in =  new ObjectInputStream(server.getInputStream());
				out.writeObject("benutzerEintragen");
				out.writeObject(benutzer);
				out.flush();

				String response = in.readObject().toString();
				server.close();
				if(response.equals("Benutzer bereits vorhanden!")) {
						throw new BenutzerDuplicateException();
				}
		}

		@Override
		public boolean benutzerOk(Benutzer benutzer)
				throws IOException {

				Socket server = new Socket(address, port);
				ObjectOutputStream out =  new ObjectOutputStream(server.getOutputStream());
				ObjectInputStream in =  new ObjectInputStream(server.getInputStream());
				out.writeObject("benutzerOk");
				out.writeObject(benutzer);
				out.flush();
				boolean response = in.readBoolean();
				server.close();
				return response;
		}
}
