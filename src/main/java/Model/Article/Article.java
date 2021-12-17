package Model.Article;

public class Article {
    private int id_article;
    private String design;
    private Categorie categorie;

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id_article=" + id_article +
                ", design='" + design + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
