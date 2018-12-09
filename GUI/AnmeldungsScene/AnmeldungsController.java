package GUI.AnmeldungsScene;

import BenutzerVerwaltung.Benutzer;
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

		@FXML
		void ausfuehren() {
				if(!Passwort.getText().equals(Wiederholung.getText())) {
						ButtonType Ups = new ButtonType("Ups!", ButtonBar.ButtonData.OK_DONE);
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
						System.out.println(newUser);

						//Fenster schließen:
						Stage stage = (Stage) ausfuehrenBtn.getScene().getWindow();
						stage.close();
				}
		}

		@FXML
		void rueckSeite() throws IOException {
				Parent root;
				root = FXMLLoader.load(getClass().getResource(
						"../LoginScene/login.fxml"));
				Stage window = (Stage) ausfuehrenBtn.getScene().getWindow();
				window.setTitle("Benutzerverwaltung");
				window.setScene(new Scene(root, 500, 350));
				window.show();
		}

}