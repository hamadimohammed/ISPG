package Application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType;

public class AlertMessage {
	public static Alert alert;

	public AlertMessage(String title, String content, AlertType type) {
		alert = new Alert(type, content);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle(title);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.setAlwaysOnTop(true);
		stage.requestFocus();
		alert.showAndWait();
	}
public static boolean AlertMessageConfirmation(String title, String content,String type) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(type);
		alert.setContentText(content);

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.setAlwaysOnTop(true);
		stage.requestFocus();

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean delete(String type,String content){
		return AlertMessageConfirmation(type,"Voulez-vous vraiment supprimer "+content+"selectionez","Supprission");
	}
}
