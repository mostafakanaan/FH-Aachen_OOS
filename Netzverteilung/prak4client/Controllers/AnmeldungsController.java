package prak4client.Controllers;

import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import prak4client.*;
import prak4gemklassen.Benutzer;

public class AnmeldungsController {

		@FXML
		Button ausfuehrenBtn;

		@FXML
		Button zurueckBtn;

		@FXML
		TextField UserID;

		@FXML
		PasswordField Passwort;

		@FXML
		PasswordField Wiederholung;

		private Client refClient;

		public void setOrb(Client c) {
				refClient = c;
		}

		@FXML
		void ausfuehren() throws IOException, ClassNotFoundException {
				if (!Passwort.getText().equals(Wiederholung.getText())) {
						ButtonType Ups = new ButtonType("Ups!",
								ButtonBar.ButtonData.OK_DONE);
						Alert alert = new Alert(Alert.AlertType.ERROR,
								"Wiederholung stimmt mit dem Passwort NICHT überein.", Ups);
						alert.setTitle("Error");
						alert.setHeaderText("Haben Sie sich vertippt?");
						alert.showAndWait();
						Passwort.clear();
						Wiederholung.clear();

				} else {
						Benutzer newUser = new Benutzer(UserID.getText(),
								Passwort.getText().toCharArray());

						refClient.neuerBenutzer(newUser);
				}
		}

		@FXML
		void rueckSeite() {
				refClient.loadLogin();
		}
}