package Database;

import Application.MainWindow;
import Application.ObservePrimaryStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArticleEntityTest extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    @Test
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Model/Article/Articles.fxml"));
        Scene scene = new Scene(root);
        stage.setMaximized(false);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }
}