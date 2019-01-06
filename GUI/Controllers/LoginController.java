package GUI.Controllers;

import BenutzerVerwaltung.Benutzer;
import BenutzerVerwaltung.Exceptions.BenutzerVerwaltungException;
import GUI.MainApplication;
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
		CheckBox neuAnmeldungCheckbox;

		@FXML
		TextField UserID;

		@FXML
		PasswordField Passwort;

		private MainApplication refMainApplication;

		public void setMain(MainApplication m) {
				refMainApplication = m;
		}

		@FXML
		void ausfuehren() throws BenutzerVerwaltungException {

				if (!neuAnmeldung) {
						Benutzer newUser = new Benutzer(UserID.getText(),
								Passwort.getText().toCharArray());

						refMainApplication.benutzerLogin(newUser);
				} else {
						refMainApplication.neuAnmeldung();
				}
		}

		@FXML
		void neu_Anmelden() {
				neuAnmeldung = neuAnmeldungCheckbox.isSelected();

				System.out
						.println("Der aktuelle Wert von Neu-Anmeldung = " + neuAnmeldung);
		}

		public void loginFehler(String header, String text) {

				Alert alert = new Alert(Alert.AlertType.ERROR, text,
						ButtonType.OK);
				alert.setTitle("Error");
				alert.setHeaderText(header);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.OK) {
				refMainApplication.loadLogin();
				}
		}
}