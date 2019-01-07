package prak4gemklassen.Exceptions;

public class BenutzerEmptyException extends BenutzerVerwaltungException {
		public BenutzerEmptyException(){
				super("Benutzer hat keine Daten.");
		}
		public BenutzerEmptyException(String ausgabe){
				super(ausgabe);
		}
}