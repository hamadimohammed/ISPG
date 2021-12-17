package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewWindow {
	public static Stage prime;
	public static Parent root;
	public static boolean maximized = false , always_top = false , front = false;

	public NewWindow(String title, String Link) {
		prime = new Stage();
		prime.setTitle(title);
		try {
			root = FXMLLoader.load(getClass().getResource(Link));
			Scene scene = new Scene(root);
			prime.setScene(scene);
			//prime.setAlwaysOnTop(true);
			if(maximized)
				prime.setMaximized(true);
			if(always_top)
				prime.setAlwaysOnTop(true);
			prime.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void set_stage_wait(String title, String Link) {
		prime = new Stage();
		prime.setTitle(title);
		try {
			root = FXMLLoader.load(NewWindow.class.getResource(Link));
			Scene scene = new Scene(root);
			prime.setScene(scene);
			if(maximized)
				prime.setMaximized(true);
			if(always_top)
				prime.setAlwaysOnTop(true);
			prime.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Stage set_stage(String title, String Link) {

		prime = new Stage();
		prime.setTitle(title);

		try {
			root = FXMLLoader.load(NewWindow.class.getResource(Link));
			Scene scene = new Scene(root);
			prime.setScene(scene);
			if(maximized)
				prime.setMaximized(true);
			if(always_top)
				prime.setAlwaysOnTop(true);
			prime.show();
			return prime;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
