package GUI;

import java.io.*;

import BenutzerVerwaltung.*;
import BenutzerVerwaltung.Exceptions.BenutzerDuplicateException;
import BenutzerVerwaltung.Exceptions.BenutzerNotFoundException;
import BenutzerVerwaltung.Exceptions.BenutzerVerwaltungException;
import GUI.Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class MainApplication extends Application {

		private Stage refStage;

		private AnwendungsController anwendungsC;

		private AnmeldungsController anmeldungsC;

		private LoginController loginC;

		public static void main(String[] args) {
				launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {

				refStage = primaryStage;

				// 1. Erzeugung eines Objekts der Klasse BenutzerVerwaltungAdmin:
				BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();

				// 2. Anfrage an den Benutzer, ob die Datenhaltung initialisiert
				// werden soll und ggf. Initialisierung durchführen.
				System.out.println("Soll die Datenbank initialisiert werden? (0/1)");
				BufferedReader din = new BufferedReader(
						new InputStreamReader(System.in));
				try {
						int dbInitialisieren = Integer.parseInt(din.readLine());
						if (dbInitialisieren == 1)
								admin.dbInitialisieren();
				} catch (Exception e) {
						e.printStackTrace();
				}

				// 3. Erzeugung einer LoginScene mit Übergabe der eigenen Referenz an deren Controller.
				try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(
								"Views/login.fxml"));
						Parent root = loader.load();

						loginC = loader.getController();
						loginC.setMain(this);
						refStage.setTitle("Benutzerverwaltung - Login");
						refStage.setScene(new Scene(root, 500, 350));
						refStage.show();

				} catch (IOException e) {
						e.printStackTrace();
				}

		}

		public void neuAnmeldung() {
				// Die Scene wird durch eine AnmeldungsScene ersetzt und deren Controller
				// wird die eigene Referenz übergeben.
				try {
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("Views/anmeldung.fxml"));
						Parent root = loader.load();
						anmeldungsC = loader.getController();
						anmeldungsC.setMain(this);
						refStage.setTitle("Benutzerverwaltung - Anmeldung");
						refStage.setScene(new Scene(root, 500, 350));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public void neuerBenutzer(Benutzer benutzer) {
				/*Es wird versucht den neuen Benutzer durch Aufruf der Methode
				benutzerEintragen(benutzer)in die BenutzerVerwaltungAdmin einzutragen.
				Ist dies erfolgreich, so soll dem Anwender angezeigt werden, dass er
				jetzt den Login-Vorgang durchführen kann (indem eine LoginScene erzeugt
				 und deren Controller die eigene Referenz übergeben wird).
				Beim Auftreten der Exception soll eine aussagekräftige Fehlermeldung
				angezeigt werden (z.B. in dem obersten Textfeld der AnmeldungsScene).
				*/
				BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
				try {
						admin.benutzerEintragen(benutzer);
						Alert alert = new Alert(Alert.AlertType.INFORMATION,
								"Sie sind nun angemeldet. Loggen Sie sich bitte ein!",
								ButtonType.OK);
						alert.setTitle("Registriert");
						alert.setHeaderText(
								"Anmeldung ERFOLGREICH :)");
						alert.showAndWait();
						if (alert.getResult() == ButtonType.OK)
								loadLogin();

				} catch (BenutzerDuplicateException e) {
						loginC.loginFehler("Benutzer existiert bereits!", "Sie sind schon angemeldet. Versuchen Sie bitte, sich einzuloggen!");
						e.printStackTrace();

				} catch (BenutzerVerwaltungException e) {
						loginC.loginFehler("Ein Fehler ist aufgetreten!", "Versuchen Sie es bitte erneut!");
						e.printStackTrace();
				}

		}

		public void benutzerLogin(Benutzer benutzer)
				throws BenutzerVerwaltungException {
				/* Es wird durch Aufruf der Methode benutzerOk(benutzer) überprüft,
					 ob der Benutzer bereits in BenutzerVerwaltungAdmin eingetragen ist.
					Ist dies der Fall, soll dem Anwender angezeigt werden, dass er nun
					 das System benutzen kann, indem eine AnwendungsScene erzeugt wird
					  (er kann jedoch lediglich nur den Button drücken).
					Im anderen Fall soll eine aussagekräftige Fehlermeldung angezeigt
					 werden (z.B. in dem obersten Textfeld der LoginScene).
				*/
				BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();

				if (admin.benutzerOk(benutzer)) {
						loadAnwendung();
				} else {
						Alert alert = new Alert(Alert.AlertType.ERROR,
								"Sie sind nicht angemeldet. Melden Sie sich bitte an!",
								ButtonType.OK);
						alert.setTitle("Error");
						alert.setHeaderText(
								"Benutzer nicht gefunden!");
						alert.showAndWait();
						if (alert.getResult() == ButtonType.OK)
								neuAnmeldung();
				}
		}

		public void loadLogin() {
				try {
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("Views/login.fxml"));
						Parent root = loader.load();
						loginC = loader.getController();
						loginC.setMain(this);
						refStage.setTitle("Benutzerverwaltung - Login");
						refStage.setScene(new Scene(root, 500, 350));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public void loadAnwendung() {
				try {
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("Views/anwendung.fxml"));
						Parent root = loader.load();
						anwendungsC = loader.getController();
						anwendungsC.setMain(this);
						refStage.setTitle("Benutzerverwaltung - Anwendung");
						refStage.setScene(new Scene(root, 600, 158));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

}


