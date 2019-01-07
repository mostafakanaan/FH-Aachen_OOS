package prak4gemklassen.Exceptions;

public class BenutzerNoIdException extends BenutzerEmptyException {
		public BenutzerNoIdException() {
				super("Benutzer hat keine userID.");
		}
}
