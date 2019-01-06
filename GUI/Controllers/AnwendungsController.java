package GUI.Controllers;

import GUI.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AnwendungsController {

		@FXML
		Button beendenBtn;

		@FXML
		Button logoutBtn;

		private MainApplication refMainApplication;

		public void setMain(MainApplication m){
				refMainApplication = m;
		}

		@FXML
		void beenden() {
				System.out.println("Das Programm wurde abgebrochen.");
				Stage stage = (Stage) beendenBtn.getScene().getWindow();
				stage.close();
		}

		@FXML
		void loginPage() {
				refMainApplication.loadLogin();
		}
}