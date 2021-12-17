package Database;

import Application.AlertMessage;
import Model.Article.Article;
import Model.Article.Categorie;
import javafx.scene.control.Alert.AlertType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategorieEntity extends DbConnect {
	private static CategorieEntity categorie_connect;
	/************************* Article sql interaction *******************/
	private CategorieEntity() {

	}
	public static CategorieEntity get_categorie_db(){
		return (categorie_connect!=null?categorie_connect:(categorie_connect = new CategorieEntity()));
	}
	/********************************************/
	public boolean insertCategorie(Categorie categorie) {
		String query = "INSERT INTO categorie (`libelle`,`type`) VALUES (?,?)";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setString(1, categorie.getLibelle());
			preparedStatement.setString(2, categorie.getType());

			if (preparedStatement.executeUpdate() == 0) {
				new AlertMessage("Ajouter", "Votre categorie n'a pas étè bien inserer", AlertType.INFORMATION);
				return false;
			}
			else
				return true;
				
		} catch (SQLException e) {
			new AlertMessage("Error", "Impossible d'inserer categorie à la base de donnée à cause de l'error : " + e.getMessage(),
					AlertType.ERROR);
			e.printStackTrace();
			return false;
		}
	}
	/********************************************/
	public boolean updateCategorie(Categorie categorie) {
		String query = "UPDATE categorie SET `libelle`=? where id_categ=? ";
		try {
			PreparedStatement prepareStatement = super.connection.prepareStatement(query);
			prepareStatement.setString(1, categorie.getLibelle());
			prepareStatement.setInt(2, categorie.getId());
			
			if(prepareStatement.executeUpdate()==0) {
				new AlertMessage("Edit","La categorie "+categorie.getLibelle()+"n'a pas ete bien modifier", AlertType.INFORMATION);
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			new AlertMessage("Edit","L'article n'a pas ete bien modifier\nError : "+e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * @return******************************************/
	public boolean deleteCategorie(int id_categ) {
		String query = "DELETE FROM `categorie` WHERE `id_categ`=?";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setInt(1, id_categ);
			if(preparedStatement.executeUpdate()==0) {
				new AlertMessage("Supprimer", "La categorie n'a pas ete bien supprimer", AlertType.ERROR);
				return false;
			}
			return true;
		} catch (SQLException e) {
			new AlertMessage("Error", "Vous pouvez pas supprimer l'article avec l'id : "+id_categ+"\nError : "+e.getMessage(),AlertType.ERROR);
			return false;
		}
	}
	/********************************************/
	public boolean is_libelle_exist(String libelle) {
		String query = "select id_categ from categorie where libelle = ?";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setString(1,libelle);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/********************************************/
	public ArrayList<Categorie> getCategories() {
		ArrayList<Categorie> liste = new ArrayList<>();
		String query = "SELECT * FROM categorie";
		try {
			Statement statement = super.connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(result.getInt("id_categ"));
				categorie.setLibelle(result.getString("libelle"));
				categorie.setType(result.getString("type"));
				liste.add(categorie);
			}
			return liste;
		} catch (SQLException e) {
			new AlertMessage("Error", "Impossible de charger les categories de la base à cause de l'error : " + e.getMessage(),
					AlertType.ERROR);
			return null;
		}

	}
	/********************************************/
	public ArrayList<Categorie> getCategoriesType(String type) {
		ArrayList<Categorie> liste = new ArrayList<>();
		String query = "SELECT * FROM categorie where type=?";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setString(1,type);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(result.getInt("id_categ"));
				categorie.setLibelle(result.getString("libelle"));
				categorie.setType(result.getString("type"));
				liste.add(categorie);
			}
			return liste;
		} catch (SQLException e) {
			new AlertMessage("Error", "Impossible de charger les categories de la base à cause de l'error : " + e.getMessage(),
					AlertType.ERROR);
			return null;
		}
	}
	public ArrayList<String> get_types(){
		ArrayList<String> list = new ArrayList<>();
		String query = "select type from categorie where type<>'NONE' group by type";
		try {
			Statement statement = super.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				list.add(resultSet.getString("type"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*********************************************************************/

}
