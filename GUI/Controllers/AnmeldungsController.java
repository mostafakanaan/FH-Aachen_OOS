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

		private MainApplication refMainApplication;

		public void setMain(MainApplication m) {
				refMainApplication = m;
		}

		@FXML
		void ausfuehren() throws IOException {
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

						refMainApplication.neuerBenutzer(newUser);
				}
		}

		@FXML
		void rueckSeite() throws IOException {
				refMainApplication.loadLogin();
		}
}