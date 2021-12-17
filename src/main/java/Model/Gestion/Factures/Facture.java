package Model.Gestion.Factures;

import Model.Article.ArticleVente;
import Model.Gestion.Commande;
import Model.Gestion.SimpleOperation;

public class Facture extends SimpleOperation {
    private Commande commande;
    private ArticleVente articleVente;

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public ArticleVente getArticleVente() {
        return articleVente;
    }

    public void setArticleVente(ArticleVente articleVente) {
        this.articleVente = articleVente;
    }
}
