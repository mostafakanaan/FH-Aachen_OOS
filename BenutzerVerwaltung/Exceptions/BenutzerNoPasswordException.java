package BenutzerVerwaltung.Exceptions;

public class BenutzerNoPasswordException extends BenutzerEmptyException {
		public BenutzerNoPasswordException() {
				super("Benutzer hat kein Passwort.");
		}
}
