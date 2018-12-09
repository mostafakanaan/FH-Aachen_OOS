package GUI.AnwendungsScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AnwendungsController {

		@FXML
		Button abbrechenBtn;

		@FXML
		Button loginBtn;

		@FXML
		void abbrechen() {
				System.out.println("Das Programm wurde abgebrochen.");
				Stage stage = (Stage) abbrechenBtn.getScene().getWindow();
				stage.close();
		}

		@FXML
		void loginPage() throws IOException {
				Parent root = FXMLLoader.load(getClass().getResource(
						"../LoginScene/login.fxml"));
				Stage window = (Stage) abbrechenBtn.getScene().getWindow();
				window.setTitle("Benutzerverwaltung");
				window.setScene(new Scene(root, 500, 350));
				window.show();
		}
}