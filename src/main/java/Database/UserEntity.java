package Database;

import Model.Personne.Interne.Admin;
import Model.Personne.Intervenant.InternePersonne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserEntity extends DbConnect{

    private static UserEntity userConnection;
    private UserEntity() {

    }
    public static UserEntity get_user_connection(){
        if (userConnection==null)
            return new UserEntity();
        else return userConnection;
    }


    public InternePersonne connect(String username, String password){
        String query = "Select * from user where `username`=? and `password`=?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                InternePersonne user = (resultSet.getString("type").contentEquals("admin")?new Admin():new InternePersonne());
                user.setId(resultSet.getInt("id"));
                user.setUsername(username);
                user.setPassword(password);
                user.setAccees(get_droit_acces(user.getId()));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> get_droit_acces(int id_user){
        String query = "Select * from users_access where `id_user`=?";
        try {
            ArrayList<String> list = new ArrayList<>();
            PreparedStatement preparedStatement = super.connection.prepareStatement(query);
            preparedStatement.setInt(1,id_user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString(2));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
