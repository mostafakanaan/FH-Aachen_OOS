package GUI.LoginScene;

import BenutzerVerwaltung.Benutzer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

		private boolean neuAnmeldung = false;

		@FXML
		Button ausfuehrenBtn;

		@FXML
		Button zurueckBtn;

		@FXML
		CheckBox neuAnmeldungCheckbox;

		@FXML
		TextField UserID;

		@FXML
		PasswordField Passwort;

		@FXML
		void neu_Anmelden() throws IOException {
				neuAnmeldung = neuAnmeldungCheckbox.isSelected();

				System.out
						.println("Der aktuelle Wert von Neu-Anmeldung = " + neuAnmeldung);

				//------------------------------------------------------------------------------//

				if (neuAnmeldung) {
						ButtonType Ja = new ButtonType("Ja", ButtonBar.ButtonData.YES);
						ButtonType Nein = new ButtonType("Nein", ButtonBar.ButtonData.NO);
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
								"Wollen Sie sich registrieren?", Ja, Nein);
						alert.setTitle("Neuer Benutzer");
						alert.setHeaderText("Neu-Anmeldung");
						alert.showAndWait();
						if (alert.getResult() == Ja) {
								Parent root;
								root = FXMLLoader.load(getClass().getResource(
										"../AnmeldungsScene/anmeldung.fxml"));
								Stage window;
								window = (Stage) ausfuehrenBtn.getScene().getWindow();
								window.setTitle("Benutzerverwaltung");
								window.setScene(new Scene(root, 500, 350));
								window.show();
						} else if (alert.getResult() == Nein) {
								neuAnmeldungCheckbox.setSelected(false);
								neuAnmeldung = false;
								System.out
										.println("Der aktuelle Wert von Neu-Anmeldung = " +
												neuAnmeldung);
						}
				}
		}

		@FXML
		void ausfuehren() {
				Benutzer newUser = new Benutzer(UserID.getText(),
						Passwort.getText().toCharArray());
				System.out.println(newUser);

				//Fenster schließen:
				Stage stage = (Stage) ausfuehrenBtn.getScene().getWindow();
				stage.close();
		}

		@FXML
		void rueckSeite() throws IOException {
				Parent root = FXMLLoader.load(getClass().getResource(
						"../AnwendungsScene/anwendung.fxml"));
				Stage window = (Stage) zurueckBtn.getScene().getWindow();
				window.setTitle("Benutzerverwaltung");
				window.setScene(new Scene(root, 600, 158));
				window.show();
		}

}