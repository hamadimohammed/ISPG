package Model.Gestion;

import Model.Personne.Externe.Client;
import Model.Personne.Intervenant.InternePersonne;

public class Operation {
    InternePersonne user;
    Client client;
    private String date;
    private int nbr_article,id;

    public InternePersonne getUser() {
        return user;
    }

    public void setUser(InternePersonne user) {
        this.user = user;
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
