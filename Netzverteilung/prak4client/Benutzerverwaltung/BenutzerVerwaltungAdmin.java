package prak4client.Benutzerverwaltung;

import prak4gemklassen.*;
import prak4gemklassen.Exceptions.*;
import java.util.ArrayList;
import java.io.*;

public class BenutzerVerwaltungAdmin
		implements BenutzerVerwaltung, Serializable {

		//Private Datenstruktur Liste aller Benutzer:
		private ArrayList<Benutzer> userlist = new ArrayList<Benutzer>();

		//Getter und setter für userlist:
		public ArrayList<Benutzer> getUserlist() {
				return userlist;
		}

		public void setUserlist(ArrayList<Benutzer> userlist) {
				this.userlist = userlist;
		}

		@Override
		public void benutzerEintragen(Benutzer benutzer)
				throws BenutzerVerwaltungException {

				if (benutzer == null) {
						throw new BenutzerNullPointerException();

				} else if ((benutzer.userID == null) || (benutzer.userID.isEmpty()) &&
						(benutzer.passWort == null || benutzer.passWort.length == 0)) {
						throw new BenutzerEmptyException();

				} else if ((benutzer.userID == null) || (benutzer.userID.isEmpty())) {
						throw new BenutzerNoIdException();

				} else if (benutzer.passWort == null || benutzer.passWort.length == 0) {
						throw new BenutzerNoPasswordException();

				} else {
						read();

						boolean found = false;
						for (Benutzer b : userlist) {
								if (b.userID.equals(benutzer.userID))
										found = true;
						}
						if (found) {
								throw new BenutzerDuplicateException();
						} else {
								this.userlist.add(benutzer);
								write();
						}
				}
		}

		@Override
		public boolean benutzerOk(Benutzer benutzer)
				throws BenutzerVerwaltungException {

				if (benutzer == null) {
						throw new BenutzerNullPointerException();

				} else if ((benutzer.userID == null) || (benutzer.userID.isEmpty()) &&
						(benutzer.passWort == null || benutzer.passWort.length == 0)) {
						throw new BenutzerEmptyException();

				} else if (benutzer.userID == null ||
						benutzer.userID.isEmpty()) {
						throw new BenutzerNoIdException();

				} else if (benutzer.passWort == null ||
						benutzer.passWort.length == 0) {
						throw new BenutzerNoPasswordException();

				} else {
						read();
						for (Benutzer b : userlist)
								if (b.equals(benutzer))
										return true;
				}
				return false;
		}

		public void benutzerLöschen(Benutzer benutzer)
				throws BenutzerVerwaltungException {

				if (benutzer == null) {
						throw new BenutzerNullPointerException();

				} else if ((benutzer.userID == null) || (benutzer.userID.isEmpty()) &&
						(benutzer.passWort == null || benutzer.passWort.length == 0)) {
						throw new BenutzerEmptyException();

				} else if (benutzer.userID == null ||
						benutzer.userID.isEmpty()) {
						throw new BenutzerNoIdException();

				} else if (benutzer.passWort == null ||
						benutzer.passWort.length == 0) {
						throw new BenutzerNoPasswordException();
				} else {
						read();

						if (!this.userlist.contains(benutzer))
								throw new BenutzerNotFoundException();
						else {
								userlist.remove(benutzer);
								write();
						}
				}
		}

		public void dbInitialisieren() {
				userlist.clear();
				write();
		}

		public void write() {
				try {
						FileOutputStream fs = new FileOutputStream("Users.s");
						ObjectOutputStream os = new ObjectOutputStream(fs);

						os.writeObject(userlist);
						os.close();

				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public void read() {
				try {
						FileInputStream fs = new FileInputStream("Users.s");
						ObjectInputStream is = new ObjectInputStream(fs);

						this.userlist = (ArrayList<Benutzer>) is.readObject();
						is.close();

				} catch (ClassNotFoundException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

}

