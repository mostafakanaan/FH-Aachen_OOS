package BenutzerVerwaltung.Exceptions;

public class BenutzerNoIdException extends BenutzerEmptyException {
		public BenutzerNoIdException() {
				super("Benutzer hat keine userID.");
		}
}
