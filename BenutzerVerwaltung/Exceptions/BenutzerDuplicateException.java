package BenutzerVerwaltung.Exceptions;

public class BenutzerDuplicateException extends BenutzerVerwaltungException {
		public BenutzerDuplicateException() {
				super("Benutzer ist bereits registriert.");
		}
}