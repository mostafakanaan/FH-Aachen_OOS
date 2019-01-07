package prak4serv;

import java.io.*;
import java.net.*;
import prak4gemklassen.*;
import prak4gemklassen.Exceptions.*;
import prak4serv.Benutzerverwaltung.*;

public class ServerOrb {

		public ServerOrb(BenutzerVerwaltungAdmin admin)
				throws IOException, ClassNotFoundException,
				BenutzerVerwaltungException {

				ServerSocket server = new ServerSocket(5555);
				System.out.println("> Server gestartet auf port 5555 . . .");

				initdb(admin);

				while (true) {

						Socket client = server.accept();

						// Streams erzeugen:
						ObjectInputStream in = new ObjectInputStream(
								client.getInputStream());
						ObjectOutputStream out = new ObjectOutputStream(
								client.getOutputStream());

						// Lesen der Methodenkodierung:
						String methode = (String) in.readObject();
						// Lesen des Parameters:
						Benutzer ben = (Benutzer) in.readObject();

						// Methodenimplementierungen durch Delegation an
						// Dienstanbieterobjekt:
						if (methode.equals("benutzerOk")) {
								System.out.println("In benutzerOk");
								out.writeBoolean(admin.benutzerOk(ben));
								// Sicherstellen, dass Ergebnis transportiert wird:
								out.flush();
						}
						if (methode.equals("benutzerEintragen")) {
								System.out.println("In benutzerEintragen");
								try {
										admin.benutzerEintragen(ben);
										out.writeObject("Benutzer remote eintragen erfolgreich!");
								} catch (BenutzerDuplicateException e) {
										out.writeObject("Benutzer bereits vorhanden!");
								}
								// Sicherstellen, dass Ergebnis transportiert wird:
								out.flush();
						}
						client.close();
				}
		}

		private void initdb(BenutzerVerwaltungAdmin bva) throws IOException {

				// Anfrage an den Benutzer, ob die Datenhaltung initialisiert werden soll..
				System.out.println("Soll die Datenbank initialisiert werden? (0/1)");
				BufferedReader din = new BufferedReader(
						new InputStreamReader(System.in));
				int dbInitialisieren = Integer.parseInt(din.readLine());

				if (dbInitialisieren == 1) {
						bva.dbInitialisieren();
						System.out.println("DB initialisiert.");
				} else
						System.out.println("Initialisierung übersprungen.");
		}
}
