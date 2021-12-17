package Controller.Client;

import Application.NewWindow;
import Interfaces.GestionController;
import Model.Personne.Externe.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;

public class ClientsController implements GestionController {

    @FXML
    TableView<Client> clients_table;
    @FXML
    TableColumn<Client, Integer> id_column;
    @FXML TableColumn<Client, String> ai_column,mf_column,nis_column,nrc_column;
    @FXML TableColumn<Client, String> nom_column,addresse_column,phone_column,date_column;
    @FXML TableColumn<Client, Double> solde_column ;
    @FXML
    TextField search_field;
    @FXML
    ToggleButton update_btn;

    @Override
    public void initialize() {

    }

    @Override
    public void insert() {

        NewWindow.always_top=true;
        NewWindow.maximized=false;
        NewWindow.set_stage("Inserer un client","/Clients/InsertClient.fxml");
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void load_permission() {

    }

    @Override
    public void load_data() {

    }

    @Override
    public void editable_cols() {

    }

    @Override
    public void properties() {

    }

    @Override
    public void events(KeyEvent event) {

    }
}
