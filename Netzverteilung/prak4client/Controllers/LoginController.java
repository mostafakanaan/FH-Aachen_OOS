package prak4client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import prak4client.*;
import prak4client.Benutzerverwaltung.*;
import prak4gemklassen.*;
import prak4gemklassen.Exceptions.*;

import java.io.IOException;

public class LoginController {

		private boolean neuAnmeldung = false;

		private boolean remote = false;

		@FXML
		Button ausfuehrenBtn;

		@FXML
		CheckBox neuAnmeldungCheckbox;

		@FXML
		CheckBox remoteCheckbox;

		@FXML
		TextField UserID;

		@FXML
		TextField remoteAddress;

		@FXML
		PasswordField Passwort;

		private Client refClient;

		public void setOrb(Client c) {
				refClient = c;
		}

		@FXML
		void ausfuehren() throws BenutzerVerwaltungException, IOException {

				if (remote && remoteAddress != null &&
						!remoteAddress.getText().isEmpty()) {
						refClient.address = remoteAddress.getText();
						System.out.println("Neue Remote-Adresse: " + refClient.address);
				}
				if (!neuAnmeldung) {
						Benutzer newUser = new Benutzer(UserID.getText(),
								Passwort.getText().toCharArray());

						refClient.benutzerLogin(newUser);
				} else {
						if (remote)
								refClient.neuAnmeldung();
						else
								refClient.neuAnmeldungLokal();
				}
		}

		@FXML
		void neu_Anmelden() {
				neuAnmeldung = neuAnmeldungCheckbox.isSelected();

				System.out
						.println("Der aktuelle Wert von Neu-Anmeldung = " + neuAnmeldung);
		}

		@FXML
		void remoteCheckbox() {
				remote = remoteCheckbox.isSelected();
				if (remote)
						refClient.admin = new ClientOrb();
				else
						refClient.admin = new BenutzerVerwaltungAdmin();
		}

		public void loginFehler(String header, String text) {

				Alert alert = new Alert(Alert.AlertType.ERROR, text,
						ButtonType.OK);
				alert.setTitle("Error");
				alert.setHeaderText(header);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.OK) {
						refClient.loadLogin();
				}
		}
}