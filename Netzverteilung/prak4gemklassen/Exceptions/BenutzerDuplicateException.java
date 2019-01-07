package prak4gemklassen.Exceptions;

public class BenutzerDuplicateException extends BenutzerVerwaltungException {
		public BenutzerDuplicateException() {
				super("Benutzer ist bereits registriert.");
		}
}