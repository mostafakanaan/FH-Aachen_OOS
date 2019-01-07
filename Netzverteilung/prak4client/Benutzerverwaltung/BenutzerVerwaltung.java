package prak4client.Benutzerverwaltung;
import prak4gemklassen.*;
import prak4gemklassen.Exceptions.*;

import java.io.IOException;

public interface BenutzerVerwaltung {
		void benutzerEintragen(Benutzer benutzer) throws
				BenutzerVerwaltungException, IOException, ClassNotFoundException;
		boolean benutzerOk(Benutzer benutzer) throws BenutzerVerwaltungException,
				IOException;
}
