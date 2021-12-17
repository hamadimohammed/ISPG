package Model.Gestion;

import Model.Article.ArticleCommander;
import Model.Gestion.Factures.Facture;

import java.util.List;

enum Etat{
    livrer,en_cours
}
public class Commande extends SimpleOperation {

    private Facture facture;
    private Etat etat;
    private List<ArticleCommander> articles_commander;

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public List<ArticleCommander> getArticles_commander() {
        return articles_commander;
    }

    public void setArticles_commander(List<ArticleCommander> articles_commander) {
        this.articles_commander = articles_commander;
    }
}
