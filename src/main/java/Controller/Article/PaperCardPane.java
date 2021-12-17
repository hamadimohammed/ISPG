package Controller.Article;

import Application.AlertMessage;
import Application.EditingStringCell;
import Database.ArticleEntity;
import Database.CategorieEntity;
import Model.Article.Article;
import Model.Article.Categorie;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class PaperCardPane extends VBox{
    private String type,color;


    private CategorieEntity connect_categorie = CategorieEntity.get_categorie_db();



    private ObservableList<Categorie> observe_categorie = FXCollections.observableArrayList();
    private ObservableList<Categorie> updated_observe_categorie = FXCollections.observableArrayList();
    private ObservableList<Categorie> inserted_observe_categorie = FXCollections.observableArrayList();
    private ObservableList<Categorie> deleted_observe_categorie = FXCollections.observableArrayList();

    private Text type_text = new Text();
    private ListView<Categorie> categorieList = new ListView<>();
    private HBox btn_container = new HBox();
    private ToggleButton add_btn ;
    private ToggleButton update_btn ;
    private ToggleButton delete_btn ;

    public PaperCardPane(String type,String color) {
        this.type=type;
        this.color=color;

        this.setWidth(150);
        this.getStyleClass().add("card_paper");
        this.setStyle("-fx-background-color: "+color);
        this.prefHeightProperty().bind(Bindings.size(observe_categorie).multiply(25).add(75));
        this.categorieList.prefHeightProperty().bind(Bindings.size(observe_categorie).multiply(25));
        initialize();
    }
    private void properties(){
        update_btn.setDisable(true);
        delete_btn.setDisable(true);
        updated_observe_categorie.addListener((ListChangeListener<? super Categorie>) change -> {
            change.next();
            if (change.getAddedSize()==1)
                update_btn.setDisable(false);
            else
                update_btn.setDisable(true);
        });
        deleted_observe_categorie.addListener((ListChangeListener<? super Categorie>) change -> {
            change.next();
            if (change.getAddedSize()==1)
                update_btn.setDisable(false);
            else
                update_btn.setDisable(true);
        });
        inserted_observe_categorie.addListener((ListChangeListener<? super Categorie>) change -> {
            change.next();
            if (change.getAddedSize()==1)
                update_btn.setDisable(false);
            else
                update_btn.setDisable(true);
        });
        categorieList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                delete_btn.setDisable(false);
            }
            else
                delete_btn.setDisable(true);
        });
    }
    public void initialize(){
        init_btns();
        init_pane();
        properties();
        load_data();
        load_permission();
        actions();
        editable_cols();

    }
    private void init_btns(){
        this.add_btn = new CustomButton("","/Assets/add_64px.png","btn");
        this.update_btn = new CustomButton("","/Assets/save_64px.png","btn");
        this.delete_btn = new CustomButton("","/Assets/delete_64px.png","btn");
    }

    private void init_pane(){
        type_text.setText(type);
        type_text.setStyle("-fx-font-size: 18px");
        HBox center_type = new HBox();
        center_type.setAlignment(Pos.CENTER);
        center_type.getChildren().add(type_text);

        btn_container.getChildren().addAll(add_btn,update_btn,delete_btn);
        btn_container.setSpacing(5);
        getChildren().add(center_type);

        getChildren().add(btn_container);
        getChildren().add(categorieList);
        setSpacing(5);

        categorieList.setFocusTraversable(true);
    }
    public void editable_cols() {
        categorieList.setOnEditCommit(e -> {
            Categorie categorie = e.getSource().getItems().get(e.getIndex());
            updated_observe_categorie.add(categorie);
            categorieList.requestFocus();
        });
    }
    private void load_permission() {
        categorieList.setEditable(true);
        categorieList.setCellFactory(list_view->{
            TextFieldListCell<Categorie> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<Categorie>() {
                @Override
                public String toString(Categorie categorie) {
                    return categorie.getLibelle();
                }

                @Override
                public Categorie fromString(String s) {
                    Categorie categorie = cell.getItem();
                    categorie.setLibelle(s);
                    return categorie;
                }
            });
            return cell;
        });
    }
    private void load_data(){
        observe_categorie.clear();
        updated_observe_categorie.clear();
        inserted_observe_categorie.clear();
        deleted_observe_categorie.clear();
        observe_categorie.addAll(connect_categorie.getCategoriesType(type));
        categorieList.setItems(observe_categorie);
    }

    public void actions(){
        add_btn.setOnAction(actionEvent -> {
            Categorie categorie = new Categorie(type);
            categorie.setLibelle("categorie");
            inserted_observe_categorie.add(categorie);
            this.categorieList.getItems().add(categorie);
        });
        update_btn.setOnAction(event->{
            if (!deleted_observe_categorie.isEmpty()){
                deleted_observe_categorie.removeAll(inserted_observe_categorie);
                deleted_observe_categorie.forEach(categorie -> {
                    connect_categorie.deleteCategorie(categorie.getId());
                });
            }
            if (!inserted_observe_categorie.isEmpty()){
                inserted_observe_categorie.forEach(categorie -> {
                    if (categorie.getLibelle()!=null && !categorie.getLibelle().isEmpty() && !categorie.getLibelle().contentEquals("categorie")){
                        connect_categorie.insertCategorie(categorie);
                    }
                });
                updated_observe_categorie.removeAll(inserted_observe_categorie);
            }
            if (!updated_observe_categorie.isEmpty()){
                updated_observe_categorie.forEach(categorie->{
                    connect_categorie.updateCategorie(categorie);
                });
            }
            load_data();
        });
        delete_btn.setOnAction(event ->{
            Categorie selected_categorie = categorieList.getSelectionModel().getSelectedItem();
            if (selected_categorie!=null){
                if (AlertMessage.delete("Categorie","la categorie")){
                    if (inserted_observe_categorie.remove(selected_categorie)){
                        observe_categorie.remove(selected_categorie);
                    }
                    else if(connect_categorie.deleteCategorie(selected_categorie.getId()))
                        observe_categorie.remove(selected_categorie);
                }
            }
        });
        categorieList.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.DELETE)){
                Categorie categorie = categorieList.getSelectionModel().getSelectedItem();
                deleted_observe_categorie.add(categorie);
                observe_categorie.remove(categorie);
            }
        });
    }
}
