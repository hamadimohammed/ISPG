package Model.Gestion;

import Model.Gestion.Livarison.LivraisonBon;
enum ModePaiement{
    Espece,Cheque
}
public class Facture extends LivraisonBon {
    private int id_comande;
    private String mode_paiement;

    public Facture() {
        super();
    }

    public int getId_comande() {
        return id_comande;
    }

    public void setId_comande(int id_comande) {
        this.id_comande = id_comande;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }
}
