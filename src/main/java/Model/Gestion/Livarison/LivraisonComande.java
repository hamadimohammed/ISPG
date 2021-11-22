package Model.Gestion.Livarison;

import javafx.scene.control.ToggleButton;

public class LivraisonComande extends Livraison{
    private int id_comande;
    private ToggleButton print_btn ;

    public LivraisonComande() {
        super();
    }

    public int getId_comande() {
        return id_comande;
    }

    public void setId_comande(int id_comande) {
        this.id_comande = id_comande;
    }

    public ToggleButton getPrint_btn() {
        return print_btn;
    }

    public void setPrint_btn(ToggleButton print_btn) {
        this.print_btn = print_btn;
    }
}
