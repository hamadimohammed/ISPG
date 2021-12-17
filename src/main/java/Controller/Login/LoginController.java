package Controller.Login;

import Application.MainWindow;
import Controller.Menu.MenuController;
import Database.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    @FXML
    public void connecter() {
        UserEntity connect_user = UserEntity.get_user_connection();
        //MenuController.user_connected = connect_user.connect(usernameField.getText(),passwordField.getText());
        MenuController.user_connected = connect_user.connect("admin","admin");
        if (MenuController.user_connected!=null){
            MainWindow.setMain();
        }
    }
}