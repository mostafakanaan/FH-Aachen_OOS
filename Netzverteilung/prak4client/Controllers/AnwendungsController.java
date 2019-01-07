package prak4client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import prak4client.*;

public class AnwendungsController {

		@FXML
		Button beendenBtn;

		@FXML
		Button logoutBtn;

		private Client refClient;

		public void setOrb(Client c){
				refClient = c;
		}

		@FXML
		void beenden() {
				System.out.println("Das Programm wurde abgebrochen.");
				Stage stage = (Stage) beendenBtn.getScene().getWindow();
				stage.close();
		}

		@FXML
		void loginPage() {
				refClient.loadLogin();
		}
}