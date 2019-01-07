package prak4serv.Benutzerverwaltung;

import prak4gemklassen.Benutzer;
import prak4gemklassen.Exceptions.BenutzerVerwaltungException;

import java.io.IOException;

public interface BenutzerVerwaltung {
		void benutzerEintragen(Benutzer benutzer) throws
				BenutzerVerwaltungException, IOException, ClassNotFoundException;
		boolean benutzerOk(Benutzer benutzer)
				throws BenutzerVerwaltungException, IOException;
}
