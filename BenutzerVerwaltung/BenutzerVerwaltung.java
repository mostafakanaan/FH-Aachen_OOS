package BenutzerVerwaltung;

import BenutzerVerwaltung.Exceptions.BenutzerVerwaltungException;

public interface BenutzerVerwaltung {
		void benutzerEintragen(Benutzer benutzer) throws
				BenutzerVerwaltungException;
		boolean benutzerOk(Benutzer benutzer) throws BenutzerVerwaltungException;
}
