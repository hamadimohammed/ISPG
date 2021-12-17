package Controller.Article;

import Application.AlertMessage;
import Application.EditingStringCell;
import Database.ArticleEntity;
import Database.CategorieEntity;
import Interfaces.GestionController;
import Model.Article.Article;
import Model.Article.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticlesController implements GestionController,ObservableArticle{
    public static ArticlesController controller;
    private ArticleEntity article_connection = ArticleEntity.get_article_db();
    private CategorieEntity categorie_connection = CategorieEntity.get_categorie_db();
    List<ObserverArticle> observer_list = new ArrayList<>();

    ObservableList<Article> observe_article = FXCollections.observableArrayList();
    ObservableList<Article> observe_updated_article = FXCollections.observableArrayList();
    ObservableList<Article> observe_inserted_article = FXCollections.observableArrayList();
    FilteredList<Article> filtered_article = new FilteredList<>(observe_article, e->true);
    ObservableList<Categorie> observe_categories = FXCollections.observableArrayList();

    @FXML TableView<Article> article_table;
    @FXML TableColumn<Article, String> design_column;
    @FXML TableColumn<Article, Integer> id_column;

    @FXML
    HBox scroll_hbox;
    @FXML
    TextField search_field;
    @FXML
    Label nbr_articles;
    @FXML
    ToggleButton save_btn,delete_btn;
    /*************************************************************************************************/
    private void load_cards(){
        HashMap<String,String> color_map = new HashMap<>();
        color_map.put("Size","#74BDCB");
        color_map.put("Type","#E7F2F8");
        color_map.put("Format","#EFE7BC");
        color_map.put("Autre","#FFA384");
        categorie_connection.get_types().forEach(card_type ->{
            scroll_hbox.getChildren().add(new PaperCardPane(card_type,color_map.get(card_type)));
        });
        scroll_hbox.setSpacing(10);
    }
    /***********************************************/
    private void init_table(){
        id_column.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        design_column.setCellValueFactory(new PropertyValueFactory<>("design"));
        //categorie_column.setCellValueFactory(new PropertyValueFactory<>("categorie"));

    }
    /***********************************************/
    @Override
    public void load_permission(){
        article_table.setEditable(true);
    }
    /***********************************************/
    @Override
    public void load_data(){
        observe_article.clear();
        observe_updated_article.clear();
        observe_article.addAll(article_connection.getArticles());
        article_table.setItems(observe_article);
        nbr_articles.setText(String.valueOf(observe_article.size()));
        observe_categories.addAll(categorie_connection.getCategories());
    }
    /***********************************************/
    @Override
    public void editable_cols() {
        design_column.setCellFactory(e -> new EditingStringCell<>());
        design_column.setOnEditCommit(e->{
            Article article = e.getTableView().getItems().get(e.getTablePosition().getRow());
            article.setDesign(e.getNewValue());
            observe_updated_article.add(article);
            e.getTableView().refresh();
        });

        /*categorie_column.setCellFactory(ComboBoxTableCell.forTableColumn(observe_categories));
        categorie_column.setOnEditCommit(e->{
            Article article = e.getTableView().getItems().get(e.getTablePosition().getRow());
            article.setCategorie(e.getNewValue());
            observe_updated_article.add(article);
            e.getTableView().refresh();
        });*/
    }
    /***********************************************/
    @Override
    public void properties() {
        save_btn.setDisable(true);
        delete_btn.setDisable(true);
        search_field.textProperty().addListener((observableValue,oldValue,newValue)->{
            filtered_article.setPredicate( Article ->{
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                else if(String.valueOf(Article.getId_article()).contains(newValue.toLowerCase())){
                    return true;
                }
                else if(Article.getDesign().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                return false;
            });
            SortedList<Article> sortedList = new SortedList<>(filtered_article);
            sortedList.comparatorProperty().bind(article_table.comparatorProperty());
            article_table.setItems(sortedList);
        });
        observe_updated_article.addListener((ListChangeListener<? super Article>) change -> {
            change.next();
            if (change.getAddedSize()==1)
                save_btn.setDisable(false);
            else
                save_btn.setDisable(true);
        });
        article_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                delete_btn.setDisable(false);
            }
            else
                delete_btn.setDisable(true);
        });
    }
    /***********************************************/

    /***********************************************/
    @Override
    public void initialize() {
        properties();
        init_table();
        load_permission();
        load_data();
        editable_cols();
        load_cards();
        controller=this;
    }
    /***********************************************/
    @Override
    public void insert() {
        Article article = new Article();
        article.setCategorie(categorie_connection.getCategories().get(0));
        observe_inserted_article.add(article);
        article_table.getItems().add(article);
    }
    /***********************************************/
    @Override
    public void update() {
        if (!observe_inserted_article.isEmpty()){
            observe_inserted_article.forEach(article -> {
                if (article.getDesign()!=null && !article.getDesign().isEmpty()){
                    article_connection.insertArticle(article);
                }
            });
            observe_updated_article.removeAll(observe_inserted_article);
        }
        if (!observe_updated_article.isEmpty()){
            observe_updated_article.forEach(article->{
                article_connection.updateArticle(article);
            });
        }
        notifyObservers();
        load_data();
    }
    /***********************************************/
    @Override
    public void delete() {
        if (!article_table.getSelectionModel().isEmpty()){
            Article article = article_table.getSelectionModel().getSelectedItem();
            if(AlertMessage.AlertMessageConfirmation("Supprimer un article",
                    "Voulez-vous vraiment supprimer l'article "+article.getDesign(),"CONFIRMATION")){
                if (article_connection.deleteArticle(article.getId_article())) {
                    load_data();
                    notifyObservers();
                }

            }
        }
    }
    /***********************************************/
    @Override
    public void events(KeyEvent event) {

    }
    /***********************************************/
    /***********************************************/
    //Observer pattern implementation
    @Override
    public void attach(ObserverArticle observer) {
        observer_list.add(observer);
    }
    /***********************************************/
    @Override
    public void dettach(ObserverArticle observer) {
        observer_list.remove(observer);
    }
    /***********************************************/
    @Override
    public void notifyObservers() {
        observer_list.forEach(observerArticle -> observerArticle.set_article_changements());
    }
    //Fin d'implementation
}
