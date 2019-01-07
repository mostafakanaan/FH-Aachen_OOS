package prak4gemklassen.Exceptions;

public class BenutzerNotFoundException extends BenutzerVerwaltungException {
		public BenutzerNotFoundException() {
				super("Benutzer unbekannt!");
		}
}