package Model.Gestion;

import Model.Gestion.Livarison.LivraisonBon;
enum Etat{
    livrer,en_cours
}
public class Commande extends LivraisonBon {

    private int id_facture;
    private Etat etat;

    public Commande() {
        super();
    }
}
