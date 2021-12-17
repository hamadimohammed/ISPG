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
import java.util.Locale;

public class ArticleEntity extends DbConnect {
	private static ArticleEntity article_connect;
	NumberFormat number = NumberFormat.getInstance(Locale.ENGLISH);
	/************************* Article sql interaction *******************/
	private ArticleEntity() {
		number.setMinimumFractionDigits(2);
		number.setMaximumFractionDigits(2);
	}
	public static ArticleEntity get_article_db(){
		return (article_connect!=null?article_connect:(article_connect = new ArticleEntity()));
	}
	/********************************************/
	public boolean insertArticle(Article article) {
		String query = "INSERT INTO article (`designation`,`id_categ`) VALUES (?,?)";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setString(1, article.getDesign());
			preparedStatement.setInt(2, article.getCategorie().getId());

			if (preparedStatement.executeUpdate() == 0) {
				new AlertMessage("Ajouter", "Votre article n'a pas étè bien inserer", AlertType.INFORMATION);
				return false;
			}
			else
				return true;
				
		} catch (SQLException e) {
			new AlertMessage("Error", "Impossible d'inserer l'article à la base de donnée à cause de l'error : " + e.getMessage(),
					AlertType.ERROR);
			e.printStackTrace();
			return false;
		}
	}
	/********************************************/
	public ArrayList<Article> getArticles() {
		ArrayList<Article> liste = new ArrayList<>();
		String query = "SELECT * FROM article_view";

		try {
			Statement statement = super.connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				Article article = new Article();
				article.setId_article(result.getInt("id_article"));
				article.setDesign(result.getString("designation"));
				article.setCategorie(new Categorie(result.getInt("id_categ"),result.getString("libelle"),result.getString("type")));
				liste.add(article);
			}
			return liste;
		} catch (SQLException e) {
			new AlertMessage("Error", "Impossible de charger les article de la base à cause de l'error : " + e.getMessage(),
					AlertType.ERROR);
			return null;
		}

	}
	/********************************************/
	public boolean updateArticle(Article article) {
		String query = "UPDATE article SET `designation`=?,`id_categ`=? where id_article=? ";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = super.connection.prepareStatement(query);
			prepareStatement.setString(1, article.getDesign());
			prepareStatement.setInt(2, article.getCategorie().getId());
			prepareStatement.setInt(3, article.getId_article());
			
			if(prepareStatement.executeUpdate()==0) {
				new AlertMessage("Edit","L'article "+article.getDesign()+"n'a pas ete bien modifier", AlertType.INFORMATION);
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
	public boolean deleteArticle(int id_article) {
		String query = "DELETE FROM `article` WHERE `id_article`=?";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setInt(1, id_article);
			if(preparedStatement.executeUpdate()==0) {
				new AlertMessage("Supprimer", "L'article n'a pas ete bien supprimer", AlertType.ERROR);
				return false;
			}
			return true;
		} catch (SQLException e) {
			new AlertMessage("Error", "Vous pouvez pas supprimer l'article avec l'id : "+id_article+"\nError : "+e.getMessage(),AlertType.ERROR);
			return false;
		}
	}
	/********************************************/
	public Article get_article(int id_article) {
		String query = "select * FROM article_view where id_article=?";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setInt(1, id_article);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Article article = new Article();
				article.setId_article(resultSet.getInt("id_article"));
				article.setDesign(resultSet.getString("designation"));
				article.setCategorie(new Categorie(resultSet.getInt("id_categ"),resultSet.getString("libelle"),resultSet.getString("type")));
				return article;
			}
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/********************************************/
	public boolean is_Design_exist(String design) {
		String query = "select id_article from article where designation = ?";
		try {
			PreparedStatement preparedStatement = super.connection.prepareStatement(query);
			preparedStatement.setString(1,design);
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
	/*********************************************************************/

}
