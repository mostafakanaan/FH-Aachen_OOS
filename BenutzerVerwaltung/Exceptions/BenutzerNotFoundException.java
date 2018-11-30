package BenutzerVerwaltung.Exceptions;

public class BenutzerNotFoundException extends BenutzerVerwaltungException {
		public BenutzerNotFoundException() {
				super("Benutzer unbekannt!");
		}
}