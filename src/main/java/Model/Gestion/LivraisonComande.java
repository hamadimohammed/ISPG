package Model.Gestion;

import Model.Article.ArticleLivrer;
import Model.Gestion.Operation;
import javafx.scene.control.ToggleButton;

import java.util.List;

public class LivraisonComande extends Operation {
    private int id_commande;
    private ToggleButton print_btn ;
    private List<ArticleLivrer> articles_livres;

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public List<ArticleLivrer> getArticles_livres() {
        return articles_livres;
    }

    public void setArticles_livres(List<ArticleLivrer> articles_livres) {
        this.articles_livres = articles_livres;
    }

    public ToggleButton getPrint_btn() {
        return print_btn;
    }

    public void setPrint_btn(ToggleButton print_btn) {
        this.print_btn = print_btn;
    }
}
