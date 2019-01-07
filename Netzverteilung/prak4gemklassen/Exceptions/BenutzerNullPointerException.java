package prak4gemklassen.Exceptions;

public class BenutzerNullPointerException extends BenutzerVerwaltungException {
		public BenutzerNullPointerException(){
				super("Keine Referenz zum Benutzer gefunden.");
		}
}