package Controller.Client;

import Application.MainWindow;
import Application.ObservePrimaryStage;
import Application.ObserverStage;
import Interfaces.WindowController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientInsert  implements WindowController,ObserverStage {
    private Stage active_stage;
    @FXML TextField nom_prenomField;
    public void initialize(){
        MainWindow.observe_primary_stage.attach(this);
    }
    @Override
    public void save() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void cancel() {
        active_stage=(Stage) nom_prenomField.getScene().getWindow();
        active_stage.close();
    }

    @Override
    public void close() {
        cancel();
    }
}
