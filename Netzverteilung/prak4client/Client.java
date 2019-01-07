package prak4client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import prak4client.Benutzerverwaltung.*;
import prak4client.Controllers.*;
import prak4gemklassen.*;
import prak4gemklassen.Exceptions.*;

import java.io.*;

public class Client extends Application {
		private Stage refStage;

		private AnwendungsController anwendungsC;

		private AnmeldungsController anmeldungsC;

		private LoginController loginC;

		private boolean lokal=true;

		public String address;

		public BenutzerVerwaltung admin;


		public static void main(String[] args) {
				launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws IOException {

				refStage = primaryStage;
				// 1. Erzeugung eines Objekts der Klasse BenutzerVerwaltungAdmin.
				admin = new BenutzerVerwaltungAdmin();

				// 2. Anfrage an den Benutzer, ob die Datenhaltung initialisiert
				// werden soll und ggf. Initialisierung durchführen.
				System.out.println("Soll die Datenbank initialisiert werden? (0/1)");
				BufferedReader din = new BufferedReader(
						new InputStreamReader(System.in));

						int dbInitialisieren = Integer.parseInt(din.readLine());
						if (dbInitialisieren == 1)
								((BenutzerVerwaltungAdmin) admin).dbInitialisieren();


				// 3. Erzeugung einer LoginScene mit Übergabe der eigenen Referenz an deren Controller.
				try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(
								"Views/login.fxml"));
						Parent root = loader.load();

						loginC = loader.getController();
						loginC.setOrb(this);
						refStage.setTitle("Benutzerverwaltung - Login");
						refStage.setScene(new Scene(root, 500, 350));
						refStage.show();

				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public void neuAnmeldung() {
				if(address != null && !address.isEmpty()) {
						((ClientOrb) admin).setAddress(address);
				}
				loadAnmeldung();
		}

		public void neuerBenutzer(Benutzer benutzer)
				throws IOException, ClassNotFoundException {

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
				throws BenutzerVerwaltungException, IOException {

				if(address != null && !address.isEmpty()) {
						((ClientOrb) admin).setAddress(address);
				}

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
						loginC.setOrb(this);
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
						anwendungsC.setOrb(this);
						refStage.setTitle("Benutzerverwaltung - Anwendung");
						refStage.setScene(new Scene(root, 600, 158));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public void loadAnmeldung() {
				try {
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("Views/anmeldung.fxml"));
						Parent root = loader.load();
						anmeldungsC = loader.getController();
						anmeldungsC.setOrb(this);
						refStage.setTitle("Benutzerverwaltung - Anmeldung");
						refStage.setScene(new Scene(root, 500, 350));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
}
