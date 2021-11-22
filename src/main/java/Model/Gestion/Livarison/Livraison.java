package Model.Gestion.Livarison;

import Model.Personne.Client.Client;
import Model.Personne.Intervenant.InternePersonne;

public class Livraison {
    InternePersonne personne;
    Client client;
    private String date;
    private int nbr_article,id;

    public Livraison() {
        super();
    }

    public InternePersonne getPersonne() {
        return personne;
    }

    public void setPersonne(InternePersonne personne) {
        this.personne = personne;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbr_article() {
        return nbr_article;
    }

    public void setNbr_article(int nbr_article) {
        this.nbr_article = nbr_article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
