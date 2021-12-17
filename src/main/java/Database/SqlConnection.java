package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
	private static Connection c = null;
	public static Connection getConn() throws Exception {
	    if(c == null){
	    Class.forName("org.sqlite.JDBC");
	    c = DriverManager.getConnection("jdbc:sqlite:Tahri.sqlite");
	    }
	    return c;
	}
	/*
	public static Connection connector() {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager.getConnection("jdbc:sqlite:grosserie.sqlite");
			return connect;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
