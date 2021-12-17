package Controller.Menu;

import Application.MainWindow;
import Model.Personne.Intervenant.InternePersonne;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MenuController {
    public static InternePersonne user_connected;
    @FXML VBox sidebar;
    @FXML public Text user;

    public void initialize() {
        initialize_sidedbar();
    }

    private void initialize_sidedbar(){
        MenuItem gestion_menu = new MenuItem("Gestion","/Assets/order_history_26px.png");
        gestion_menu.setOnAction(actionEvent -> gestion());

        MenuItem article_menu = new MenuItem("Articles","/Assets/blog_26px.png");
        article_menu.setOnAction(actionEvent -> article());

        MenuItem client_menu = new MenuItem("Clients","/Assets/customer_26px.png");
        client_menu.setOnAction(actionEvent -> client());

        MenuItem fournisseur_menu = new MenuItem("Fournisseurs","/Assets/supplier_26px.png");
        fournisseur_menu.setOnAction(actionEvent -> fournisseur());

        MenuItem users_menu = new MenuItem("Utilisateurs","/Assets/users_26px.png");
        users_menu.setOnAction(actionEvent -> users());

        MenuItem statistic_menu = new MenuItem("Statistics","/Assets/statistics_26px.png");
        statistic_menu.setOnAction(actionEvent -> statistics());

        MenuItem profile_menu = new MenuItem("Profile","/Assets/user_26px.png");
        profile_menu.setOnAction(actionEvent -> my_profile());

        Map<String,MenuItem> menu_access= new HashMap<>();
        menu_access.put(Menu.Gestion.toString(),gestion_menu);
        menu_access.put(Menu.Article.toString(),article_menu);
        menu_access.put(Menu.Client.toString(),client_menu);
        menu_access.put(Menu.Fournisseur.toString(),fournisseur_menu);
        menu_access.put(Menu.Statistic.toString(),statistic_menu);
        menu_access.put(Menu.Utilisateur.toString(),users_menu);
        menu_access.put(Menu.Profile.toString(),profile_menu);


        for (String droit : user_connected.getAccees()){
            sidebar.getChildren().add(menu_access.get(droit));
        }
        user.setText("@"+user_connected.getUsername());
    }

    public void disconnect(){
        try {
            user_connected=null;
            MainWindow.relance_login();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*public void admin() {
        //#1F8FE8
        /*try {

            MainWindow.setPaneFragment("/user/Admin.fxml");

            //MainWindow.setPaneFragment(FXMLLoader.load(getClass().getResource("../fxmlMainDesign/Articles.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            new AlertMessage("Error", "Error d'acceder a l'administration  !!!\nERROR : " + e.getMessage(), AlertType.ERROR);
        }
    }
    /**********************************************************/
    @FXML
    public void gestion() {
        /*try {
            MainWindow.setPaneFragment("/application/Gestion.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            new AlertMessage("Error", "Error d'ouvrir la fenetres du gestion !!!\nERROR : " + e.getMessage(), AlertType.ERROR);
        }*/
        System.out.println("commande");
    }
    /**********************************************************/
    @FXML
    public void article(){
        try {
            MainWindow.setPaneFragment("/Model/Article/Articles.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**********************************************************/
    @FXML
    public void client(){
        try {
            MainWindow.setPaneFragment("/Clients/Clients.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**********************************************************/
    @FXML
    public void fournisseur(){

    }
    /**********************************************************/
    @FXML
    public void my_profile(){

    }
    /**********************************************************/
    @FXML
    public void users(){

    }
    /**********************************************************/
    @FXML
    public void statistics(){

    }
}