package BenutzerVerwaltung;

import java.io.Serializable;

public class Benutzer implements Serializable {
		public String userID;

		public char[] passWort;

		public Benutzer() {
		}

		public Benutzer(String userID, char[] passWort) {
				this.userID = userID;
				this.passWort = passWort;
		}

		@Override
		public boolean equals(Object other) {
				if(other == this) return true;
				if(!(other instanceof Benutzer)) return false;

				Benutzer benutzer = (Benutzer) other;

				return (this.toString().equals(benutzer.toString()));
		}

		public String toString() {
				return ("userID: " + userID + "\nPasswort: " +
						String.copyValueOf(passWort));
		}
}
