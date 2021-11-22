package Model.Personne.Intervenant;

public class ExternePersonne extends Personne{
    private double solde;

    public ExternePersonne() {
        super();
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
