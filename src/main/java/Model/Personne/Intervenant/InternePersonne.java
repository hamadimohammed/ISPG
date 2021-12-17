package Model.Personne.Intervenant;

import Model.Personne.Personne;

import java.util.ArrayList;

public class InternePersonne extends Personne {
    private String password,username ;
    private ArrayList<String> accees;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getAccees() {
        return accees;
    }

    public void setAccees(ArrayList<String> accees) {
        this.accees = accees;
    }
}
