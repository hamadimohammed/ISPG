package Model.Gestion;

import Model.Gestion.Operation;

public class SimpleOperation extends Operation {
    private double total,versement,a_solde;

    public SimpleOperation() {
        super();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVersement() {
        return versement;
    }

    public void setVersement(double versement) {
        this.versement = versement;
    }

    public double getA_solde() {
        return a_solde;
    }

    public void setA_solde(double a_solde) {
        this.a_solde = a_solde;
    }
}
