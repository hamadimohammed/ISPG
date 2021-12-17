package Model.Article;

public class ArticleVente extends Article{
    private int id_operation,qte_livrer;
    private double prix;
    private String description;

    public int getId_operation() {
        return id_operation;
    }

    public void setId_operation(int id_operation) {
        this.id_operation = id_operation;
    }

    public int getQte_livrer() {
        return qte_livrer;
    }

    public void setQte_livrer(int qte_livrer) {
        this.qte_livrer = qte_livrer;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
