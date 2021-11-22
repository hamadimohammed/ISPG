package Model.Personne.Intervenant;

public class InternePersonne extends Personne{
    private String password,username ;

    public InternePersonne() {
        super();
    }

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
}
